package vn.com.fpt.clt.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.com.fpt.clt.entities.ProjectHoliday;

/**
 * @author HoangL4
 *
 */
@Repository
public interface ProjectHolidayRepository
		extends JpaRepository<ProjectHoliday, Long>, JpaSpecificationExecutor<ProjectHoliday> {


	/**
	 * @param holidayDate
	 * @param projectId
	 * @return
	 */
	@Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM ProjectHoliday t INNER JOIN t.project p WHERE t.holidayDate = :holidayDate AND p.id = :projectId")
	boolean existsByHolidayDateAndProjectId(Date holidayDate, Long projectId);

	/**
	 * @param projectId
	 * @param userId
	 * @param pageable
	 * @return
	 */
	@EntityGraph(value = "graph.ProjectHoliday.project.users_setting", type = EntityGraphType.FETCH)
	Page<ProjectHoliday> findByProjectIdAndProjectUsersId(Long projectId, Long userId, Pageable pageable);

	/**
	 * @param id
	 * @param projectId
	 * @param userId
	 * @return
	 */
	@EntityGraph(value = "graph.ProjectHoliday.project.users_setting", type = EntityGraphType.FETCH)
	Optional<ProjectHoliday> findByIdAndProjectIdAndProjectUsersId(Long id, Long projectId, Long userId);
	
	/**
	 * @param id
	 * @param projectId
	 * @param userId
	 * @return
	 */
	@Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM ProjectHoliday t INNER JOIN t.project p INNER JOIN p.users u WHERE t.id = :id AND p.id = :projectId and u.id = :userId")
	boolean existsByIdAndProjectIdAndProjectUsersId(Long id, Long projectId, Long userId);
	
	
}
