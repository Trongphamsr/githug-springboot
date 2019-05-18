package vn.com.fpt.clt.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.com.fpt.clt.dto.FileDto;
import vn.com.fpt.clt.dto.MessageDto;
import vn.com.fpt.clt.enums.ActiveEnum;
import vn.com.fpt.clt.exceptions.ConflictException;
import vn.com.fpt.clt.exceptions.EntityNotfoundException;
import vn.com.fpt.clt.services.FileService;

/**
 * @author TrongPV4
 *
 */
@RestController
@RequestMapping(value = "/api")
public class FileController {

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@Autowired
	private FileService fileService;

	/**
	 * 
	 * @param teamId
	 * @param projectId
	 * @param active
	 * @param pageable
	 * @return
	 */
	@GetMapping(value = "/{projectId}/{teamId}/files")
	public ResponseEntity<?> findAll(@PathVariable(value = "teamId") Long teamId,
			@PathVariable(value = "projectId") Long projectId, Boolean active, Pageable pageable) {

		try {
			return ResponseEntity.ok(fileService.findAll(projectId, teamId, active, pageable));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	/**
	 * 
	 * @param projectId
	 * @param teamId
	 * @param fileId
	 * @return
	 */
	@GetMapping(value = "/{projectId}/{teamId}/file/{fileId}")
	public ResponseEntity<?> finOne(@PathVariable(value = "projectId") Long projectId,
			@PathVariable(value = "teamId") Long teamId, @PathVariable(value = "fileId") Long fileId) {

		try {
			return ResponseEntity.ok(fileService.findOne(projectId, teamId, fileId));
		} catch (EntityNotfoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getPlayload());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	/**
	 * 
	 * @param fileDto
	 * @param projectId
	 * @param teamId
	 * @return
	 */
	@PostMapping("/{projectId}/{teamId}/file")
	public ResponseEntity<?> create(@RequestBody FileDto fileDto, @PathVariable(value = "projectId") Long projectId,
			@PathVariable(value = "teamId") Long teamId) {

		try {
			fileDto.setProjectId(projectId);
			fileDto.setTeamId(teamId);

			return ResponseEntity.ok(fileService.create(fileDto));
		} catch (EntityNotfoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getPlayload());
		} catch (ConflictException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getPlayload());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	/**
	 * 
	 * @param fileId
	 * @param teamId
	 * @param projectId
	 * @param fileDto
	 * @return
	 */
	@PutMapping(value = "/{projectId}/{teamId}/file/{fileId}")
	public ResponseEntity<?> update(@PathVariable(value = "fileId") Long fileId,
			@PathVariable(value = "teamId") Long teamId, @PathVariable(value = "projectId") Long projectId,
			@RequestBody FileDto fileDto) {

		try {
			fileDto.setId(fileId);
			fileDto.setTeamId(teamId);
			fileDto.setProjectId(projectId);

			return ResponseEntity.ok(fileService.update(fileDto));
		} catch (EntityNotfoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getPlayload());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	/**
	 * 
	 * @param fileId
	 * @param projectId
	 * @param teamId
	 * @param active
	 * @return
	 */
	@DeleteMapping(value = "/{projectId}/{teamId}/file/{fileId}")
	public ResponseEntity<MessageDto> deleteOrUndelete(@PathVariable(value = "fileId") Long fileId,
			@PathVariable(value = "projectId") Long projectId, @PathVariable(value = "teamId") Long teamId,
			Boolean active) {

		try {
			if (null == active)
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			else
				return ResponseEntity.ok(fileService.deleteOrUndelete(fileId, teamId, projectId,
						active == true ? ActiveEnum.ACTIVE : ActiveEnum.IN_ACTIVE));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

}
