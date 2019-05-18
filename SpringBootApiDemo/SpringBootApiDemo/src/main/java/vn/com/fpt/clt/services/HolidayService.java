package vn.com.fpt.clt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.com.fpt.clt.dto.HolidayDto;
import vn.com.fpt.clt.dto.MessageDto;
import vn.com.fpt.clt.entities.Holiday;
import vn.com.fpt.clt.enums.ActiveEnum;
import vn.com.fpt.clt.enums.MessageEnum;
import vn.com.fpt.clt.enums.PatternDateTime;
import vn.com.fpt.clt.exceptions.ConflictException;
import vn.com.fpt.clt.exceptions.EntityNotfoundException;
import vn.com.fpt.clt.repository.HolidayRepository;
import vn.com.fpt.clt.utils.BeanUtil;
import vn.com.fpt.clt.utils.DateUtil;
import vn.com.fpt.clt.utils.MessageUtil;
import vn.com.fpt.clt.utils.PageUtil;

/**
 * @author TrongPV4
 *
 */

@Service
public class HolidayService extends AbstractService<HolidayDto> implements CRUDService<HolidayDto> {

	@Autowired
	private HolidayRepository holidayRepository;

	@Autowired
	private DateUtil dateUtil;

	/**
	 * 
	 */
	@Override
	public Page<HolidayDto> findAll(Boolean active, Pageable pageable) {

		// get page of holiday
		Page<Holiday> pageOfHoliday = holidayRepository.findAll(pageable);
		return PageUtil.copy(pageOfHoliday, HolidayDto.class, pageable);

	}

	/**
	 * 
	 */
	@Override
	public MessageDto create(HolidayDto holidayDto) {

		// check exists date of holiday
		boolean existsHolidayDate = holidayRepository.existsByHolidayDate(holidayDto.getHolidayDate());
		if (existsHolidayDate) {
			message = MessageUtil.conflict(Holiday.class.getSimpleName(), "Holiday Date",
					dateUtil.convertDateToString(holidayDto.getHolidayDate(), PatternDateTime.YYYYMMDD_HYPHEN));
			throw new ConflictException(message);
		}

		// copy holidayDto to new holiday
		Holiday holiday = BeanUtil.createAndCopy(holidayDto, Holiday.class);
		// save new holiday
		holidayRepository.save(holiday);

		return MessageUtil.ok(MessageEnum.SUCCESS_ADD, Holiday.class.getSimpleName());
	}

	@Override
	public MessageDto update(HolidayDto holidayDto) {

		// check exists holiday by id
		Holiday holiday = holidayRepository.findById(holidayDto.getId()).orElseThrow(() -> {
			message = MessageUtil.notFound(Holiday.class.getSimpleName(), holidayDto.getId());
			return new EntityNotfoundException(message);
		});

		// update data
		holiday.setRemarks(holidayDto.getRemarks());
		// update holiday entity
		holidayRepository.save(holiday);

		return MessageUtil.ok(MessageEnum.SUCCESS_EDIT, Holiday.class.getSimpleName());

	}

	@Override
	public HolidayDto findOne(Long id) {

		// find holiday by id
		Holiday holiday = holidayRepository.findById(id).orElseThrow(() -> {
			message = MessageUtil.notFound(Holiday.class.getSimpleName(), id);
			return new EntityNotfoundException(message);
		});

		return BeanUtil.createAndCopy(holiday, HolidayDto.class);

	}

	@Override
	public MessageDto deleteOrUndelete(Long id, ActiveEnum activeEnum) {

		// check exists holiday by id
		boolean existsHoliday = holidayRepository.existsById(id);
		if (!existsHoliday) {
			message = MessageUtil.notFound(Holiday.class.getSimpleName(), id);
			throw new EntityNotfoundException(message);
		}

		// delete holiday by id
		holidayRepository.deleteById(id);

		return MessageUtil.ok(MessageEnum.SUCCESS_DELETE, Holiday.class.getSimpleName());

	}

	@Override
	public Page<HolidayDto> filter(HolidayDto object, Pageable pageable) {
		return null;
	}

}
