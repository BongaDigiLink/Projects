package za.co.project_03.utils;

import com.google.common.hash.Hashing;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class CSVHelper
{
    public static String[] CSV_HEADER_COLUMNS;
    public static String[] CSV_EXCLUSION_LIST;
    private static String[] COLUMNS;
    public static String[] REQUIRED_DATA_COLUMNS;

    public static Iterable<CSVRecord> readFile(String filename) throws IOException
    {
        try
        {
            Reader reader = new FileReader(System.getProperty("user.dir") + "/" + filename);
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
            COLUMNS = getCsvHeaders(filename);
            //REQUIRED_DATA_COLUMNS = getCsvHeaders(filename);
            return records;
        }
        catch (FileNotFoundException fn)
        {
            System.err.println("File not found.");
            return null;
        }catch (IOException e)
        {
            System.err.println("Could not read file.");
            return null;
        }
    }

    public static String[] getCsvHeaders(String filename) throws NullPointerException
    {
        try
        {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(
                    System.getProperty("user.dir")+"/"+filename));
            String header = bufferedReader.readLine();
            if(header != null)
            {
                return header.split(",");
            }
            throw new NullPointerException("The "+filename+" does not have headers!");
        }
        catch (IOException e)
        {
            System.out.println("Headers not found! "+e.getMessage());
            return null;
        }
    }

    public static String recordHash(CSVRecord csvRecord)
    {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < REQUIRED_DATA_COLUMNS.length; i++)
        {
            String dataColumn = REQUIRED_DATA_COLUMNS[i].replaceAll("\"","");
            stringBuilder.append(csvRecord.get(dataColumn));
            if(i < REQUIRED_DATA_COLUMNS.length - 1)
            {
                stringBuilder.append("_");
            }
        }
        String hashedRecordString = Hashing.sha256().hashString(stringBuilder.toString(),
                StandardCharsets.UTF_8).toString();
        return hashedRecordString;
    }

    public static String recordStringObject(CSVRecord csvRecord)
    {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < REQUIRED_DATA_COLUMNS.length; i++)
        {
            stringBuilder.append(csvRecord.get(REQUIRED_DATA_COLUMNS[i].replaceAll("\"","")));
            if(i < REQUIRED_DATA_COLUMNS.length - 1)
            {
                stringBuilder.append("_");
            }
        }
        return stringBuilder.toString();
    }

    public static String[] createTargetColumns(String[] excludedColumns)
    {
        Set<String> set = new HashSet<>();
        for(String col : getColumns())
        {
            set.add(col);
        }

        for(String rem: excludedColumns)
        {
            set.remove(rem);
        }
        return set.toArray(new String[set.size()]);
    }

    public static String[] createTargetDataColumns(String[] excludedColumns)
    {
        List<String> targetColumns = new ArrayList<>(Arrays.asList(getColumns()));
        System.out.println("Tsize: "+targetColumns.size());
        targetColumns.removeAll(Arrays.asList(excludedColumns));
        System.out.println("Tsize2: "+targetColumns.size());
        String[] targs = targetColumns.toArray(new String[targetColumns.size()]);
        return targs;
    }

    public static String[] getColumns()
    {
        return COLUMNS;
    }

    public static String[] splitStringToArray(String combinedString)
    {
        String[] stringArray = combinedString.split("_");
        String[] objectArray = new String[stringArray.length];
        for (int i = 0; i < stringArray.length; i++)
        {
            String element = stringArray[i];
            if (element.matches("-?\\d+(\\.\\d+)?"))
            { // Check if the element is a number
                if (element.contains("."))
                {
                    objectArray[i] = String.valueOf(Double.parseDouble(element));
                } else
                {
                    objectArray[i] = String.valueOf(Integer.parseInt(element));
                }
            }
            else
            {
                objectArray[i] = element;
            }
        }
        return objectArray;
    }

    public static void writeCSV()
    {

    }
}
