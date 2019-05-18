package vn.com.fpt.clt.exceptions;

import javax.persistence.PersistenceException;

import vn.com.fpt.clt.dto.MessageDto;

/**
 * 
 * @author ChienNV7
 *
 */
public class BusinessException extends PersistenceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The customize message */
	private MessageDto payload;

	/**
	 * Constructors
	 * 
	 * @param _payload - the customize message
	 */
	public BusinessException(MessageDto _payload) {
		payload = _payload;
	}

	/**
	 * Get customize message
	 */
	public MessageDto getPlayload() {
		return payload;
	}
}
