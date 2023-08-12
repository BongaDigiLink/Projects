package za.co.project_03;

import za.co.project_03.utils.CSVHelper;
import java.io.IOException;

public class Project03Application
{
    public static void main(String[] args)
    {
        try {
            CSVHelper.readFile("TRANSACTION_002.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String na : CSVHelper.getColumns()){
        System.out.println("col : "+na);
        }
    }
}
