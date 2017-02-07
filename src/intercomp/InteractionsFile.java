package intercomp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class representing a file with (multiple) gene(s) and the interactions between them.
 *
 * @author Alexander
 */
public class InteractionsFile {

    private final String path;
    private List<Interaction> interactions;
    private Set<Gene> genesASet;
    private Set<Gene> genesBSet;
    private Set<String> typesSet;

    /**
     * Constructor for reading a file with interactions and storing the information in this object.
     *
     * @param path a absolute path to a file containing the interactions.
     * @throws FileNotFoundException when the specified file on the path is not found.
     * @throws IOException when there is something wrong with IO of the file.
     * @throws IndexOutOfBoundsException when the file format is wrong. The format should be 11 tab-delimited values for each line, except lines that start with '#'.
     */
    public InteractionsFile(String path) throws FileNotFoundException, IOException, IndexOutOfBoundsException {
        this.path = path;
        readFile();
        interactions.sort(null);
        createLists();
    }

    /**
     * Returns the path of the file with interactions.
     *
     * @return a String absolute path to a file containing the interactions.
     */
    public String getPath() {
        return path;
    }

    /**
     * Returns a list with all interactions contained in the interactions file.
     *
     * @return a List containing every interaction in the file.
     */
    public List<Interaction> getInteractions() {
        return interactions;
    }

    /**
     * Returns a set with all the genes from the first taxonomy identifier in the interactions file.
     *
     * @return a String Set with all the genes from the first taxonomy identifier in the file.
     */
    public Set<Gene> getGenesA() {
        return genesASet;
    }

    /**
     * Returns a set with all the genes from the second taxonomy identifier in the interactions file.
     *
     * @return a String Set with all the genes from the second taxonomy identifier in the file.
     */
    public Set<Gene> getGenesB() {
        return genesBSet;
    }

    /**
     * Returns a set with all types of interactions in the interactions file.
     *
     * @return a String Set with all the types of interactions in the file.
     */
    public Set<String> getTypes() {
        return typesSet;
    }

    /**
     * Returns four statistics about the interactions file.
     * <p>
     * A first statistic is a number of unique gene identifiers from the first taxonomy id. A second statistic is a number of unique gene identifiers from the second taxonomy id. A third statistic is a number of interactions in the file. The last statistic is a number of unique types in the file.
     *
     * @return a String containing a text summary of the four statistics.
     */
    public String getStatsText() {
        String stats = "";
        stats = stats.concat("Tax ID 1:               \t" + Integer.toString(genesASet.size()) + " genes\n");
        stats = stats.concat("Tax ID 2:               \t" + Integer.toString(genesBSet.size()) + " genes\n");
        stats = stats.concat("# Interactions:         \t" + Integer.toString(interactions.size()) + " interactions\n");
        stats = stats.concat("# Types of interactions:\t" + Integer.toString(typesSet.size()) + " interactions");
        return stats;
    }

    /**
     * Reads a file with the object's path, creates Gene objects and encapsulates them in Interaction objects.
     * <p>
     * Opens a file with the path specified in this object. Tries to split the data of each line into 11 values and assign them to two Gene objects; one object with taxonomy identifier one and one with taxonomy identifier two. The two Gene objects are encapsulated in an Interaction object with the rest of the information in the line.
     *
     * @throws FileNotFoundException when the file at the path saved in this object is not found.
     * @throws IOException when there is something wrong with IO of the file.
     * @throws IndexOutOfBoundsException when the file format is wrong. The format should be 11 tab-delimited values for each line, except lines that start with '#'.
     */
    private void readFile() throws FileNotFoundException, IOException, IndexOutOfBoundsException {
        String line;
        BufferedReader file = new BufferedReader(new FileReader(path));
        interactions = new ArrayList<>();
        while ((line = file.readLine()) != null) {
            if (!line.startsWith("#") & line != "") {
                String[] data = line.split("\t");
                Gene geneA = new Gene(data[1], data[0], data[2], data[3]);
                Gene geneB = new Gene(data[6], data[5], data[7], data[8]);
                interactions.add(new Interaction(geneA, geneB, data[9], data[4], data[10], data[11]));
            }
        }
        file.close();
    }

    /**
     * Uses the data from the interactions list to form three sets of data.
     * <p>
     * Uses the data from the interactions list to form a Set with the genes from taxonomy identifier one, a Set with the genes from taxonomy identifier two and a Set with all the kinds of interaction types. The sets are stored in three seperated variables; genesASet, genesBSet and typesSet.
     */
    private void createLists() {
        genesASet = new HashSet<>();
        genesBSet = new HashSet<>();
        typesSet = new HashSet<>();
        for (Interaction i : interactions) {
            genesASet.add(i.getGeneA());
            genesBSet.add(i.getGeneB());
            typesSet.add(i.getType());
        }
    }

}
