package vn.com.fpt.clt.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.lang.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.com.fpt.clt.entities.Project;
import vn.com.fpt.clt.enums.ActiveEnum;

/**
 * 
 * @author ChienNV7
 *
 */

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>, JpaSpecificationExecutor<Project> {

	/**
	 * 
	 * @param active
	 * @param pageable
	 * @return
	 */
	@EntityGraph(value = "graph.Project.setting", type = EntityGraphType.LOAD)
	Page<Project> findByUsersIdAndActive(Long userId, ActiveEnum active, Pageable pageable);

	/**
	 * 
	 * @param user
	 * @param pageable
	 * @return
	 */
	@EntityGraph(value = "graph.Project.setting", type = EntityGraphType.LOAD)
	Page<Project> findByUsersId(Long userId, Pageable pageable);

	/**
	 * 
	 * @param projectName
	 * @return true or false
	 */
	@Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Project p WHERE p.projectName = :projectName")
	boolean existsByProjectName(String projectName);

	/**
	 * 
	 * @param id
	 * @param userId
	 * @return
	 */
	@EntityGraph(value = "graph.Project.users_setting", type = EntityGraphType.LOAD)
	Optional<Project> findByIdAndUsersId(Long id, Long userId);
	
	@EntityGraph(value = "graph.Project.users_setting", type = EntityGraphType.LOAD)
	Page<Project> findAll(@Nullable Specification<Project> spec, Pageable pageable);
}
