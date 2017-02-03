package intercomp;

/**
 *
 * @author Alexander
 */
public class Gene implements Comparable<Gene> {

    private String geneID;
    private String taxID;
    private String accessionVersion;
    private String productName;

    public Gene(String geneID, String taxID, String accessionVersion, String productName) {
        this.geneID = geneID;
        this.taxID = taxID;
        this.accessionVersion = accessionVersion;
        this.productName = productName;
    }

    public String getGeneID() {
        return geneID;
    }

    public String getTaxID() {
        return taxID;
    }

    public String getAccessionVersion() {
        return accessionVersion;
    }

    public String getProductName() {
        return productName;
    }

    public void setGeneID(String geneID) {
        this.geneID = geneID;
    }

    public void setTaxID(String taxID) {
        this.taxID = taxID;
    }

    public void setAccessionVersion(String accessionVersion) {
        this.accessionVersion = accessionVersion;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public int compareTo(Gene other) {
        return geneID.compareTo(other.getGeneID());
    }
}
