package vn.com.fpt.clt.exceptions;

import vn.com.fpt.clt.dto.MessageDto;

public class NotImplementException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotImplementException(MessageDto _payload) {
		super(_payload);
	}

}
