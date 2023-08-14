package za.co.project_03;

import org.apache.commons.csv.CSVRecord;
import za.co.project_03.utils.CSVHelper;
import java.io.IOException;
import java.util.Arrays;

public class Project03Application
{
    public static void main(String[] args)
    {
        try
        {
            Iterable<CSVRecord> records =  CSVHelper.readFile("TRANSACTION_002.csv");
            for(CSVRecord csvRecord : records)
            {
//                System.out.println("");
//                System.out.println("Record is: "+ csvRecord);
//                System.out.println("Hash is: "+ CSVHelper.recordHash(csvRecord));
//                System.out.println("Object String is: "+CSVHelper.recordStringObject(csvRecord));
            }
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        String[] exl = {"DataType","SystemName"};
        String[] targCol = CSVHelper.createTargetColumns(exl);
        for(String name: targCol)
        {
            System.out.println("Target column: "+ name);
        }
    }
}
