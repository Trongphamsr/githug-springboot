package vn.com.fpt.clt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import vn.com.fpt.clt.dto.MessageDto;
import vn.com.fpt.clt.dto.UserDto;
import vn.com.fpt.clt.entities.Role;
import vn.com.fpt.clt.entities.User;
import vn.com.fpt.clt.enums.ActiveEnum;
import vn.com.fpt.clt.enums.MessageEnum;
import vn.com.fpt.clt.enums.RoleEnum;
import vn.com.fpt.clt.exceptions.ConflictException;
import vn.com.fpt.clt.exceptions.EntityNotfoundException;
import vn.com.fpt.clt.repository.RoleRepository;
import vn.com.fpt.clt.repository.UserRepository;
import vn.com.fpt.clt.utils.BeanUtil;
import vn.com.fpt.clt.utils.MessageUtil;
import vn.com.fpt.clt.utils.PageUtil;

/**
 * 
 * @author ChienNV7
 *
 */

@Service
public class UserService extends AbstractService<UserDto> implements CRUDService<UserDto> {

	private static final BCryptPasswordEncoder BC_ENCODER = new BCryptPasswordEncoder();

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	/**
	 * Find user by username
	 * 
	 * @param username
	 * @return userDto
	 * @throws JsonProcessingException
	 */
	public UserDto findByUsername(String username) {
		User user = userRepository.findByUsername(username).orElse(null);
		return BeanUtil.createAndCopy(user, UserDto.class);
	}

	@Override
	public Page<UserDto> findAll(Boolean active, Pageable pageable) {
		Page<User> pageOfEntity = active == null || active == false ? userRepository.findAll(pageable)
				: userRepository.findByActive(ActiveEnum.ACTIVE, pageable);

		return PageUtil.copy(pageOfEntity, UserDto.class, pageable);
	}

	@Override
	public MessageDto create(UserDto userDto) {

		// check user exists
		boolean existsUser = userRepository.existsByUsername(userDto.getUsername());
		if (existsUser) {
			message = MessageUtil.conflict(User.class.getSimpleName(), "Username", userDto.getUsername());
			throw new ConflictException(message);
		}

		// init new user
		User user = BeanUtil.createAndCopy(userDto, User.class);
		// encode password
		user.setPassword(BC_ENCODER.encode(user.getPassword()));
		// set default active
		user.setActive(ActiveEnum.ACTIVE);

		// get role user
		Role roleUser = roleRepository.findByRoleName(RoleEnum.USER).orElseThrow(null);
		// set user to role user
		roleUser.getUsers().add(user);

		// set default role user
		user.getRoles().add(roleUser);
		// insert new user
		userRepository.save(user);

		return MessageUtil.ok(MessageEnum.SUCCESS_ADD, User.class.getSimpleName());
	}

	@Override
	public MessageDto update(UserDto userDto) {
		return null;
	}

	@Override
	public MessageDto deleteOrUndelete(Long id, ActiveEnum activeEnum) {
		return null;
	}

	@Override
	public UserDto findOne(Long id) {

		User user = userRepository.findById(id).orElseThrow(() -> {
			message = MessageUtil.notFound(User.class.getSimpleName(), id);
			return new EntityNotfoundException(message);
		});

		return BeanUtil.createAndCopy(user, UserDto.class);
	}

	@Override
	public Page<UserDto> filter(UserDto object, Pageable pageable) {
		return null;
	}

}
