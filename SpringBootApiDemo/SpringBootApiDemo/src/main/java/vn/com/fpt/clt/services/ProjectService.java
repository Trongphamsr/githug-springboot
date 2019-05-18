package vn.com.fpt.clt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.com.fpt.clt.dto.MessageDto;
import vn.com.fpt.clt.dto.ProjectDto;
import vn.com.fpt.clt.entities.Project;
import vn.com.fpt.clt.entities.User;
import vn.com.fpt.clt.enums.ActiveEnum;
import vn.com.fpt.clt.enums.MessageEnum;
import vn.com.fpt.clt.exceptions.ConflictException;
import vn.com.fpt.clt.exceptions.EntityNotfoundException;
import vn.com.fpt.clt.repository.ProjectRepository;
import vn.com.fpt.clt.repository.UserRepository;
import vn.com.fpt.clt.repository.custom.ProjectRepositoryCustom;
import vn.com.fpt.clt.security.AuthenticationFacade;
import vn.com.fpt.clt.utils.BeanUtil;
import vn.com.fpt.clt.utils.MessageUtil;
import vn.com.fpt.clt.utils.PageUtil;

/**
 * 
 * @author ChienNV7
 *
 */

@Service
public class ProjectService extends AbstractService<ProjectDto> implements CRUDService<ProjectDto> {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationFacade authenticationFacade;
	
	@Autowired
	private ProjectRepositoryCustom projectRepositoryCustom; 

	@Override
	public Page<ProjectDto> findAll(Boolean active, Pageable pageable) {
		
		authenticationFacade.getAuthentication().isAuthenticated();
		Long userId = authenticationFacade.getUserCredentials().getId();
		Page<Project> pageOfProject = active == null || active == false ? projectRepository.findByUsersId(userId, pageable)
				: projectRepository.findByUsersIdAndActive(userId, ActiveEnum.ACTIVE, pageable);

		return PageUtil.copy(pageOfProject, ProjectDto.class, pageable);
	}

	@Override
	public MessageDto create(ProjectDto projectDto) {

		// check project exists by project name
		boolean existsProject = projectRepository.existsByProjectName(projectDto.getProjectName());
		if (existsProject) {
			message = MessageUtil.conflict(Project.class.getSimpleName(), "Project Name", projectDto.getProjectName());
			throw new ConflictException(message);
		}

		Long userId = authenticationFacade.getUserCredentials().getId();
		// get user
		User user = userRepository.findById(userId).orElseThrow(() -> {
			message = MessageUtil.notFound(User.class.getSimpleName(), userId);
			return new EntityNotfoundException(message);
		});

		// copy projectDto to project
		Project project = BeanUtil.createAndCopy(projectDto, Project.class);
		// set default active
		project.setActive(ActiveEnum.ACTIVE);
		// add project to list project in object user
		user.getProjects().add(project);
		// add user to list user in object project
		project.getUsers().add(user);
		// add new project
		projectRepository.save(project);

		return MessageUtil.ok(MessageEnum.SUCCESS_ADD, Project.class.getSimpleName());
	}

	@Override
	public MessageDto update(ProjectDto projectDto) {

		Long userId = authenticationFacade.getUserCredentials().getId();
		// check exists project by id
		Project project = projectRepository.findByIdAndUsersId(projectDto.getId(), userId).orElseThrow(() -> {
			message = MessageUtil.notFound(Project.class.getSimpleName(), projectDto.getId());
			return new EntityNotfoundException(message);
		});

		project.setStartDate(projectDto.getStartDate());
		project.setEndDate(projectDto.getEndDate());
		project.setAreaName(projectDto.getAreaName());
		// update project
		projectRepository.save(project);

		return MessageUtil.ok(MessageEnum.SUCCESS_EDIT, Project.class.getSimpleName());
	}

	@Override
	public MessageDto deleteOrUndelete(Long projectId, ActiveEnum activeEnum) {

		Long userId = authenticationFacade.getUserCredentials().getId();
		// check exists project by id and get project
		Project project = projectRepository.findByIdAndUsersId(projectId, userId).orElseThrow(() -> {
			message = MessageUtil.notFound(Project.class.getSimpleName(), projectId);
			return new EntityNotfoundException(message);
		});

		project.setActive(activeEnum);
		// update project
		projectRepository.save(project);

		return MessageUtil.ok(
				ActiveEnum.ACTIVE == activeEnum ? MessageEnum.SUCCESS_UNDELETE : MessageEnum.SUCCESS_DELETE,
				Project.class.getSimpleName());
	}

	@Override
	public ProjectDto findOne(Long projectId) {

		Project project = projectRepository
				.findByIdAndUsersId(projectId, authenticationFacade.getUserCredentials().getId()).orElseThrow(() -> {
					message = MessageUtil.notFound(Project.class.getSimpleName(), projectId);
					return new EntityNotfoundException(message);
				});

		return BeanUtil.createAndCopy(project, ProjectDto.class);
	}

	@Override
	public Page<ProjectDto> filter(ProjectDto projectDto, Pageable pageable) {
		
		Long userId = authenticationFacade.getUserCredentials().getId();
		projectDto.setUserId(userId);
		
		Page<Project> pageOfProject = projectRepositoryCustom.findByConditions(projectDto, pageable);
		return PageUtil.copy(pageOfProject, ProjectDto.class, pageable);
	}

}
