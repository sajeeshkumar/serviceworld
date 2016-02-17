/**
 * 
 */
package net.sajeesh.samplerest.api.exception;

/**
 * User defined exception.
 * 
 * @author Sajeesh
 *
 */
public class ServiceException extends Exception {

	/**
	 * default serial version id
	 */
	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

}
