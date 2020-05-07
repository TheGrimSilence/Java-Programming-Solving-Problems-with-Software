/**
 * Write a description of FindGeneWhile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FindGeneWhile {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currentIndex = dna.indexOf(stopCodon, startIndex + 3);
        
        while(currentIndex != -1) {
            if ((currentIndex - startIndex) % 3 == 0) {
                System.out.println("Current Index: " + currentIndex);
                return currentIndex;
            } else {
                currentIndex = dna.indexOf(stopCodon, currentIndex + 1);
            }
        }
        
        return -1;
    }
    
    public String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = 0;
        
        if (startIndex == -1) {
            return "";
        }
        
        if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)) {
            minIndex = tgaIndex;
        } else {
            minIndex = taaIndex;
        }
        
        if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)) {
            minIndex = tagIndex;
        } 
        
        if (minIndex == -1) {
            return "";
        }
        
        String gene = dna.substring(startIndex, minIndex + 3);
        
        // This ensures we don't get a sequence of ATGTAA on accident.
        if (gene.length() > 6) {
            return gene;
        } else {
            return "";
        }
    }
    
    public void printAllGenes(String dna) {
        int startIndex = 0;
        
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        
    
    }
    
    public void testSimpleGene() {
        // DNA valid (start, stop, multiple 3)
        String dna = "GACTAGTATGGGAAGTGTGGCTAGATCATTTGCTCACGCATCTAGTCGGTCCACGTTTGGTTTTTAAGATCCAATGATCTTCAAAACGCTGCAAGATTCTCAA";
        System.out.println("DNA strand is " + dna);
        String gene = findGene(dna, 0);
        System.out.println("Gene is " + gene);
        
        // DNA valid but lowercase? (start, stop, multiple 3)
        dna = "gaCTAGTATGGGAAGTGTGGCTAGATCATTTGCTCACGCATCTAGTCGGTCCACGTTTGGTTTTTAAGATCCAATGATCTTCAAAACGCTGCAAGATTCTCAA";
        System.out.println("DNA strand is " + dna);
        gene = findGene(dna, 0);
        System.out.println("Gene is " + gene);
        
        // DNA no start_codon
        dna = "GACTAGTATTGAAGTGTGGCTAGATCTTTGCTCACGCATCTAGTCGGTCCACGTTTGGTTTTTAAGATCCAATTATCTTCAAAACGCTGCAAGATTCTCAA";
        System.out.println("DNA strand is " + dna);
        gene = findGene(dna, 0);
        System.out.println("Gene is " + gene);
        
        // DNA no stop_codon
        dna = "GACTAGTATGGAAGTGTGGCTAGATCTTTGCTCACGCATCTAGTCGGTCCACGTTTGGTTTTTAGGATCCAATTATCTTCAAAACGCTGCAAGATTCTCAA";
        System.out.println("DNA strand is " + dna);
        gene = findGene(dna, 0);
        System.out.println("Gene is " + gene);
        
        // DNA no start_codon or stop_codon
        dna = "GACTAGTATTGAAGTGTGGCTAGATCTTTGCTCACGCATCTAGTCGGTCCACGTTTGGTTTTTAGGATCCAATTATCTTCAAAACGCTGCAAGATTCTCAA";
        System.out.println("DNA strand is " + dna);
        gene = findGene(dna, 0);
        System.out.println("Gene is " + gene);
        
        // DNA no not valid (start, stop, not multiple 3)
        dna = "GACTAGTATGGGAAGTGTGGCTAGATCTTTGCTCACGCATCTAGTCGGTCCACGTTTGGTTTTTAAGATCCAATTATCTTCAAAACGCTGCAAGATTCTCAA";
        System.out.println("DNA strand is " + dna);
        gene = findGene(dna, 0);
        System.out.println("Gene is " + gene);
    }
    
    public void testFindGene() {
        String dna = "GACTAGTATGGGAAGTGTGGCTAGATCATTTGCTCACGCATCTAGTCGGTCCACGTTTGGTTTTTAAGATCCAATGATCTTCAAAACGCTGCAAGATTCTCAA";
        String gene = findGene(dna, 0);
        
        if (! gene.equals("ATGGGAAGTGTGGCTAGATCATTTGCTCACGCATCTAGTCGGTCCACGTTTGGTTTTTAA")) {
            System.out.println("[ ERROR ]");
        } else {
            System.out.println("[ SUCCESS ]");
        }
        
        System.out.println("Tests finished");
    }
    
    public void testPrintAllGenes() {
//        String dna = "TTTGAAATTATATTATTTCAAAAGGAAAGCACTAATGGTCTTTTTTTCTGATGACTTAACTCGTAAAGATCATGAAATTGATTCTTTCAATAGTTAAAAATCAAAAATTCACTATGTAAACTGAAGCATCTATCTAACGGTTTGTATCTCGAATACTTAGTCTCTTTTGTTATTCCGGATAAATTCATACCCCTTATTCA";
        String dna = "AATGCTAACTAGCTGACTAAT";
        System.out.println("Testing printAllGenes() on " + dna);
        printAllGenes(dna);
        
        System.out.println("Test finished");
    }
}
