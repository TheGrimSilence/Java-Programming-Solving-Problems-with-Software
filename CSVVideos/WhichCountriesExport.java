import edu.duke.*;
import org.apache.commons.csv.*;

/**
 * Write a description of WhichCountriesExport here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WhichCountriesExport {
    public void listExporters(CSVParser parser, String exportOfInterest) {
        // for each row in the CSV file
        for (CSVRecord record : parser) {
            // Look at the "exports" column
            String export = record.get("Exports");
            // Check if it contains exportOfInterest
            if (export.contains(exportOfInterest)) {
                // If so, write down the Country from that row
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    
    public void testListExporters() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        listExporters(parser, "coffee");
    }
}
