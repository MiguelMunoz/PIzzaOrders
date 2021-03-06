package com.disney.miguelmunoz.challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p>Created by IntelliJ IDEA.
 * <p>Date: 9/30/18
 * <p>Time: 5:53 PM
 *
 * @author Miguel Mu\u00f1oz
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends ResponseException {

	public NotFoundException(final String message) {
		super(message);
	}

	public NotFoundException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
