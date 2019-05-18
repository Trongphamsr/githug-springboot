package vn.com.fpt.clt.exceptions;

import vn.com.fpt.clt.dto.MessageDto;

public class ConflictException extends BusinessException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConflictException(MessageDto _payload) {
		super(_payload);
	}
	
}
