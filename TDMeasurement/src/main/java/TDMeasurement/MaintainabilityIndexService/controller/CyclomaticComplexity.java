package TDMeasurement.MaintainabilityIndexService.controller;

import java.io.*;
import java.util.*;

public class CyclomaticComplexity {
    /* Get CC Result */
    public int getCCResult(String filePath) {

        //todo: give attribution
        int numComplexity = 0;
        String javaFile;
        String[] keywords = {"if","else","while","case","for","switch","do","continue","break","&&","||","?",":","catch","finally","throw","throws","default","return"};
        String words = "";
        String codeLine = null;
        try {
            javaFile = filePath;
            FileReader reader = new FileReader(javaFile);
            BufferedReader br = new BufferedReader(reader);
            codeLine = br.readLine();
            while (codeLine != null)
            {
                StringTokenizer stTokenizer = new StringTokenizer(codeLine);
                while (stTokenizer.hasMoreTokens())
                {
                    words = stTokenizer.nextToken();
                    for(int i=0; i<keywords.length; i++)
                    {
                        if (keywords[i].equals(words))
                        {
                            numComplexity++;
                        }
                    }
                }
                codeLine = br.readLine();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return numComplexity;
    }
    //get CCRate
    public String getCCRate(int ccResult) {
        if (ccResult> 50){
            return "Extra High";
        }
        else if(ccResult>= 41 && ccResult<=50)
            return "Very High";
        else if(ccResult>= 21 && ccResult<=40)
            return "High";
        else if(ccResult>= 11 && ccResult<=20)
            return "Nominal";
        else if(ccResult>= 5 && ccResult<=10)
            return "Low";
        else if(ccResult>= 1 && ccResult<=4)
            return "Very Low";
        else
            return "Very Low";
    }

}