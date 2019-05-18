package vn.com.fpt.clt.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import vn.com.fpt.clt.dto.TeamDto;
import vn.com.fpt.clt.entities.Team;

/**
 * 
 * @author ChienNV7
 *
 */

@Repository
public interface TeamRepositoryCustom {

	/**
	 * 
	 * @param teamDto
	 * @return
	 */
	Page<Team> findByConditions(TeamDto teamDto, Pageable pageable);
	
}
