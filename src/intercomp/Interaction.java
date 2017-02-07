package intercomp;

/**
 * Interaction class to describe an interaction between two genes.
 *
 * @author Alexander
 */
public class Interaction implements Comparable<Interaction> {

    private Gene geneA;
    private Gene geneB;
    private String pubmedID;
    private String type;
    private String lastUpdate;
    private String geneRIF;

    /**
     * Constructor for creating a Interaction object with all internal fields.
     *
     * @param geneA the first Gene object involved with the interaction.
     * @param geneB the second Gene object involved with the interaction.
     * @param pubmedID the PubMed identifier linked to the interaction between the two genes.
     * @param type the type of interaction between the two genes.
     * @param lastUpdate string representation of the last update timestamp.
     * @param geneRIF (small) text description of the interaction between the two genes.
     */
    public Interaction(Gene geneA, Gene geneB, String pubmedID, String type, String lastUpdate, String geneRIF) {
        this.geneA = geneA;
        this.geneB = geneB;
        this.pubmedID = pubmedID;
        this.type = type;
        this.lastUpdate = lastUpdate;
        this.geneRIF = geneRIF;
    }

    /**
     * Returns the first gene involved with the interaction.
     *
     * @return the first Gene object involved with the interaction.
     */
    public Gene getGeneA() {
        return geneA;
    }

    /**
     * Returns the second gene involved with the interaction.
     *
     * @return the second Gene object involved with the interaction.
     */
    public Gene getGeneB() {
        return geneB;
    }

    /**
     * Returns the PubMed identifier associated with the interaction.
     *
     * @return the PubMed identifier linked to the interaction between the two genes.
     */
    public String getPubMedID() {
        return pubmedID;
    }

    /**
     * Returns the PubMed identifier associated with the interaction.
     *
     * @return the PubMed identifier linked to the interaction between the two genes.
     */
    public String getType() {
        return type;
    }

    /**
     * Returns last update timestamp of the information contained in this object.
     *
     * @return a String representation of the latest update timestamp.
     */
    public String getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Returns a geneRIF text describing the interaction (in short).
     *
     * @return a String text describing the interaction (in short).
     */
    public String getGeneRIF() {
        return geneRIF;
    }

    /**
     * Sets the first gene involved with the interaction.
     *
     * @param geneA the first Gene object involved with the interaction.
     */
    public void setGeneA(Gene geneA) {
        this.geneA = geneA;
    }

    /**
     * Sets the second gene involved with the interaction.
     *
     * @param geneB the second Gene object involved with the interaction.
     */
    public void setGeneB(Gene geneB) {
        this.geneB = geneB;
    }

    /**
     * Sets the PubMed identifier associated with the interaction.
     *
     * @param pubmedID the PubMed identifier linked to the interaction between the two genes.
     */
    public void setPubMedID(String pubmedID) {
        this.pubmedID = pubmedID;
    }

    /**
     * Sets the type of the interaction between the two genes.
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Sets the latest update timestamp of the information in the object.
     *
     * @param lastUpdate
     */
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Sets the geneRIF text description of the interaction.
     *
     * @param geneRIF
     */
    public void setGeneRIF(String geneRIF) {
        this.geneRIF = geneRIF;
    }

    /**
     * Compares two types from two interactions lexicographically.
     *
     * @param other the Interaction to be compared.
     * @return the value 0 if the type from the argument Interaction is equal to this gene identifier; a value less than 0 if this type is lexicographically less than the type of the Interaction in the argument; and a value greater than 0 if this type is lexicographically greater than the Interaction identifier from the argument.
     */
    @Override
    public int compareTo(Interaction other) {
        return type.compareTo(other.getType());
    }

    /**
     * Compares this Interaction to the specified object.
     * <p>
     * The result is true if and only if the argument is not null and is a Interaction object that represents the same sequence of characters as this object.
     *
     * @param other the object to compare this Interaction against
     * @return true if the given object represents a Interaction equivalent to this Interaction, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (other != null & other instanceof Interaction) {
            return type.equals(((Interaction) other).getType());
        }
        return false;
    }

    /**
     * Returns a hash code for this object.
     * <p>
     * The hash code of this object is equal to the hash code of the encapsulated String type.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return type.hashCode();
    }

}
