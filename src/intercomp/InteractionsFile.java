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
 *
 * @author Alexander
 */
public class InteractionsFile {

    private final String path;
    private List<Interaction> interactions;
    private Set<String> genesASet;
    private Set<String> genesBSet;
    private Set<String> typesSet;

    public InteractionsFile(String path) throws FileNotFoundException, IOException, IndexOutOfBoundsException {
        this.path = path;
        readFile();
        interactions.sort(null);
        createLists();
    }

    public String getPath() {
        return path;
    }

    public List<Interaction> getInteractions() {
        return interactions;
    }

    public Set<String> getGenesA() {
        return genesASet;
    }

    public Set<String> getGenesB() {
        return genesBSet;
    }

    public Set<String> getTypes() {
        return typesSet;
    }

    public String getStatsText() {
        String stats = "";
        stats = stats.concat("Tax ID 1:               \t" + Integer.toString(genesASet.size()) + " genes\n");
        stats = stats.concat("Tax ID 2:               \t" + Integer.toString(genesBSet.size()) + " genes\n");
        stats = stats.concat("# Interactions:         \t" + Integer.toString(interactions.size()) + " interactions\n");
        stats = stats.concat("# Types of interactions:\t" + Integer.toString(typesSet.size()) + " interactions");
        return stats;
    }

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

    private void createLists() {
        genesASet = new HashSet<>();
        genesBSet = new HashSet<>();
        typesSet = new HashSet<>();
        for (Interaction i : interactions) {
            genesASet.add(i.getGeneA().getGeneID());
            genesBSet.add(i.getGeneB().getGeneID());
            typesSet.add(i.getInteraction());
        }
    }

}
