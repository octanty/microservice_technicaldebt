package TDMeasurement.MeasurementService.service;

import TDMeasurement.MaintainabilityIndexService.vo.Directory;
import TDMeasurement.MeasurementService.entity.GenericStatistic;
import TDMeasurement.MeasurementService.repository.GenericStatisticRepository;

import TDMeasurement.MeasurementService.vo.DirResult;
import java.io.*;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class MeasurementService {
    //private static Gson gson;
    private static String UPLOADED_FOLDER = "C:\\Users\\Octanty\\Documents\\Course\\Masaryk\\thesis\\JavaProjectForThesis\\";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private GenericStatisticRepository genericStatisticRepository;


    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {

        return builder.build();
    }

    public GenericStatistic findByGenericStatisticID(long genericStatisticID) {
        return genericStatisticRepository.findByGenericStatisticID(genericStatisticID);
    }

    public String saveToRepository(){
        restTemplate.exchange("http://localhost:8004/directories/saveToRepository",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Directory>>() {
                        });
        return "Successfully saved";
    }

  /*  public String deleteDirectory(){
        restTemplate.exchange("http://localhost:8004/directories/deleteDirectory",
                HttpMethod.DELETE, null, new ParameterizedTypeReference<>() {
                });
        return "Directory deleted";
    }*/


    public String calculate() throws IOException, URISyntaxException {
        saveToRepository();
        calculateMI();
        return "Successfully Calculated";
    }

    public List<DirResult> calculateMI() throws  URISyntaxException, IOException {
        ResponseEntity<List<DirResult>> responseEntity = restTemplate.exchange("http://localhost:8004/mi/calculate",
                HttpMethod.POST, null, new ParameterizedTypeReference<List<DirResult>>() {
                });
        List<DirResult> result = responseEntity.getBody();
        return result;
    }


    public int GetCommentNumber(List<String> result) throws IOException {
        int count = 0;
        for (String results : result) {
            String line = "";
                BufferedReader br = new BufferedReader(new FileReader(results));
                while ((line = br.readLine()) != null) {
                    if (line.startsWith("//")) {
                        count++;
                    } else if (line.startsWith("/*")) {
                        count++;
                        while ((line = br.readLine())!=null && !line.endsWith("'*\'"));
                    }
                }
                br.close();
        }
        return count;
    }

    public int GetLineOfCode(List<String> result) throws IOException {
        int count = 0;
        for (String results : result) {
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader(results));
            while ((line = br.readLine()) != null) {
                    count++;
            }
            br.close();
        }
        return count;
    }

 /*  public static void main(String[] args) throws IOException, URISyntaxException{
        RestTemplate restTemplate = new RestTemplate();
        gson = new GsonBuilder().setPrettyPrinting().create();
        String access_token = "access_token=dcb950a2efd8eaa09132a28b8aa08f0f7640403a";
        Map jsonMap = restTemplate.getForObject("https://api.github.com/repos/octanty/JavaProjectForThesis/branches/main?"+access_token, Map.class);

        String treeApiUrl = gson.toJsonTree(jsonMap).getAsJsonObject().get("commit").getAsJsonObject().get("commit")
                .getAsJsonObject().get("tree").getAsJsonObject().get("url").getAsString();
        System.out.println("TREE API URL = " + treeApiUrl + "\n");

        Map jsonTreeMap = restTemplate.getForObject(treeApiUrl + "?recursive=1&" +access_token, Map.class);

        for (Object obj : ((List) jsonTreeMap.get("tree"))) {

            Map fileMetadata = (Map) obj;

            // Type tree will be directory & blob will be file. Print files & directory
            // list with file sizes.
            if (fileMetadata.get("type").equals("tree")) {
                    System.out.println("Directory = " + fileMetadata.get("path"));

            } /* else {
                System.out.println(
                        "File = " + fileMetadata.get("path") + " | Size = " + fileMetadata.get("size") + " Bytes");
            }*/
    /*    }
    } */
}


