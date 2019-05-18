package vn.com.fpt.clt.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.com.fpt.clt.dto.SettingDto;
import vn.com.fpt.clt.enums.ActiveEnum;
import vn.com.fpt.clt.exceptions.ConflictException;
import vn.com.fpt.clt.exceptions.EntityNotfoundException;
import vn.com.fpt.clt.services.SettingService;

/**
 * 
 * @author ChienNV7
 *
 */

@RepositoryRestController
@RequestMapping("/api")
public class SettingController {

	private static final Logger logger = LoggerFactory.getLogger(SettingController.class);

	@Autowired
	private SettingService settingService;

	@GetMapping(value = "/{projectId}/setting")
	public ResponseEntity<?> findOne(@PathVariable(value = "projectId") Long projectId) {

		try {
			return ResponseEntity.ok(settingService.findOne(projectId));
		} catch (EntityNotfoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getPlayload());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@PostMapping("/{projectId}/setting")
	public ResponseEntity<?> create(@PathVariable(value = "projectId") Long projectId,
			@RequestBody SettingDto settingDto) {

		try {
			settingDto.setProjectId(projectId);
			return ResponseEntity.ok(settingService.create(settingDto));
		} catch (EntityNotfoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getPlayload());
		} catch (ConflictException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getPlayload());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@PutMapping("/{projectId}/setting/{settingId}")
	public ResponseEntity<?> update(@PathVariable(value = "projectId") Long projectId,
			@PathVariable(value = "settingId") Long settingId, @RequestBody SettingDto settingDto) {

		try {
			settingDto.setId(settingId);
			settingDto.setProjectId(projectId);
			return ResponseEntity.ok(settingService.update(settingDto));
		} catch (EntityNotfoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getPlayload());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@DeleteMapping("/{projectId}/setting/{settingId}")
	public ResponseEntity<?> deleteOrUndelete(@PathVariable(value = "projectId") Long projectId,
			@PathVariable(value = "settingId") Long settingId, Boolean active) {

		try {
			if (null == active)
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			else
				return ResponseEntity.ok(settingService.deleteOrUndelete(projectId, settingId,
						active == true ? ActiveEnum.ACTIVE : ActiveEnum.IN_ACTIVE));
		} catch (EntityNotfoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getPlayload());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}
