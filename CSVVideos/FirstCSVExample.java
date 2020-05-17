import edu.duke.*;
import org.apache.commons.csv.*;

/**
 * Write a description of FirstCSVExample here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FirstCSVExample {
    public void readFood() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        for (CSVRecord record : parser) {
            System.out.println(record.get("Name"));
        }
    }
}
