package vn.com.fpt.clt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.com.fpt.clt.dto.MessageDto;
import vn.com.fpt.clt.dto.SettingDto;
import vn.com.fpt.clt.entities.Project;
import vn.com.fpt.clt.entities.Setting;
import vn.com.fpt.clt.enums.ActiveEnum;
import vn.com.fpt.clt.enums.MessageEnum;
import vn.com.fpt.clt.exceptions.ConflictException;
import vn.com.fpt.clt.exceptions.EntityNotfoundException;
import vn.com.fpt.clt.repository.ProjectRepository;
import vn.com.fpt.clt.repository.SettingRepository;
import vn.com.fpt.clt.security.AuthenticationFacade;
import vn.com.fpt.clt.utils.BeanUtil;
import vn.com.fpt.clt.utils.MessageUtil;

/**
 * 
 * @author ChienNV7
 *
 */

@Service
public class SettingService extends AbstractService<SettingDto> implements CRUDService<SettingDto> {

	@Autowired
	private SettingRepository settingRepository;

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private AuthenticationFacade authenticationFacade;

	@Override
	public Page<SettingDto> findAll(Boolean active, Pageable pageable) {
		return null;
	}

	@Override
	public SettingDto findOne(Long projectId) {

		Setting setting = settingRepository
				.findByProjectIdAndProjectUsersId(projectId, authenticationFacade.getUserCredentials().getId())
				.orElseThrow(() -> {
					message = MessageUtil.notFound(Project.class.getSimpleName(), projectId);
					return new EntityNotfoundException(message);
				});
		
		return BeanUtil.createAndCopy(setting, SettingDto.class);
	}

	@Override
	public MessageDto create(SettingDto settingDto) {

		Long userId = authenticationFacade.getUserCredentials().getId();
		Project project = projectRepository.findByIdAndUsersId(settingDto.getProjectId(), userId).orElseThrow(() -> {
			message = MessageUtil.notFound(Project.class.getSimpleName(), settingDto.getProjectId());
			return new EntityNotfoundException(message);
		});

		if (null != project.getSetting()) {
			message = MessageUtil.conflict(Setting.class.getSimpleName(), Setting.class.getSimpleName(),
					Project.class.getSimpleName() + " " + project.getProjectName());
			throw new ConflictException(message);
		}

		Setting setting = BeanUtil.createAndCopy(settingDto, Setting.class);
		setting.setReferenceLinks(null);
		setting.setActive(ActiveEnum.ACTIVE);
		setting.setProject(project);

		settingRepository.save(setting);

		return MessageUtil.ok(MessageEnum.SUCCESS_ADD, Setting.class.getSimpleName());
	}

	@Override
	public MessageDto update(SettingDto settingDto) {

		Long userId = authenticationFacade.getUserCredentials().getId();
		Setting setting = settingRepository
				.findByIdAndProjectIdAndProjectUsersId(settingDto.getId(), settingDto.getProjectId(), userId)
				.orElseThrow(() -> {
					message = MessageUtil.notFound(Setting.class.getSimpleName(), settingDto.getId());
					return new EntityNotfoundException(message);
				});

		setting.setPathOutputCollect(settingDto.getPathOutputCollect());
		setting.setSmtpServerMail(settingDto.getSmtpServerMail());
		setting.setAccountMail(settingDto.getAccountMail());
		setting.setPasswordMail(settingDto.getPasswordMail());
		setting.setPathOutputLogs(settingDto.getPathOutputLogs());
		setting.setMailTo(settingDto.getMailTo());
		setting.setSubjectMail(settingDto.getSubjectMail());
		setting.setActive(settingDto.getActive());

		settingRepository.save(setting);

		return MessageUtil.ok(MessageEnum.SUCCESS_EDIT, Setting.class.getSimpleName());
	}

	@Override
	public MessageDto deleteOrUndelete(Long id, ActiveEnum activeEnum) {
		return null;
	}

	public MessageDto deleteOrUndelete(Long projectId, Long settingId, ActiveEnum activeEnum) {
		Long userId = authenticationFacade.getUserCredentials().getId();
		Setting setting = settingRepository.findByIdAndProjectIdAndProjectUsersId(settingId, projectId, userId)
				.orElseThrow(() -> {
					message = MessageUtil.notFound(Setting.class.getSimpleName(), settingId);
					return new EntityNotfoundException(message);
				});

		setting.setActive(activeEnum);

		settingRepository.save(setting);

		return MessageUtil.ok(
				ActiveEnum.ACTIVE == activeEnum ? MessageEnum.SUCCESS_UNDELETE : MessageEnum.SUCCESS_DELETE,
				Project.class.getSimpleName());
	}

	@Override
	public Page<SettingDto> filter(SettingDto object, Pageable pageable) {
		return null;
	}

}
