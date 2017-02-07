package intercomp;

/**
 * Exception class which is throwed when there are no genes or PMID's to export.
 *
 * @author Alexander
 */
class NullExportException extends Exception {

    /**
     * Constructor for setting up an empty NullExportException.
     */
    public NullExportException() {
        super();
    }

    /**
     * Constructor for setting up a NullExportException with a message.
     *
     * @param message the message to be attached to the Exception.
     */
    public NullExportException(String message) {
        super(message);
    }
}
