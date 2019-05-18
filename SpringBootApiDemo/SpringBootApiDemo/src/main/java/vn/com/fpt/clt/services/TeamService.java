package vn.com.fpt.clt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.com.fpt.clt.dto.MessageDto;
import vn.com.fpt.clt.dto.TeamDto;
import vn.com.fpt.clt.entities.Project;
import vn.com.fpt.clt.entities.Team;
import vn.com.fpt.clt.enums.ActiveEnum;
import vn.com.fpt.clt.enums.MessageEnum;
import vn.com.fpt.clt.exceptions.ConflictException;
import vn.com.fpt.clt.exceptions.EntityNotfoundException;
import vn.com.fpt.clt.repository.ProjectRepository;
import vn.com.fpt.clt.repository.TeamRepository;
import vn.com.fpt.clt.repository.custom.TeamRepositoryCustom;
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
public class TeamService extends AbstractService<TeamDto> implements CRUDService<TeamDto> {

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private AuthenticationFacade authenticationFacade;

	@Autowired

	private TeamRepositoryCustom teamRepositoryCustom;
	/**
	 * 
	 * @param userId
	 * @param active
	 * @param pageable
	 * @return
	 */
	public Page<TeamDto> findAll(Long projectId, Boolean active, Pageable pageable) {

		// Get userId
		Long userId = authenticationFacade.getUserCredentials().getId();

		// Get page of Team
		Page<Team> pageOfEntity = active == null || active == false
				? teamRepository.findByProjectIdAndProjectUsersId(projectId, userId, pageable)
				: teamRepository.findByProjectIdAndProjectUsersIdAndActive(projectId, userId, ActiveEnum.ACTIVE,
						pageable);

		return PageUtil.copy(pageOfEntity, TeamDto.class, pageable);
	}

	@Override
	public Page<TeamDto> findAll(Boolean active, Pageable pageable) {
		return null;
	}

	@Override
	public MessageDto create(TeamDto teamDto) {

		// Get userId
		Long userId = authenticationFacade.getUserCredentials().getId();

		// Get project
		Project project = projectRepository.findByIdAndUsersId(teamDto.getProjectId(), userId).orElseThrow(() -> {
			message = MessageUtil.notFound(Project.class.getSimpleName(), teamDto.getProjectId());
			return new EntityNotfoundException(message);
		});

		// Check exist teamName
		boolean existsTeam = teamRepository.existsByTeamNameAndProject(teamDto.getTeamName(), teamDto.getProjectId());
		if (existsTeam) {
			message = MessageUtil.conflict(Team.class.getSimpleName(), "Team Name", teamDto.getTeamName());
			throw new ConflictException(message);
		}

		// Copy team and update data
		Team team = BeanUtil.createAndCopy(teamDto, Team.class);
		team.setActive(ActiveEnum.ACTIVE);
		team.setProject(project);

		// Save entity
		teamRepository.save(team);

		return MessageUtil.ok(MessageEnum.SUCCESS_ADD, Team.class.getSimpleName());
	}

	@Override
	public MessageDto update(TeamDto teamDto) {

		// Get userId
		Long userId = authenticationFacade.getUserCredentials().getId();

		// Get team entity
		Team team = teamRepository
				.findByIdAndProjectIdAndProjectUsersId(teamDto.getId(), teamDto.getProjectId(), userId)
				.orElseThrow(() -> {
					message = MessageUtil.notFound(Team.class.getSimpleName(), teamDto.getId());
					return new EntityNotfoundException(message);
				});

		// Update new data
		team.setStartDate(teamDto.getStartDate());
		team.setEndDate(teamDto.getEndDate());

		// Save entity
		teamRepository.save(team);

		return MessageUtil.ok(MessageEnum.SUCCESS_EDIT, Team.class.getSimpleName());

	}

	@Override
	public TeamDto findOne(Long teamId) {
		return null;
	}

	/**
	 * @param projectId
	 * @param teamId
	 * @return
	 */
	public TeamDto findOne(Long projectId, Long teamId) {

		// Get team
		Team team = teamRepository.findByIdAndProjectIdAndProjectUsersId(teamId, projectId,
				authenticationFacade.getUserCredentials().getId()).orElseThrow(() -> {
					message = MessageUtil.notFound(Team.class.getSimpleName(), teamId);
					return new EntityNotfoundException(message);
				});

		return BeanUtil.createAndCopy(team, TeamDto.class);
	}

	@Override
	public MessageDto deleteOrUndelete(Long id, ActiveEnum activeEnum) {
		return null;
	}

	/**
	 * @param projectId
	 * @param teamId
	 * @param activeEnum
	 * @return
	 */
	public MessageDto deleteOrUndelete(Long projectId, Long teamId, ActiveEnum activeEnum) {

		// Get userId
		Long userId = authenticationFacade.getUserCredentials().getId();

		// Get team
		Team team = teamRepository.findByIdAndProjectIdAndProjectUsersId(teamId, projectId, userId).orElseThrow(() -> {
			message = MessageUtil.notFound(Team.class.getSimpleName(), teamId);
			return new EntityNotfoundException(message);
		});

		// Set active
		team.setActive(activeEnum);

		// Save entity
		teamRepository.save(team);

		return MessageUtil.ok(
				ActiveEnum.ACTIVE == activeEnum ? MessageEnum.SUCCESS_UNDELETE : MessageEnum.SUCCESS_DELETE,
				Team.class.getSimpleName());
	}

	@Override
	public Page<TeamDto> filter(TeamDto teamDto, Pageable pageable) {

		Long userId = authenticationFacade.getUserCredentials().getId();
		teamDto.setUserId(userId);

		Page<Team> pageOfTeam = teamRepositoryCustom.findByConditions(teamDto, pageable);
		return PageUtil.copy(pageOfTeam, TeamDto.class, pageable);
	}

}
