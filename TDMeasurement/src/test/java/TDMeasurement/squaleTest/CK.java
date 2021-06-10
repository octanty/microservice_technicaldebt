package TDMeasurement;



import com.github.mauricioaniche.ck.CKNotifier;
import com.github.mauricioaniche.ck.MetricsExecutor;
import com.github.mauricioaniche.ck.metric.ClassLevelMetric;
import com.github.mauricioaniche.ck.metric.MethodLevelMetric;
import com.github.mauricioaniche.ck.util.FileUtils;
import com.github.mauricioaniche.ck.util.MetricsFinder;
import com.google.common.collect.Lists;
import org.eclipse.jdt.core.dom.AST;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.ASTParser;

public class CK {
    private final int maxAtOnce;
    private final boolean useJars;
    private static Logger log = Logger.getLogger(com.github.mauricioaniche.ck.CK.class);
    Callable<List<ClassLevelMetric>> classLevelMetrics;
    Callable<List<MethodLevelMetric>> methodLevelMetrics;

    public CK(Callable<List<ClassLevelMetric>> classLevelMetrics, Callable<List<MethodLevelMetric>> methodLevelMetrics) {
        this.useJars = false;
        this.classLevelMetrics = classLevelMetrics;
        this.methodLevelMetrics = methodLevelMetrics;
        this.maxAtOnce = 100;
    }

    public CK(boolean useJars, int maxAtOnce, boolean variablesAndFields) {
        MetricsFinder finder = new MetricsFinder();
        this.classLevelMetrics = () -> {
            return finder.allClassLevelMetrics();
        };
        this.methodLevelMetrics = () -> {
            return finder.allMethodLevelMetrics(variablesAndFields);
        };
        this.useJars = useJars;
        if (maxAtOnce == 0) {
            this.maxAtOnce = this.getMaxPartitionBasedOnMemory();
        } else {
            this.maxAtOnce = maxAtOnce;
        }

    }

    public CK() {
        this(false, 0, true);
    }

    public void calculate(String path, CKNotifier notifier) {
        String[] javaFiles = FileUtils.getAllJavaFiles(path);
        log.info("Found " + javaFiles.length + " java files");
        this.calculate(Paths.get(path), notifier, (Path[])Stream.of(javaFiles).map((x$0) -> {
            return Paths.get(x$0);
        }).toArray((x$0) -> {
            return new Path[x$0];
        }));
    }

    public void calculate(Path path, CKNotifier notifier) {
        this.calculate(path.toString(), notifier);
    }

    public void calculate(Path path, CKNotifier notifier, Path... javaFilePaths) {
        String[] srcDirs = FileUtils.getAllDirs(path.toString());
        log.info("Found " + srcDirs.length + " src dirs");
        String[] allDependencies = this.useJars ? FileUtils.getAllJars(path.toString()) : null;
        if (this.useJars) {
            log.info("Found " + allDependencies.length + " jar dependencies");
        }

        MetricsExecutor storage = new MetricsExecutor(this.classLevelMetrics, this.methodLevelMetrics, notifier);
        List<String> strJavaFilePaths = (List)Stream.of(javaFilePaths).map((file) -> {
            return file.isAbsolute() ? file.toString() : path.resolve(file).toString();
        }).collect(Collectors.toList());
        List<List<String>> partitions = Lists.partition(strJavaFilePaths, this.maxAtOnce);
        log.debug("Max partition size: " + this.maxAtOnce + ", total partitions=" + partitions.size());
        Iterator var9 = partitions.iterator();

        while(var9.hasNext()) {
            List<String> partition = (List)var9.next();
            log.debug("Next partition");
            ASTParser parser = ASTParser.newParser(3);
            parser.setResolveBindings(true);
            parser.setBindingsRecovery(true);
            Map<String, String> options = JavaCore.getOptions();
            JavaCore.setComplianceOptions("11", options);
            parser.setCompilerOptions(options);
            parser.setEnvironment(allDependencies, srcDirs, (String[])null, true);
            parser.createASTs((String[])partition.toArray(new String[partition.size()]), (String[])null, new String[0], storage, (IProgressMonitor)null);
        }

        log.info("Finished parsing");
    }

    private int getMaxPartitionBasedOnMemory() {
        long maxMemory = Runtime.getRuntime().maxMemory() / 1048576L;
        if (maxMemory >= 2000L) {
            return 400;
        } else if (maxMemory >= 1500L) {
            return 300;
        } else if (maxMemory >= 1000L) {
            return 200;
        } else {
            return maxMemory >= 500L ? 100 : 25;
        }
    }
}
