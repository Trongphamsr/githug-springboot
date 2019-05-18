package vn.com.fpt.clt.exceptions;

import vn.com.fpt.clt.dto.MessageDto;

public class TimeoutException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TimeoutException(MessageDto _payload) {
		super(_payload);
	}

}
