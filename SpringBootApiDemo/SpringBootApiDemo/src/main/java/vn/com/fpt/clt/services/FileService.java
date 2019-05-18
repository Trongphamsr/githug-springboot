package vn.com.fpt.clt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.com.fpt.clt.dto.FileDto;
import vn.com.fpt.clt.dto.MessageDto;
import vn.com.fpt.clt.entities.File;
import vn.com.fpt.clt.entities.Team;
import vn.com.fpt.clt.enums.ActiveEnum;
import vn.com.fpt.clt.enums.MessageEnum;
import vn.com.fpt.clt.exceptions.ConflictException;
import vn.com.fpt.clt.exceptions.EntityNotfoundException;
import vn.com.fpt.clt.repository.FileRepository;
import vn.com.fpt.clt.repository.TeamRepository;
import vn.com.fpt.clt.security.AuthenticationFacade;
import vn.com.fpt.clt.utils.BeanUtil;
import vn.com.fpt.clt.utils.MessageUtil;
import vn.com.fpt.clt.utils.PageUtil;

/**
 * @author TrongPV4
 *
 */

@Service
public class FileService extends AbstractService<FileDto> implements CRUDService<FileDto> {

	@Autowired
	private FileRepository fileRepository;

	@Autowired
	private AuthenticationFacade authenticationFacade;

	@Autowired
	private TeamRepository teamRepository;

	/**
	 * 
	 * @param teamId
	 * @param projectId
	 * @param active
	 * @param pageable
	 * @return
	 */
	public Page<FileDto> findAll(Long teamId, Long projectId, Boolean active, Pageable pageable) {

		// get userId
		Long userId = authenticationFacade.getUserCredentials().getId();
		// check active file then return page of file
		Page<File> pageOfFile = active == null || active == false
				? fileRepository.findByTeamIdAndTeamProjectIdAndTeamProjectUsersId(teamId, projectId, userId, pageable)
				: fileRepository.findByTeamIdAndTeamProjectIdAndTeamProjectUsersIdAndActive(teamId, projectId, userId,
						ActiveEnum.ACTIVE, pageable);

		return PageUtil.copy(pageOfFile, FileDto.class, pageable);
	}

	@Override
	public Page<FileDto> findAll(Boolean active, Pageable pageable) {
		return null;
	}

	@Override
	public FileDto findOne(Long id) {
		return null;
	}

	/**
	 * 
	 * @param projectId
	 * @param teamId
	 * @param fileId
	 * @return
	 */
	public FileDto findOne(Long projectId, Long teamId, Long fileId) {

		// find file by id
		File file = fileRepository.findByIdAndTeamIdAndTeamProjectIdAndTeamProjectUsersId(fileId, teamId, projectId,
				authenticationFacade.getUserCredentials().getId()).orElseThrow(() -> {
					message = MessageUtil.notFound(File.class.getSimpleName(), fileId);
					return new EntityNotfoundException(message);
				});

		// copy file into FileDto and return result
		return BeanUtil.createAndCopy(file, FileDto.class);
	}

	/**
	 * 
	 */
	@Override
	public MessageDto create(FileDto fileDto) {

		// get userId
		Long userId = authenticationFacade.getUserCredentials().getId();
		// get team
		Team team = teamRepository
				.findByIdAndProjectIdAndProjectUsersId(fileDto.getTeamId(), fileDto.getProjectId(), userId)
				.orElseThrow(() -> {
					message = MessageUtil.notFound(Team.class.getSimpleName(), fileDto.getTeamId());
					return new EntityNotfoundException(message);
				});

		// check exists name of file
		boolean existsFileName = fileRepository.existsFileName(fileDto.getFileName(), fileDto.getTeamId(),
				fileDto.getProjectId(), userId);
		if (existsFileName) {
			message = MessageUtil.conflict(File.class.getSimpleName(), "File Name", fileDto.getFileName());
			throw new ConflictException(message);
		}

		// copy FileDto to file
		File file = BeanUtil.createAndCopy(fileDto, File.class);
		// set get information in entity
		file.setActive(ActiveEnum.ACTIVE);
		file.setTeam(team);

		// save file new
		fileRepository.save(file);

		return MessageUtil.ok(MessageEnum.SUCCESS_ADD, Team.class.getSimpleName());

	}

	/**
	 * 
	 */
	@Override
	public MessageDto update(FileDto fileDto) {

		// get userId
		Long userId = authenticationFacade.getUserCredentials().getId();
		// check exists id file
		File file = fileRepository.findByIdAndTeamIdAndTeamProjectIdAndTeamProjectUsersId(fileDto.getId(),
				fileDto.getTeamId(), fileDto.getProjectId(), userId).orElseThrow(() -> {
					message = MessageUtil.notFound(File.class.getSimpleName(), fileDto.getId());
					return new EntityNotfoundException(message);
				});

		// set get information in entity
		file.setFileLastUpdate(fileDto.getFileLastUpdate());
		file.setEndDate(fileDto.getEndDate());
		file.setStartDate(fileDto.getStartDate());
		file.setTargetFlg(fileDto.getTargetFlg());

		// save file
		fileRepository.save(file);

		return MessageUtil.ok(MessageEnum.SUCCESS_EDIT, File.class.getSimpleName());

	}

	@Override
	public MessageDto deleteOrUndelete(Long id, ActiveEnum activeEnum) {
		return null;
	}

	/**
	 * 
	 * @param fileId
	 * @param teamId
	 * @param projectId
	 * @param activeEnum
	 * @return
	 */
	public MessageDto deleteOrUndelete(Long fileId, Long teamId, Long projectId, ActiveEnum activeEnum) {

		// get userId
		Long userId = authenticationFacade.getUserCredentials().getId();
		// check exists id file
		File file = fileRepository
				.findByIdAndTeamIdAndTeamProjectIdAndTeamProjectUsersId(fileId, teamId, projectId, userId)
				.orElseThrow(() -> {
					message = MessageUtil.notFound(File.class.getSimpleName(), fileId);
					return new EntityNotfoundException(message);
				});

		// set active in entity
		file.setActive(activeEnum);
		// save file
		fileRepository.save(file);

		// check active and return result
		return MessageUtil.ok(
				ActiveEnum.ACTIVE == activeEnum ? MessageEnum.SUCCESS_UNDELETE : MessageEnum.SUCCESS_DELETE,
				File.class.getSimpleName());
	}

	@Override
	public Page<FileDto> filter(FileDto object, Pageable pageable) {
		return null;
	}

}
