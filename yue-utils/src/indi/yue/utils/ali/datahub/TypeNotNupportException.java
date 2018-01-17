package indi.yue.utils.ali.datahub;

public class TypeNotNupportException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TypeNotNupportException() {
		super();
	}

	public TypeNotNupportException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TypeNotNupportException(String message, Throwable cause) {
		super(message, cause);
	}

	public TypeNotNupportException(String message) {
		super(message);
	}

	public TypeNotNupportException(Throwable cause) {
		super(cause);
	}
	
}
