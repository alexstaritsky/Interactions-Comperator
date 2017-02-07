package intercomp;

/**
 * Gene class for storing information about a gene in one object.
 *
 * @author Alexander
 */
public class Gene implements Comparable<Gene> {

    private String geneID;
    private String taxID;
    private String accessionVersion;
    private String productName;

    /**
     * Constructor for creating a Gene object with all internal fields.
     *
     * @param geneID the gene identifier for the gene.
     * @param taxID the taxonomy id for the gene.
     * @param accessionVersion the accession version of the gene.
     * @param productName the product name of the gene
     */
    public Gene(String geneID, String taxID, String accessionVersion, String productName) {
        this.geneID = geneID;
        this.taxID = taxID;
        this.accessionVersion = accessionVersion;
        this.productName = productName;
    }

    /**
     * Returns the gene identifier for this gene.
     *
     * @return a String that represents the gene identifier.
     */
    public String getGeneID() {
        return geneID;
    }

    /**
     * Returns the taxonomy identifier for this gene.
     *
     * @return a String that represents the taxonomy identifier.
     */
    public String getTaxID() {
        return taxID;
    }

    /**
     * Returns the accession version for this gene.
     *
     * @return a String that represents the accession version.
     */
    public String getAccessionVersion() {
        return accessionVersion;
    }

    /**
     * Returns the product name for this gene.
     *
     * @return a String that represents the product name.
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets the gene identifier.
     *
     * @param geneID the gene identifier to set.
     */
    public void setGeneID(String geneID) {
        this.geneID = geneID;
    }

    /**
     * Sets the taxonomy identifier.
     *
     * @param taxID the taxonomy identifier to set.
     */
    public void setTaxID(String taxID) {
        this.taxID = taxID;
    }

    /**
     * Sets the accession version.
     *
     * @param accessionVersion the accession version to set.
     */
    public void setAccessionVersion(String accessionVersion) {
        this.accessionVersion = accessionVersion;
    }

    /**
     * Sets the product name.
     *
     * @param productName the product name to set.
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Compares two gene identifiers from two genes lexicographically.
     *
     * @param other the Gene to be compared.
     * @return the value 0 if the gene identifier from the argument Gene is equal to this gene identifier; a value less than 0 if this gene identifier is lexicographically less than the gene identifier of the Gene in the argument; and a value greater than 0 if this gene identifier is lexicographically greater than the Gene identifier from the argument.
     */
    @Override
    public int compareTo(Gene other) {
        return geneID.compareTo(other.getGeneID());
    }

    /**
     * Compares this Gene to the specified object.
     * <p>
     * The result is true if and only if the argument is not null and is a Gene object that represents the same sequence of characters as this object.
     *
     * @param other the object to compare this Gene against
     * @return true if the given object represents a Gene equivalent to this Gene, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (other != null & other instanceof Gene) {
            return geneID.equals(((Gene) other).getGeneID());
        }
        return false;
    }

    /**
     * Returns a hash code for this object.
     * <p>
     * The hash code of this object is equal to the hash code of the encapsulated gene identifier.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return geneID.hashCode();
    }

}
