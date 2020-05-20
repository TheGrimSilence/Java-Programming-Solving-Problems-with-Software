import edu.duke.*;
import org.apache.commons.csv.*;

/**
 * Write a description of BabyNames here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BabyNames {
    public void readOneFile(int year) {
        String firstName = "data/yob" + year + ".txt";
        CSVParser parser = new FileResource(firstName).getCSVParser(false);
        
        for (CSVRecord record : parser) {
            String name = record.get(0);
            String gender = record.get(1);
            String count = record.get(3);
        }
    }
}
