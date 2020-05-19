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

        for (CSVRecord currentHour : parser) {
            hottestHour = largestOfTwoTemps(currentHour, hottestHour);
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

            hottestDay = largestOfTwoTemps(currentDay, hottestDay);
        }

        return hottestDay;
    }

    public CSVRecord largestOfTwoTemps(CSVRecord currentTemp, CSVRecord hottestTemp) {
        if (hottestTemp == null) hottestTemp = currentTemp;
        else {
            double hottest = Double.parseDouble(hottestTemp.get("TemperatureF"));
            double current = Double.parseDouble(currentTemp.get("TemperatureF"));

            if (current > hottest) hottestTemp = currentTemp;
        }
        
        return hottestTemp;
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
