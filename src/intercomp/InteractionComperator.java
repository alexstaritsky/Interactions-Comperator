package intercomp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class to compare sets of genes that belong to a interaction. 
 * @author Alexander
 */
public class InteractionComperator {

    private String typeA;
    private String typeB;
    private List<Interaction> interactions;

    private Set<Gene> genesA;
    private Set<Gene> intersection;
    private Set<Gene> genesB;

    public InteractionComperator(String typeA, String typeB, List<Interaction> interactions) {
        this.typeA = typeA;
        this.typeB = typeB;
        this.interactions = interactions;
        compare();
    }

    public String getTypeA() {
        return typeA;
    }

    public String getTypeB() {
        return typeB;
    }

    public List<Interaction> getInteractions() {
        return interactions;
    }

    public Set<Gene> getGenesA() {
        return genesA;
    }

    public Set<Gene> getGenesIntersection() {
        return intersection;
    }

    public Set<Gene> getGenesB() {
        return genesB;
    }

    public void setTypeA(String type) {
        this.typeA = type;
    }

    public void setTypeB(String type) {
        this.typeB = type;
    }

    public void setInteractions(List<Interaction> interactions) {
        this.interactions = interactions;
    }

    public void compare() {
        String type;
        genesA = new HashSet<>();
        genesB = new HashSet<>();
        for (Interaction i : interactions) {
            if (i.getType().equals(typeA) && i.getType().equals(typeB)) {
                genesA.add(i.getGeneB());
                genesB.add(i.getGeneB());
            } else if (i.getType().equals(typeA)) {
                genesA.add(i.getGeneB());
            } else if (i.getType().equals(typeB)) {
                genesB.add(i.getGeneB());
            }
        }
        intersection = new HashSet<>(genesA);
        intersection.retainAll(genesB);
    }

    private Set<String> getIdentifiers() {
        Set<String> identifiers = new HashSet<>();
        for (Interaction i : interactions) {
            for (Gene g : intersection) {
                if (i.getGeneB().equals(g)) {
                    identifiers.add(i.getPubmedID());
                }
            }
        }
        return identifiers;
    }

    public void exportGenes(String path) throws FileNotFoundException, IOException, NullExportException {
        if (!intersection.isEmpty()) {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(path))));
            writer.write("#Tax ID 2\tGene ID 2\tproduct accession.version 2\tproduct name 2");
            for (Gene g : intersection) {
                writer.write(String.format("\n%s\t%s\t%s\t%s", g.getTaxID(), g.getGeneID(), g.getAccessionVersion(), g.getProductName()));
            }
            writer.close();
        } else {
            throw new NullExportException("No genes to export!");
        }
    }

    public void exportPubMed(String path) throws FileNotFoundException, IOException, NullExportException {
        Set<String> identifiers = getIdentifiers();
        if (!intersection.isEmpty() && !identifiers.isEmpty()) {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(path))));
            writer.write("#PubMed ID (PMID) list");
            for (String id : identifiers) {
                writer.write("\n" + id);
            }
            writer.close();
        } else {
            throw new NullExportException("No PubMed identifiers to export!");
        }
    }

}
