/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna, String start_codon, String stop_codon) {
        // If -1 then return ""
        String result = "";
        int strt_codon = dna.indexOf(start_codon);
        // If -1 then return ""
        int stp_codon = dna.indexOf(stop_codon,strt_codon + 3);
        
        if (strt_codon == -1 && stp_codon == -1) {
            System.out.println("No Gene sequence found!");
            return "";
        } else if (strt_codon == -1) {
            System.out.println("Start codon missing!");
            return "";
        } else if (stp_codon == -1) {
            System.out.println("Stop codon missing!");
            return "";
        }
        
        // I didn't see reasoning to do the UP -> UP, lower -> lower challenge.

        result = dna.substring(strt_codon, stp_codon + 3);

        if (result.length() % 3 == 0) {
            System.out.println("Sequence is valid!");
            return result.toUpperCase();
        } else {
            System.out.println("Invalid sequence!");
            return "";
        }
    }

    public void testSimpleGene() {
        // DNA valid (start, stop, multiple 3)
        String dna = "GACTAGTATGGGAAGTGTGGCTAGATCATTTGCTCACGCATCTAGTCGGTCCACGTTTGGTTTTTAAGATCCAATGATCTTCAAAACGCTGCAAGATTCTCAA";
        System.out.println("DNA strand is " + dna);
        String gene = findSimpleGene(dna, "ATG", "TAA");
        System.out.println("Gene is " + gene);
        
        // DNA valid but lowercase? (start, stop, multiple 3)
        dna = "gaCTAGTATGGGAAGTGTGGCTAGATCATTTGCTCACGCATCTAGTCGGTCCACGTTTGGTTTTTAAGATCCAATGATCTTCAAAACGCTGCAAGATTCTCAA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna, "ATG", "TAA");
        System.out.println("Gene is " + gene);
        
        // DNA no start_codon
        dna = "GACTAGTATTGAAGTGTGGCTAGATCTTTGCTCACGCATCTAGTCGGTCCACGTTTGGTTTTTAAGATCCAATTATCTTCAAAACGCTGCAAGATTCTCAA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna, "ATG", "TAA");
        System.out.println("Gene is " + gene);
        
        // DNA no stop_codon
        dna = "GACTAGTATGGAAGTGTGGCTAGATCTTTGCTCACGCATCTAGTCGGTCCACGTTTGGTTTTTAGGATCCAATTATCTTCAAAACGCTGCAAGATTCTCAA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna, "ATG", "TAA");
        System.out.println("Gene is " + gene);
        
        // DNA no start_codon or stop_codon
        dna = "GACTAGTATTGAAGTGTGGCTAGATCTTTGCTCACGCATCTAGTCGGTCCACGTTTGGTTTTTAGGATCCAATTATCTTCAAAACGCTGCAAGATTCTCAA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna, "ATG", "TAA");
        System.out.println("Gene is " + gene);
        
        // DNA no not valid (start, stop, not multiple 3)
        dna = "GACTAGTATGGGAAGTGTGGCTAGATCTTTGCTCACGCATCTAGTCGGTCCACGTTTGGTTTTTAAGATCCAATTATCTTCAAAACGCTGCAAGATTCTCAA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna, "ATG", "TAA");
        System.out.println("Gene is " + gene);
    }
}
