package vn.com.fpt.clt.exceptions;

import vn.com.fpt.clt.dto.MessageDto;

public class ConflictStatusException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConflictStatusException(MessageDto _payload) {
		super(_payload);
	}

}
