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

import vn.com.fpt.clt.dto.ProjectHolidayDto;
import vn.com.fpt.clt.exceptions.ConflictException;
import vn.com.fpt.clt.exceptions.EntityNotfoundException;
import vn.com.fpt.clt.services.ProjectHolidayService;

/**
 * @author HoangL4
 *
 */
@RestController
@RequestMapping("/api")
public class ProjectHolidayController {

	private static final Logger logger = LoggerFactory.getLogger(ProjectHolidayController.class);

	@Autowired
	private ProjectHolidayService projectHolidayService;

	@GetMapping("/{projectId}/holidays")
	public ResponseEntity<?> findByProject(@PathVariable(value = "projectId") Long projectId, Pageable pageable) {

		try {
			return ResponseEntity.ok(projectHolidayService.findAll(projectId, pageable));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@GetMapping(value = "/{projectId}/holiday/{projectHolidayId}")
	public ResponseEntity<?> findOne(@PathVariable(value = "projectId") Long projectId,
			@PathVariable(value = "projectHolidayId") Long projectHolidayId) {

		try {
			return ResponseEntity.ok(projectHolidayService.findOne(projectId, projectHolidayId));
		} catch (EntityNotfoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getPlayload());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@PostMapping("/{projectId}/holiday")
	public ResponseEntity<?> create(@RequestBody ProjectHolidayDto projectHolidayDto, @PathVariable Long projectId) {

		try {
			projectHolidayDto.setProjectId(projectId);

			return ResponseEntity.ok(projectHolidayService.create(projectHolidayDto));
		} catch (EntityNotfoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getPlayload());
		} catch (ConflictException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getPlayload());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@PutMapping("/{projectId}/holiday/{projectHolidayId}")
	public ResponseEntity<?> update(@RequestBody ProjectHolidayDto ProjectHolidayDto,
			@PathVariable(value = "projectId") Long projectId,
			@PathVariable(value = "projectHolidayId") Long projectHolidayId) {

		try {
			ProjectHolidayDto.setProjectId(projectId);
			ProjectHolidayDto.setId(projectHolidayId);

			return ResponseEntity.ok(projectHolidayService.update(ProjectHolidayDto));
		} catch (EntityNotfoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getPlayload());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@DeleteMapping(value = "/{projectId}/holiday/{projectHolidayId}")
	public ResponseEntity<?> delete(@PathVariable(value = "projectHolidayId") Long projectHolidayId,
			@PathVariable(value = "projectId") Long projectId) {

		try {
			return ResponseEntity.ok(projectHolidayService.delete(projectId, projectHolidayId));
		} catch (EntityNotfoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getPlayload());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

}
