package vn.com.fpt.clt.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import vn.com.fpt.clt.dto.TeamDto;
import vn.com.fpt.clt.enums.ActiveEnum;
import vn.com.fpt.clt.exceptions.ConflictException;
import vn.com.fpt.clt.exceptions.EntityNotfoundException;
import vn.com.fpt.clt.services.TeamService;

/**
 * 
 * @author ChienNV7
 *
 */

@RestController
@RequestMapping("/api")
public class TeamController {

	private static final Logger logger = LoggerFactory.getLogger(TeamController.class);

	@Autowired
	private TeamService teamService;

	@GetMapping("/{projectId}/teams")
	public ResponseEntity<?> findByProject(@PathVariable(value = "projectId") Long projectId,
			Boolean active, Pageable pageable) {

		try {
			Page<TeamDto> pageOfTeam = teamService.findAll(projectId, active, pageable);

			return ResponseEntity.ok(pageOfTeam);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@GetMapping(value = "/{projectId}/team/{teamId}")
	public ResponseEntity<?> findOne(@PathVariable(value = "projectId") Long projectId,
			@PathVariable(value = "teamId") Long teamId) {

		try {
			return ResponseEntity.ok(teamService.findOne(projectId, teamId));
		} catch (EntityNotfoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getPlayload());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@PostMapping(value = "/{projectId}/team")
	public ResponseEntity<?> create(@RequestBody TeamDto teamDto,
			@PathVariable(value = "projectId") Long projectId) {

		try {
			teamDto.setProjectId(projectId);
			return ResponseEntity.ok(teamService.create(teamDto));
		} catch (EntityNotfoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getPlayload());
		} catch (ConflictException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getPlayload());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@PutMapping(value = "/{projectId}/team/{teamId}")
	public ResponseEntity<?> update(@RequestBody TeamDto teamDto,
			@PathVariable(value = "projectId") Long projectId, @PathVariable(value = "teamId") Long teamId) {

		try {
			teamDto.setProjectId(projectId);
			teamDto.setId(teamId);
			return ResponseEntity.ok(teamService.update(teamDto));
		} catch (EntityNotfoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getPlayload());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@DeleteMapping(value = "/{projectId}/team/{teamId}")
	public ResponseEntity<?> deleteOrUndelete(@PathVariable(value = "teamId") Long teamId,
			@PathVariable(value = "projectId") Long projectId, Boolean active) {

		try {
			if (null == active)
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			else
				return ResponseEntity.ok(teamService.deleteOrUndelete(projectId, teamId,
						active == true ? ActiveEnum.ACTIVE : ActiveEnum.IN_ACTIVE));
		} catch (EntityNotfoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getPlayload());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@PostMapping(value = "/{projectId}/team/searches")
	public ResponseEntity<?> filter(@PathVariable(value = "projectId") Long projectId, @RequestBody TeamDto teamDto, Pageable pageable) {

		try {
			teamDto.setProjectId(projectId);
			return ResponseEntity.ok(teamService.filter(teamDto, pageable));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}
