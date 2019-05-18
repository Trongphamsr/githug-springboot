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

import vn.com.fpt.clt.dto.HolidayDto;
import vn.com.fpt.clt.exceptions.ConflictException;
import vn.com.fpt.clt.exceptions.EntityNotfoundException;
import vn.com.fpt.clt.services.HolidayService;

/**
 * @author TrongPV4
 *
 */

@RestController
@RequestMapping("/api")
public class HolidayController {

	private static final Logger logger = LoggerFactory.getLogger(HolidayController.class);

	@Autowired
	private HolidayService holidayService;

	/**
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/holidays")
	public ResponseEntity<?> findAll(Pageable pageable) {

		try {
			return ResponseEntity.ok(holidayService.findAll(null, pageable));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/holiday/{id}")
	public ResponseEntity<?> findOne(@PathVariable(name = "id") Long id) {

		try {
			return ResponseEntity.ok(holidayService.findOne(id));
		} catch (EntityNotfoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getPlayload());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	/**
	 * 
	 * @param holidayDto
	 * @return
	 */
	@PostMapping("/holiday")
	public ResponseEntity<?> create(@RequestBody HolidayDto holidayDto) {

		try {
			return ResponseEntity.ok(holidayService.create(holidayDto));
		} catch (ConflictException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getPlayload());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	/**
	 * 
	 * @param id
	 * @param holidayDto
	 * @return
	 */
	@PutMapping("/holiday/{id}")
	public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody HolidayDto holidayDto) {

		try {
			holidayDto.setId(id);
			return ResponseEntity.ok(holidayService.update(holidayDto));
		} catch (EntityNotfoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getPlayload());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}

	/**
	 * 
	 * @param id
	 * @param activeEnum
	 * @return
	 */
	@DeleteMapping("/holiday/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {

		try {
			return ResponseEntity.ok(holidayService.deleteOrUndelete(id, null));
		} catch (EntityNotfoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getPlayload());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
}
