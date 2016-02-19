/**
 * 
 */
package com.multigold.mdm.service.ws.exception;

public class BusinessException  extends RuntimeException {

	private static final long serialVersionUID = -2580863687548674587L;
	
    public BusinessException(String s) {
        super(s);
    }

    public BusinessException() {
        super();
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

}
