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

        listExporters(parser, "fish");
    }

    // Programming Exercise: Parsing Export Data
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        System.out.println("countryInfo");
        countryInfo(parser, "Nauru");
        
        System.out.println("listExportersTwoProducts");
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "fish", "nuts");
        
        System.out.println("numberOfExporters");
        parser = fr.getCSVParser();
        numberOfExporters(parser, "sugar");
        
        System.out.println("bigExporters");
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
    }

    public void countryInfo(CSVParser parser, String country) {
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            String value = record.get("Value (dollars)");

            if (exports.isEmpty()) {
                System.out.println("NOT FOUND");
            } else {
                System.out.println(String.format("%s: %s: %s", country, exports, value));
                System.out.println();
                return; 
            }
        }
        System.out.println();
    }

    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {
            String export = record.get("Exports");

            if (export.contains(exportItem1) && export.contains(exportItem2)) {
                String country = record.get("Country");

                System.out.println(country);
            } 
        }
        System.out.println();
    }

    public void numberOfExporters(CSVParser parser, String exportItem) {
        int numberOfCountries = 0;

        for (CSVRecord record : parser) {
            String export = record.get("Exports");

            if (export.contains(exportItem)) {
                numberOfCountries = numberOfCountries + 1;
            }
        }

        System.out.println(numberOfCountries);
        System.out.println();
    }

    public void bigExporters(CSVParser parser, String amount) {
        if (!amount.contains("$")) {
            System.out.println("Must indlude the '$'");

            return;
        } else if (!amount.contains(",")) {
            System.out.println("Must indlude the ',' between numerical steps.");

            return;
        }

        for (CSVRecord record : parser) {
            String country = record.get("Country");
            String value = record.get("Value (dollars)");

            if (amount.length() < value.length()) {
                System.out.println(String.format("%s: %s", country, value));
            }
        }
        System.out.println();
    }
}
