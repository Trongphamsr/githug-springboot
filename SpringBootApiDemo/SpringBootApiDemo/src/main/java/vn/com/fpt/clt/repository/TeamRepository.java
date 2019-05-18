package vn.com.fpt.clt.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import vn.com.fpt.clt.entities.Team;
import vn.com.fpt.clt.enums.ActiveEnum;

/**
 * 
 * @author ChienNV7
 *
 */

@Repository
public interface TeamRepository extends JpaRepository<Team, Long>, JpaSpecificationExecutor<Team> {

	/**
	 * 
	 * @param projectId
	 * @param userId
	 * @param active
	 * @param pageable
	 * @return
	 */
	@EntityGraph(value = "graph.Team.project.users_setting", type = EntityGraphType.FETCH)
	Page<Team> findByProjectIdAndProjectUsersIdAndActive(Long projectId, Long userId, ActiveEnum active,
			Pageable pageable);

	/**
	 * 
	 * @param projectId
	 * @param userId
	 * @param pageable
	 * @return
	 */
	@EntityGraph(value = "graph.Team.project.users_setting", type = EntityGraphType.FETCH)
	Page<Team> findByProjectIdAndProjectUsersId(Long projectId, Long userId, Pageable pageable);

	/**
	 * 
	 * @param teamId
	 * @param projectId
	 * @param userId
	 * @return
	 */
	@EntityGraph(value = "graph.Team.project.users_setting", type = EntityGraphType.FETCH)
	Optional<Team> findByIdAndProjectIdAndProjectUsersId(Long teamId, Long projectId, Long userId);

	/**
	 * 
	 * @param projectName
	 * @return true or false
	 */
	@Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM Team t INNER JOIN t.project p WHERE t.teamName = :teamName AND p.id = :projectId")
	boolean existsByTeamNameAndProject(String teamName, Long projectId);

	/**
	 * 
	 * @param projectId
	 * @param spec
	 * @param pageable
	 * @return
	 */
	@EntityGraph(value = "graph.Team.project.users_setting", type = EntityGraphType.FETCH)
	Page<Team> findAll(@Nullable Specification<Team> spec, Pageable pageable);
	
}
