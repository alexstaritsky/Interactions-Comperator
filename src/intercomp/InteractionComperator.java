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
 *
 * @author Alexander
 */
public class InteractionComperator {

    private String typeA;
    private String typeB;
    private List<Interaction> interactions;

    private Set<Gene> genesA;
    private Set<Gene> intersection;
    private Set<Gene> genesB;

    /**
     * Constructor for an new InteractionComperator with all fields.
     *
     * @param typeA the first type of interactions for the first group of genes.
     * @param typeB the second type of interactions for the second group of genes.
     * @param interactions a List containing all Interaction objects in the interaction file.
     */
    public InteractionComperator(String typeA, String typeB, List<Interaction> interactions) {
        this.typeA = typeA;
        this.typeB = typeB;
        this.interactions = interactions;
        compare();
    }

    /**
     * Returns the first type of interactions to compare.
     *
     * @return the first type of interactions from the first group of genes.
     */
    public String getTypeA() {
        return typeA;
    }

    /**
     * Returns the second type of interactions to compare.
     *
     * @return the second type of interactions from the second group of genes.
     */
    public String getTypeB() {
        return typeB;
    }

    /**
     * Returns all the interactions that can be compared in this comperator.
     *
     * @return a List containing all the Interaction objects that can be compared.
     */
    public List<Interaction> getInteractions() {
        return interactions;
    }

    /**
     * Returns all the genes from group A containing typeA interactions.
     *
     * @return a Set of Gene objects that contain typeA interactions.
     */
    public Set<Gene> getGenesA() {
        return genesA;
    }

    /**
     * Returns all the genes that have a interaction in group A and a interaction in group B (intersection).
     *
     * @return a Set of Gene objects that have a Interaction in group A and a Interaction in group B (intersection).
     */
    public Set<Gene> getGenesIntersection() {
        return intersection;
    }

    /**
     * Returns all the genes from group B containing typeB interactions.
     *
     * @return a Set of Gene objects that contain typeB interactions.
     */
    public Set<Gene> getGenesB() {
        return genesB;
    }

    /**
     * Sets the first type of interaction for group A.
     *
     * @param type the first type of interactions for the first group of genes.
     */
    public void setTypeA(String type) {
        this.typeA = type;
    }

    /**
     * Sets the second type of interaction for group B.
     *
     * @param type the second type of interactions for the first group of genes.
     */
    public void setTypeB(String type) {
        this.typeB = type;
    }

    /**
     * Sets the list of interactions that can be compared in this object.
     *
     * @param interactions a List containing Interaction objects that can be compared in this object.
     */
    public void setInteractions(List<Interaction> interactions) {
        this.interactions = interactions;
    }

    /**
     * Creates two sets of genes and a set of the intersection.
     * <p>
     * Creates two Sets with Genes that have an interaction equal to the type specified for their group. The two sets are compared with the retainAll function and the result of the intersection is stored in 'intersection'. Stores the sets in this object.
     */
    public void compare() {
        String type;
        genesA = new HashSet<>();
        genesB = new HashSet<>();
        for (Interaction i : interactions) {
            if (i.getType().equals(typeA) & i.getType().equals(typeB)) {
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

    /**
     * Returns a set with all the unique PubMed identifiers linked to the genes in the intersection.
     *
     * @return a String Set containing all unique PubMed identifiers belonging to the Gene objects in 'intersection'.
     */
    private Set<String> getIdentifiers() {
        Set<String> identifiers = new HashSet<>();
        for (Interaction i : interactions) {
            for (Gene g : intersection) {
                if (i.getGeneB().equals(g)) {
                    for (String id : i.getPubMedID().split(",")) {
                        identifiers.add(id);
                    }
                }
            }
        }
        return identifiers;
    }

    /**
     * Exports all information about genes in the intersection in a tab-delimited text file.
     * <p>
     * Exports a header beginning with '#' and the names of the columns in a tab-delimited fashion. Exports each gene on a line with tab-delimited values.
     *
     * @param path a String absolute path to a file.
     * @throws FileNotFoundException when the file specified in 'path' can not be found.
     * @throws IOException when there is a problem with IO in the file.
     * @throws NullExportException when there are no genes to export.
     */
    public void exportGenes(String path) throws FileNotFoundException, IOException, NullExportException {
        if (!intersection.isEmpty()) {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(path))));
            writer.write("#Tax ID\tGene ID\tProduct accession version\tProduct name");
            for (Gene g : intersection) {
                writer.write(String.format("\n%s\t%s\t%s\t%s", g.getTaxID(), g.getGeneID(), g.getAccessionVersion(), g.getProductName()));
            }
            writer.close();
        } else {
            throw new NullExportException("No genes to export!");
        }
    }

    /**
     * Exports all PubMed identifiers associated with the genes in the intersection in a line-delimited text file.
     * <p>
     * Exports a header beginning with '#PubMed ID (PMID)'. Exports each PubMed identifier per line.
     *
     * @param path a String absolute path to a file.
     * @throws FileNotFoundException when the file specified in 'path' can not be found.
     * @throws IOException when there is a problem with IO in the file.
     * @throws NullExportException when there are no genes to export.
     */
    public void exportPubMed(String path) throws FileNotFoundException, IOException, NullExportException {
        Set<String> identifiers = getIdentifiers();
        if (!intersection.isEmpty() && !identifiers.isEmpty()) {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(path))));
            writer.write("#PubMed ID (PMID)");
            for (String id : identifiers) {
                writer.write("\n" + id);
            }
            writer.close();
        } else {
            throw new NullExportException("No PubMed identifiers to export!");
        }
    }

}
