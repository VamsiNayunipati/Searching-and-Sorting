import java.io.*;
import java.util.*;

public class ReadAndSearch
{
    public static void main(String[] args)
    {
        String file = "myFile.txt";
        String[] data = {"Kalahari", "Gobi", "Sahara", "Kyzyl", "Mojave", "Karakum", "Great Basin", "Taklamakan"};
        writeFile(file, data);
        String[] filteredData = readNFilterFile(file);

        System.out.println("Filtered Data: " + Arrays.toString(filteredData));
        
        String[] updatedData = Arrays.copyOf(filteredData, filteredData.length + 2);
        
        updatedData[updatedData.length - 2] = "Thar";
        updatedData[updatedData.length - 1] = "Namib";
        
        System.out.println("Updated Data: " + Arrays.toString(updatedData));
        
        writeFile(file, updatedData);
        
        Arrays.sort(updatedData);
        System.out.println("Sorted Data for performing binary Search: " + Arrays.toString(updatedData));
        
        searchAndPrintResult(updatedData, "Gobi");
        searchAndPrintResult(updatedData, "Kalahari");
    }

    private static void writeFile(String file, String[] info)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file)))
        {
            for (String data : info)
            {
                writer.write(data);
                writer.newLine();
            }
        }
        
        catch (IOException e)
        {
            System.out.println("Error while writing to the file: " + e.getMessage());
        }
    }

    private static String[] readNFilterFile(String file)
    {
        List<String> filteredData = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String element;
            while ((element = reader.readLine()) != null)
            {
                if (element.startsWith("G") || element.startsWith("K"))
                {
                    filteredData.add(element);
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("Error while reading from the file: " + e.getMessage());
        }

        return filteredData.toArray(new String[0]);
    }

    private static void searchAndPrintResult(String[] list, String target)
    {
        int index = binarySearch(list, target);

        if (index >= 0)
        {
            System.out.println("Found element '" + target + "' at the index: " + index);
        }
        else
        {
            System.out.println("'" + target + "' not found in the list.");
        }
    }

    private static int binarySearch(String[] array, String target)
    {
        int left = 0;
        int right = array.length - 1;

        while (left <= right)
        {
            int mid = left + (right - left) / 2;
            int comparison = array[mid].compareTo(target);

            if (comparison == 0)
            {
                return mid;
            }
            else if (comparison < 0)
            {
                left = mid + 1;
            }
            else
            {
                right = mid - 1;
            }
        }

        return -1;
    }
}
