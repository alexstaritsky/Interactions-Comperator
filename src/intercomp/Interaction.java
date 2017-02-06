package intercomp;

/**
 * Interaction class to describe a interatiction between two genes.
 * @author Alexander
 */
public class Interaction implements Comparable<Interaction> {

    private Gene geneA;
    private Gene geneB;
    private String pubmedID;
    private String type;
    private String lastUpdate;
    private String geneRIF;

    public Interaction(Gene geneA, Gene geneB, String pubmedID, String type, String lastUpdate, String geneRIF) {
        this.geneA = geneA;
        this.geneB = geneB;
        this.pubmedID = pubmedID;
        this.type = type;
        this.lastUpdate = lastUpdate;
        this.geneRIF = geneRIF;
    }

    public Gene getGeneA() {
        return geneA;
    }

    public Gene getGeneB() {
        return geneB;
    }

    public String getPubmedID() {
        return pubmedID;
    }

    public String getType() {
        return type;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public String getGeneRIF() {
        return geneRIF;
    }

    public void setGeneA(Gene geneA) {
        this.geneA = geneA;
    }

    public void setGeneB(Gene geneB) {
        this.geneB = geneB;
    }

    public void setPubmedID(String pubmedID) {
        this.pubmedID = pubmedID;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setGeneRIF(String geneRIF) {
        this.geneRIF = geneRIF;
    }

    @Override
    public int compareTo(Interaction other) {
        return type.compareTo(other.getType());
    }

}
