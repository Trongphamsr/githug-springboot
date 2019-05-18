package vn.com.fpt.clt.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import vn.com.fpt.clt.dto.MessageDto;
import vn.com.fpt.clt.enums.ActiveEnum;

public interface CRUDService<T> {
	
	Page<T> findAll(Boolean active, Pageable pageable);
	
	T findOne(Long id);
	
	@Transactional
	MessageDto create(T object);

	@Transactional
	MessageDto update(T object);
	
	@Transactional
	MessageDto deleteOrUndelete(Long id, ActiveEnum activeEnum);
	
	Page<T> filter(T object, Pageable pageable);
}
