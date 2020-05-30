/**
*
* @author  Jaime Mejia
* @version 1.0
* @since   30/05/2020
*/

package co.com.cidenet.pnc.configurations;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import co.com.cidenet.pnc.exceptions.ControlledException;
	@RestControllerAdvice
	public class GlobalExceptionHandler {
	    /** Provides handling for exceptions throughout this service. */
	    @ExceptionHandler({ NullPointerException.class, ControlledException.class, Exception.class,
	            RuntimeException.class })
	    public final ResponseEntity<Object> handleException(Exception ex, WebRequest request) {
	        HttpHeaders headers = new HttpHeaders();

	        if (ex instanceof NullPointerException) {
	            HttpStatus status = HttpStatus.BAD_REQUEST;
	            NullPointerException cnae = (NullPointerException) ex;

	            return handleNullPointerException(cnae, headers, status, request);
	        } else if (ex instanceof ControlledException) {
	            HttpStatus status = HttpStatus.BAD_REQUEST;
	            ControlledException controlledException = (ControlledException) ex;

	            return handleControlledException(controlledException, headers, status, request);
	        } else if (ex instanceof RuntimeException) {
	            HttpStatus status = HttpStatus.CONFLICT;
	            RuntimeException controlledException = (RuntimeException) ex;

	            return handleControlledException(controlledException, headers, status, request);
	        } else {
	            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
	            return handleExceptionInternal(ex, headers, status, request);
	        }
	    }

	    /** Customize the response for NullPointerException. */
	    protected ResponseEntity<Object> handleNullPointerException(NullPointerException ex, HttpHeaders headers,
	            HttpStatus status, WebRequest request) {
	        return handleExceptionInternal(ex, headers, status, request);
	    }

	    /** A single place to customize the response body of all Exception types. */
	    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, HttpHeaders headers, HttpStatus status,
	            WebRequest request) {
	        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
	            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
	        }
	        return new ResponseEntity<>(ex.getMessage(), status);
	    }

	    private ResponseEntity<Object> handleControlledException(Exception controlledException, HttpHeaders headers,
	            HttpStatus status, WebRequest request) {
	        return new ResponseEntity<>(controlledException.getMessage(), status);
	    }
}


