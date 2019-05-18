package vn.com.fpt.clt.exceptions;

import vn.com.fpt.clt.dto.MessageDto;

/**
 * 
 * @author ChienNV7
 *
 */
public class RequireException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RequireException(MessageDto _payload) {
		super(_payload);
	}

}
