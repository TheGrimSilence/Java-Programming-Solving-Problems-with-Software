import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

/**
 * Write a description of CSVWeather here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CSVWeather {
    /**
     * Finds the lowest or highest of the given category in a single day.
     * 
     * @param parser    The CSVParser handed down.
     * @param high      The temperature basis, if high then we look for the highest value, else the lowest.
     * @param category  The category to be searched, (e.g. 'TemperatureF', 'Humidity').
     * 
     * @return          The CSVRecord handed up to main search method.
     */
    private CSVRecord dualTemperatureOfDay(CSVParser parser, Boolean high, String category) {
        CSVRecord recordHour = null;

        for (CSVRecord currentHour : parser) {
            if (currentHour.get(category) == "-9999" || currentHour.get(category) == "N/A") {
                System.out.println("Invalid value, breaking...");
                break;
            }
            
            recordHour = dualTemperatureComparison(currentHour, recordHour, false, category);
        }

        return recordHour; 
    }

    /**
     * Finds the lowest or highest of the given category over multiple days.
     * 
     * @param high      The temperature basis, if high then we look for the highest value, else the lowest.
     * @param category  The category to be searched, (e.g. 'TemperatureF', 'Humidity').
     * 
     * @return          The CSVRecord handed up to main search method.
     */
    private CSVRecord dualTemperatureOfManyDays(Boolean high, String category) {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord recordDay = null;

        for (File file : dr.selectedFiles()) {
            FileResource fr = new FileResource(file);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentDay = dualTemperatureOfDay(parser, high, category);

            recordDay = dualTemperatureComparison(currentDay, recordDay, high, category);
        }

        return recordDay;
    }

    /**
     * Compares two values of a given category to find the desired value, highest or lowest.
     * 
     * @param currentTemperature    The current value in a record.
     * @param recordTemperature     The record value found so far.
     * @param high                  The temperature basis, if high then we look for the highest value, else the lowest.
     * @param category              The category to be searched, (e.g. 'TemperatureF', 'Humidity').
     */
    private CSVRecord dualTemperatureComparison(CSVRecord currentTemperature, CSVRecord recordTemperature, Boolean high, String category) {
        if (recordTemperature == null) recordTemperature = currentTemperature;
        else {
            double recordDbl = Double.parseDouble(recordTemperature.get(category));
            double currentDbl = Double.parseDouble(currentTemperature.get(category));
            
            if (high) {
                if (currentDbl > recordDbl) recordTemperature = currentTemperature;
            } else {
                if (currentDbl < recordDbl) recordTemperature = currentTemperature;
            }
        }
        
        return recordTemperature;
    }
    
    private void listAllTemperatures(CSVParser parser) {
        for (CSVRecord currentHour : parser) {
            System.out.println(currentHour.get("DateUTC") + ": " + currentHour.get("TemperatureF"));
        }
    }
    
    private String fileWithColdestTemperature() {
        DirectoryResource directory = new DirectoryResource();
        CSVRecord coldestHour = null;
        String fileName = "None";
        CSVParser _parser = null;
        
        for (File file : directory.selectedFiles()) {
            FileResource fr = new FileResource(file);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentHour = dualTemperatureOfDay(parser, false, "TemperatureF");
            
            coldestHour = dualTemperatureComparison(currentHour, coldestHour, false, "TemperatureF");
           
            fileName = file.getName();
            _parser = fr.getCSVParser();
        }
        
        System.out.println(coldestHour.get("TemperatureF"));
        listAllTemperatures(_parser);
        return fileName;
    }
    
    public void testLowesetHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = dualTemperatureOfDay(parser, false, "Humidity");
        
        System.out.println("Lowest humidity recorded: " + csv.get("Humidity"));
    }
    
    public void testFileWithColdestTemp() {
        String coldestDay = fileWithColdestTemperature();
        System.out.println(coldestDay);
    }
    
    
    public void testColdestHourInFile() {
        FileResource fileResource = new FileResource();
        CSVParser parser = fileResource.getCSVParser();
        
        
        System.out.println("Coldest recorded temperature: " + dualTemperatureOfDay(parser, false, "TemperatureF").get("TemperatureF"));
    }

    public void testTemps() {
        FileResource fr = new FileResource("hottestTemp/data/2015/weather-2015-01-01.csv");
        CSVRecord hottest = dualTemperatureOfDay(fr.getCSVParser(), true, "TemperatureF");

        System.out.println("The hottest temperature recorded on January 1st of 2015 was "
            + hottest.get("TemperatureF") + "°F at " + hottest.get("TimeEST") + ".");

        CSVRecord hottestOverTime = dualTemperatureOfManyDays(true, "TemperatureF");
        System.out.println("In addition, the hottest temperature recorded over the week of January 1st, was "
            + hottestOverTime.get("TemperatureF") + "°F at " + hottestOverTime.get("DateUTC") + ", Dr. Hayden.");
    }
}
