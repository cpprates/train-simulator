package exception;

public class OverflowException extends RuntimeException {
	/**
	 * Overflow Exception
	 */
	private static final long serialVersionUID = 1L;

	public OverflowException() {
		super("Overflow!");
	}
}