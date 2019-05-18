package vn.com.fpt.clt.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import vn.com.fpt.clt.dto.ProjectDto;
import vn.com.fpt.clt.entities.Project;

/**
 * @author TrongPV4
 *
 */

@Repository
public interface ProjectRepositoryCustom {

	/**
	 * @param projectDto
	 * @param pageable
	 * @return
	 */
	Page<Project> findByConditions(ProjectDto projectDto, Pageable pageable);
}
