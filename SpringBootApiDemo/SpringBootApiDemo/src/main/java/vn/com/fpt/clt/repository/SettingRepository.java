package vn.com.fpt.clt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.com.fpt.clt.entities.Setting;

/**
 * 
 * @author ChienNV7
 *
 */

@Repository
public interface SettingRepository extends JpaRepository<Setting, Long> {

	/**
	 * 
	 * @param projectId
	 * @param userId
	 * @return
	 */
	@EntityGraph(value = "graph.Setting.project_referenceLinks.users" , type=EntityGraphType.LOAD)
	Optional<Setting> findByProjectIdAndProjectUsersId(Long projectId, Long userId);
	
	/**
	 * 
	 * @param id
	 * @param projectId
	 * @param userId
	 * @return
	 */
	@EntityGraph(value = "graph.Setting.project_referenceLinks.users" , type=EntityGraphType.LOAD)
	Optional<Setting> findByIdAndProjectIdAndProjectUsersId(Long id, Long projectId, Long userId);
}
