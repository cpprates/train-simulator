package exception;

public class UnderflowException extends RuntimeException {
	/**
	 * Underflow Exception
	 */
	private static final long serialVersionUID = 1L;

	public UnderflowException() {
		super("Underflow!");
	}
}