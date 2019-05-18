package vn.com.fpt.clt.controllers;

import javax.validation.Valid;

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

import vn.com.fpt.clt.dto.ProjectDto;
import vn.com.fpt.clt.enums.ActiveEnum;
import vn.com.fpt.clt.exceptions.ConflictException;
import vn.com.fpt.clt.exceptions.EntityNotfoundException;
import vn.com.fpt.clt.services.ProjectService;

/**
 * 
 * @author ChienNV7
 *
 */

@RestController
@RequestMapping("/api")
public class ProjectController {

	private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

	@Autowired
	private ProjectService projectService;

	@GetMapping("/projects")
	public ResponseEntity<?> findByUser(Boolean active, Pageable pageable) {

		try {
			Page<ProjectDto> pageOfProject = projectService.findAll(active, pageable);
			return ResponseEntity.ok(pageOfProject);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@PostMapping("/project")
	public ResponseEntity<?> create(@Valid @RequestBody ProjectDto projectDto) {

		try {
			return ResponseEntity.ok(projectService.create(projectDto));
		} catch (EntityNotfoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getPlayload());
		} catch (ConflictException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getPlayload());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@GetMapping(value = "/project/{id}")
	public ResponseEntity<?> findOne(@PathVariable(value = "id") Long projectId) {

		try {
			return ResponseEntity.ok(projectService.findOne(projectId));
		} catch (EntityNotfoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getPlayload());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@PutMapping("/project/{id}")
	public ResponseEntity<?> update(@PathVariable(value = "id") Long projectId,
			@Valid @RequestBody ProjectDto projectDto) {

		try {
			projectDto.setId(projectId);
			return ResponseEntity.ok(projectService.update(projectDto));
		} catch (EntityNotfoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getPlayload());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@DeleteMapping("/project/{id}")
	public ResponseEntity<?> deleteOrUndelete(@PathVariable(value = "id") Long id, Boolean active) {

		try {
			if (null == active)
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			else
				return ResponseEntity.ok(
						projectService.deleteOrUndelete(id, active == true ? ActiveEnum.ACTIVE : ActiveEnum.IN_ACTIVE));
		} catch (EntityNotfoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getPlayload());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@PostMapping(value = "/project/searches")
	public ResponseEntity<?> filter(@RequestBody ProjectDto projectDto, Pageable pageable) {

		try {
			return ResponseEntity.ok(projectService.filter(projectDto, pageable));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
}
