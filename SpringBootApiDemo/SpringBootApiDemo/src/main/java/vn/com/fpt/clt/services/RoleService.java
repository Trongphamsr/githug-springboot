package vn.com.fpt.clt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.com.fpt.clt.dto.MessageDto;
import vn.com.fpt.clt.dto.UserDto;
import vn.com.fpt.clt.enums.ActiveEnum;
import vn.com.fpt.clt.repository.RoleRepository;

/**
 * 
 * @author ChienNV7
 *
 */

@Service
public class RoleService extends AbstractService<UserDto> implements CRUDService<UserDto> {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Page<UserDto> findAll(Boolean active, Pageable pageable) {
		return null;
	}

	@Override
	public UserDto findOne(Long id) {
		return null;
	}

	@Override
	public MessageDto create(UserDto object) {
		return null;
	}

	@Override
	public MessageDto update(UserDto object) {
		return null;
	}

	@Override
	public MessageDto deleteOrUndelete(Long id, ActiveEnum activeEnum) {
		return null;
	}

	@Override
	public Page<UserDto> filter(UserDto object, Pageable pageable) {
		return null;
	}

}
