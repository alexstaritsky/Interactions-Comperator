package intercomp;

/**
 * Exception class which is throwed when there are no genes or PMID's to export.
 * @author Alexander
 */
class NullExportException extends Exception {

    public NullExportException() {
        super();
    }

    public NullExportException(String message) {
        super(message);
    }
}
