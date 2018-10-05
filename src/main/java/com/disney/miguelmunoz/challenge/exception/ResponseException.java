package com.disney.miguelmunoz.challenge.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown by a service implementation with an HttpStatus instance. This Exception lets the service specify the
 * HttpStatus that should be returned to the client. This allows the code that throws the exception to communicate
 * with the catch clause that generates the error response.
 * 
 * All subclasses of this class must be annotated with the @ResponseStatus annotation with
 * an HttpStatus value, which will be incorporated into the message of this exception.
 * <p>Created by IntelliJ IDEA.
 * <p>Date: 2/11/18
 * <p>Time: 4:02 PM
 *
 * @author Miguel Mu\u00f1oz
 */
public abstract class ResponseException extends RuntimeException {
	// I don't like making this a RuntimeException, but I'm sticking to the APIs generated by swagger, which doesn't
	// declare any exceptions, so this is what it has to be.
	private final HttpStatus httpStatus;

	/**
	 * Create a ResponseException with the specified status and error message
	 *
	 * @param message the message
	 */
	protected ResponseException(String message) {
		super(clean(message));
		httpStatus = extractStatus(getClass());
	}

	/**
	 * Create a ResponseException with the specified status, error message, and cause
	 *
	 * @param message the message
	 * @param cause   the cause
	 */
	@SuppressWarnings("WeakerAccess")
	public ResponseException(String message, Throwable cause) {
		super(clean(message), cause);
		httpStatus = extractStatus(getClass());
	}

	@Override
	public String getMessage() {
		final String message = super.getMessage();
		if (message.isEmpty()) {
			return String.format("Code: %s %s", httpStatus, httpStatus.getReasonPhrase());
		}
		return String.format("%s -- Code: %s %s", message, httpStatus, httpStatus.getReasonPhrase());
	}

	@JsonIgnore
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	/**
	 * Return the value of the ResponseStatus annotation. Subclasses of this Exception class that aren't annotated
	 * with @ResponseStatus will throw an exception the first time you try to use it.
	 * @param exceptionClass The subclass of this class
	 * @param <E> The subclass of this class
	 * @return The HttpStatus value from the @ResponseStatus annotation
	 */
	private static <E extends ResponseException> HttpStatus extractStatus(Class<E> exceptionClass) {
		ResponseStatus responseStatus = exceptionClass.getDeclaredAnnotation(ResponseStatus.class);
		return responseStatus.value();
	}
	
	private static String clean(String s) { return (s == null) ? "" : s; }
}
