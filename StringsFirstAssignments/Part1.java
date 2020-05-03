/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene(String dna) {
        // If -1 then return ""
        String result = "";
        int start_codon = dna.indexOf("ATG");
        // If -1 then return ""
        int stop_codon = dna.indexOf("TAA",start_codon + 3);
        
        if (start_codon == -1 && stop_codon == -1) {
            System.out.println("No Gene sequence found!");
        } else if (start_codon == -1) {
            System.out.println("Start codon missing!");
            return "";
        } 
        
        if (stop_codon == -1) {
            System.out.println("Stop codon missing!");
            return "";
        }

        result = dna.substring(start_codon, stop_codon + 3);

        if (result.length() % 3 == 0) {
            System.out.println("Sequence is valid!");
            return result;
        }
        else {
            System.out.println("Invalid sequence!");
            return "";
        }
    }

    public void testSimpleGene() {
        // DNA valid (start, stop, multiple 3)
        String dna = "GACTAGTATGGGAAGTGTGGCTAGATCATTTGCTCACGCATCTAGTCGGTCCACGTTTGGTTTTTAAGATCCAATGATCTTCAAAACGCTGCAAGATTCTCAA";
        System.out.println("DNA strand is " + dna);
        String gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        // DNA no start_codon
        dna = "GACTAGTATTGAAGTGTGGCTAGATCTTTGCTCACGCATCTAGTCGGTCCACGTTTGGTTTTTAAGATCCAATTATCTTCAAAACGCTGCAAGATTCTCAA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        // DNA no stop_codon
        dna = "GACTAGTATGGAAGTGTGGCTAGATCTTTGCTCACGCATCTAGTCGGTCCACGTTTGGTTTTTAGGATCCAATTATCTTCAAAACGCTGCAAGATTCTCAA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        // DNA no start_codon or stop_codon
        dna = "GACTAGTATTGAAGTGTGGCTAGATCTTTGCTCACGCATCTAGTCGGTCCACGTTTGGTTTTTAGGATCCAATTATCTTCAAAACGCTGCAAGATTCTCAA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        // DNA no not valid (start, stop, not multiple 3)
        dna = "GACTAGTATGGGAAGTGTGGCTAGATCTTTGCTCACGCATCTAGTCGGTCCACGTTTGGTTTTTAAGATCCAATTATCTTCAAAACGCTGCAAGATTCTCAA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
    }
}
