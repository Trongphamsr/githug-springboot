package vn.com.fpt.clt.utils;

import vn.com.fpt.clt.dto.MessageDto;
import vn.com.fpt.clt.enums.MessageEnum;

public class MessageUtil {

	/**
	 * Get not found message of entity
	 * 
	 * @param entity - the entity name
	 * @param id     - the id of entity
	 * @return not found message object
	 */
	public static MessageDto notFound(String entity, Long id) {
		return new MessageDto(MessageEnum.NOT_EXIST.getCode(),
				ContextUtil.getMessage(MessageEnum.NOT_EXIST.getKey(), entity),
				String.format(MessageEnum.NOT_EXIST.getCause(), entity, id));
	}

	/**
	 * Get timeout message of entity
	 * 
	 * @param entity - the entity name
	 * @return timeout message object
	 */
	public static MessageDto requestTimeout(String entity) {
		return new MessageDto(MessageEnum.REQUEST_TIMEOUT.getCode(),
				ContextUtil.getMessage(MessageEnum.REQUEST_TIMEOUT.getKey(), entity),
				String.format(MessageEnum.REQUEST_TIMEOUT.getCause(), entity));
	}

	/**
	 * Get conflict message of entity
	 * 
	 * @param entity - the entity name
	 * @param field  - the filed of conflict
	 * @param value  - the value of conflict
	 * @return not found message object
	 */
	public static MessageDto conflict(String entity, String field, String value) {
		return new MessageDto(MessageEnum.DUPLICATE.getCode(),
				ContextUtil.getMessage(MessageEnum.DUPLICATE.getKey(), field),
				String.format(MessageEnum.DUPLICATE.getCause(), entity, value));
	}

	/**
	 * Get not found message of entity
	 * 
	 * @param entity - the entity name
	 * @param id     - the id of entity
	 * @param status - the status conflict
	 * @return not found message object
	 */
	public static MessageDto conflictStatus(String entity, Long id, String status) {
		return new MessageDto(MessageEnum.DUPLICATE_STATUS.getCode(),
				ContextUtil.getMessage(MessageEnum.DUPLICATE_STATUS.getKey(), entity, status),
				String.format(MessageEnum.DUPLICATE_STATUS.getCause(), entity, id));
	}
	
	/**
	 * Get require message of entity
	 * 
	 * @param entity - the entity name
	 * @return not found message object
	 */
	public static MessageDto require(String entity) {
		return new MessageDto(MessageEnum.REQUIRED.getCode(),
				ContextUtil.getMessage(MessageEnum.REQUIRED.getKey(), entity),
				String.format(MessageEnum.REQUIRED.getCause(), entity));
	}

	/**
	 * Get require disable message of entity
	 * 
	 * @param entity - the entity name
	 * @param id     - the id of entity
	 * @return not found message object
	 */
	public static MessageDto requireDisable(String entity, Long id) {
		return new MessageDto(MessageEnum.NOT_DELETED_YET.getCode(),
				ContextUtil.getMessage(MessageEnum.NOT_DELETED_YET.getKey(), entity),
				String.format(MessageEnum.NOT_DELETED_YET.getCause(), id));
	}

	/**
	 * Get in using message of entity
	 * 
	 * @param entity - the entity name
	 * @param id     - the id of entity
	 * @return not found message object
	 */
	public static MessageDto inUse(String entity, Long id) {
		return new MessageDto(MessageEnum.IN_USE.getCode(), ContextUtil.getMessage(MessageEnum.IN_USE.getKey(), entity),
				String.format(MessageEnum.IN_USE.getCause(), id));
	}

	/**
	 * Get OK message of entity
	 * 
	 * @param type   - the enum type of ok message
	 * @param entity - the entity name
	 * @return OK message object
	 */
	public static MessageDto ok(MessageEnum type, String entity) {
		return new MessageDto(type.getCode(), ContextUtil.getMessage(type.getKey(), entity), null);
	}

	/**
	 * Get OK message of entity
	 * 
	 * @param type - the enum type of ok message
	 * @return OK message object
	 */
	public static MessageDto ok(MessageEnum type) {
		return new MessageDto(type.getCode(), ContextUtil.getMessage(type.getKey()), null);
	}

	public static MessageDto ok(MessageEnum type, String entity, String value) {
		return new MessageDto(type.getCode(), ContextUtil.getMessage(type.getKey(), entity), value);
	}
	
	public static MessageDto notValid(String message) {
		return new MessageDto(MessageEnum.NOT_VALID.getCode(),
				MessageEnum.NOT_VALID.getKey(),
				String.format(MessageEnum.NOT_VALID.getCause(), message));
	}
}
