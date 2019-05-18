/**
 * 
 */
package vn.com.fpt.clt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.com.fpt.clt.dto.MessageDto;
import vn.com.fpt.clt.dto.ProjectHolidayDto;
import vn.com.fpt.clt.entities.Project;
import vn.com.fpt.clt.entities.ProjectHoliday;
import vn.com.fpt.clt.enums.ActiveEnum;
import vn.com.fpt.clt.enums.MessageEnum;
import vn.com.fpt.clt.enums.PatternDateTime;
import vn.com.fpt.clt.exceptions.ConflictException;
import vn.com.fpt.clt.exceptions.EntityNotfoundException;
import vn.com.fpt.clt.repository.ProjectHolidayRepository;
import vn.com.fpt.clt.repository.ProjectRepository;
import vn.com.fpt.clt.security.AuthenticationFacade;
import vn.com.fpt.clt.utils.BeanUtil;
import vn.com.fpt.clt.utils.DateUtil;
import vn.com.fpt.clt.utils.MessageUtil;
import vn.com.fpt.clt.utils.PageUtil;

/**
 * @author HoangL4
 *
 */
@Service
public class ProjectHolidayService extends AbstractService<ProjectHolidayDto>
		implements CRUDService<ProjectHolidayDto> {

	@Autowired
	private ProjectHolidayRepository projectHolidayRepository;

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private AuthenticationFacade authenticationFacade;

	@Autowired
	private DateUtil dateUtil;

	/**
	 * 
	 * @param projectId
	 * @param pageable
	 * @return
	 */
	public Page<ProjectHolidayDto> findAll(Long projectId, Pageable pageable) {

		// Get userID
		Long userId = authenticationFacade.getUserCredentials().getId();
		// Get page of ProjectHoliday
		Page<ProjectHoliday> pageOfEntity = projectHolidayRepository.findByProjectIdAndProjectUsersId(projectId, userId,
				pageable);

		return PageUtil.copy(pageOfEntity, ProjectHolidayDto.class, pageable);
	}

	/**
	 * @param projectId
	 * @param projectHolidayId
	 * @return
	 */
	public ProjectHolidayDto findOne(Long projectId, Long projectHolidayId) {

		// Get ProjectHoliday
		ProjectHoliday projectHoliday = projectHolidayRepository.findByIdAndProjectIdAndProjectUsersId(projectHolidayId,
				projectId, authenticationFacade.getUserCredentials().getId()).orElseThrow(() -> {
					message = MessageUtil.notFound(ProjectHoliday.class.getSimpleName(), projectHolidayId);
					return new EntityNotfoundException(message);
				});

		return BeanUtil.createAndCopy(projectHoliday, ProjectHolidayDto.class);
	}

	/**
	 * 
	 */
	@Override
	public MessageDto create(ProjectHolidayDto holidayDto) {

		// Get userId
		Long userId = authenticationFacade.getUserCredentials().getId();

		// Get project
		Project project = projectRepository.findByIdAndUsersId(holidayDto.getProjectId(), userId).orElseThrow(() -> {
			message = MessageUtil.notFound(Project.class.getSimpleName(), holidayDto.getProjectId());
			return new EntityNotfoundException(message);
		});

		// Check exist date
		boolean existsDate = projectHolidayRepository.existsByHolidayDateAndProjectId(holidayDto.getHolidayDate(),
				holidayDto.getProjectId());
		if (existsDate) {
			message = MessageUtil.conflict(ProjectHoliday.class.getSimpleName(), "Holiday date",
					dateUtil.convertDateToString(holidayDto.getHolidayDate(), PatternDateTime.YYYYMMDD_HYPHEN));
			throw new ConflictException(message);
		}

		// Copy ProjectHoliday to new projectHoliday
		ProjectHoliday projectHoliday = BeanUtil.createAndCopy(holidayDto, ProjectHoliday.class);
		projectHoliday.setProject(project);

		// Save entity
		projectHolidayRepository.save(projectHoliday);

		return MessageUtil.ok(MessageEnum.SUCCESS_ADD, ProjectHoliday.class.getSimpleName());
	}

	/**
	 * 
	 */
	@Override
	public MessageDto update(ProjectHolidayDto holidayDto) {

		// Get userId
		Long userId = authenticationFacade.getUserCredentials().getId();

		// Get ProjectHoliday
		ProjectHoliday projectHoliday = projectHolidayRepository
				.findByIdAndProjectIdAndProjectUsersId(holidayDto.getId(), holidayDto.getProjectId(), userId)
				.orElseThrow(() -> {
					message = MessageUtil.notFound(ProjectHoliday.class.getSimpleName(), holidayDto.getId());
					return new EntityNotfoundException(message);
				});

		// Set remarks
		projectHoliday.setRemarks(holidayDto.getRemarks());
		// Save entity
		projectHolidayRepository.save(projectHoliday);

		return MessageUtil.ok(MessageEnum.SUCCESS_EDIT, ProjectHoliday.class.getSimpleName());
	}

	/**
	 * 
	 * @param projectHolidayId
	 * @param projectId
	 * @return
	 */
	public MessageDto delete(Long projectId, Long projectHolidayId) {

		// Get userId
		Long userId = authenticationFacade.getUserCredentials().getId();

		// Check exist ProjectHoliday
		boolean existProjectHoliday = projectHolidayRepository.existsByIdAndProjectIdAndProjectUsersId(projectHolidayId,
				projectId, userId);
		if (!existProjectHoliday) {
			message = MessageUtil.notFound(ProjectHoliday.class.getSimpleName(), projectHolidayId);
			throw new EntityNotfoundException(message);
		}

		// Delete entity
		projectHolidayRepository.deleteById(projectHolidayId);

		return MessageUtil.ok(MessageEnum.SUCCESS_DELETE, ProjectHoliday.class.getSimpleName());
	}

	@Override
	public Page<ProjectHolidayDto> findAll(Boolean active, Pageable pageable) {
		return null;
	}

	@Override
	public ProjectHolidayDto findOne(Long id) {
		return null;
	}

	@Override
	public MessageDto deleteOrUndelete(Long id, ActiveEnum activeEnum) {
		return null;
	}

	@Override
	public Page<ProjectHolidayDto> filter(ProjectHolidayDto object, Pageable pageable) {
		return null;
	}

}
