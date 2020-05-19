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
    public CSVRecord hottestHourInFile(CSVParser parser) {
        CSVRecord hottestHour = null;
        
        for (CSVRecord record : parser) {
            if (hottestHour == null) {
                hottestHour = record;
            } else {
                double currentRow = Double.parseDouble(record.get("TemperatureF"));
                double currentTemp = Double.parseDouble(hottestHour.get("TemperatureF"));
                
                if (currentTemp < currentRow) hottestHour = record;
            }
        }
        
        return hottestHour; 
    }
    
    public CSVRecord hottestOfMultipleDays() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord hottestDay = null;
        
        for (File file : dr.selectedFiles()) {
            FileResource fr = new FileResource(file);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentDay = hottestHourInFile(parser);
            
            if (hottestDay == null) hottestDay = currentDay;
            else {
                double hottest = Double.parseDouble(hottestDay.get("TemperatureF"));
                double current = Double.parseDouble(currentDay.get("TemperatureF"));
                
                if (current > hottest) hottestDay = currentDay;
            }
        }
        
        return hottestDay;
    }
    
    public void testTemps() {
        FileResource fr = new FileResource("hottestTemp/data/2015/weather-2015-01-01.csv");
        CSVRecord hottest = hottestHourInFile(fr.getCSVParser());
        
        System.out.println("The hottest temperature recorded on January 1st of 2015 was "
        + hottest.get("TemperatureF") + "°F at " + hottest.get("TimeEST") + ".");
        
        CSVRecord hottestOverTime = hottestOfMultipleDays();
        System.out.println("In addition, the hottest temperature recorded over the week of January 1st, was "
        + hottestOverTime.get("TemperatureF") + "°F at " + hottestOverTime.get("DateUTC") + ", Dr. Hayden.");
    }
}
