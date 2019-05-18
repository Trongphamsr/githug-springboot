package vn.com.fpt.clt.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.com.fpt.clt.entities.File;
import vn.com.fpt.clt.enums.ActiveEnum;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

	/**
	 * 
	 * @param teamId
	 * @param projectId
	 * @param userId
	 * @param active
	 * @param pageable
	 * @return
	 */
	@EntityGraph(value = "graph.File.team.project.users_setting", type = EntityGraphType.FETCH)
	Page<File> findByTeamIdAndTeamProjectIdAndTeamProjectUsersIdAndActive(Long teamId, Long projectId, Long userId,
			ActiveEnum active, Pageable pageable);

	/**
	 * 
	 * @param teamId
	 * @param projectId
	 * @param userId
	 * @param pageable
	 * @return
	 */
	@EntityGraph(value = "graph.File.team.project.users_setting", type = EntityGraphType.FETCH)
	Page<File> findByTeamIdAndTeamProjectIdAndTeamProjectUsersId(Long teamId, Long projectId, Long userId,
			Pageable pageable);

	/**
	 * 
	 * @param fileId
	 * @param teamId
	 * @param projectId
	 * @param userId
	 * @return
	 */
	@EntityGraph(value = "graph.File.team.project.users_setting", type = EntityGraphType.FETCH)
	Optional<File> findByIdAndTeamIdAndTeamProjectIdAndTeamProjectUsersId(Long fileId, Long teamId, Long projectId,
			Long userId);

	/**
	 * 
	 * @param fileName
	 * @return
	 */
	@Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM File f INNER JOIN f.team t INNER JOIN t.project p INNER JOIN p.users u WHERE f.fileName = :fileName AND t.id = :teamId AND p.id = :projectId AND u.id = :userId")
	boolean existsFileName(String fileName, Long teamId, Long projectId, Long userId);

}
