package vn.com.fpt.clt.exceptions;

import vn.com.fpt.clt.dto.MessageDto;

public class EntityNotfoundException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EntityNotfoundException(MessageDto _payload) {
		super(_payload);
	}

}
