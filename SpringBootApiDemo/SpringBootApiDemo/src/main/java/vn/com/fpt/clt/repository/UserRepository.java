package vn.com.fpt.clt.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import vn.com.fpt.clt.entities.User;
import vn.com.fpt.clt.enums.ActiveEnum;

/**
 * 
 * @author ChienNV7
 *
 */
@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {
	/**
	 * SELECT USER WHERE USERNAME = ?
	 * 
	 * @param username
	 * @return object USER
	 */
	@EntityGraph(value = "graph.User.roles", type = EntityGraphType.LOAD)
	Optional<User> findByUsername(String username);

	/**
	 * 
	 * @param active
	 * @param pageable
	 * @return list user
	 */
	@Query(value = "FROM User u WHERE u.active = :active ORDER BY u.id DESC")
	@EntityGraph(value = "graph.User.roles", type = EntityGraphType.LOAD)
	Page<User> findByActive(ActiveEnum active, Pageable pageable);

	/**
	 * 
	 */
	@Query(value = "FROM User u ORDER BY u.id DESC")
	@EntityGraph(value = "graph.User.roles", type = EntityGraphType.LOAD)
	Page<User> findAll(Pageable pageable);

	/**
	 * 
	 * @param username
	 * @return true or false
	 */
	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.username = :username")
	boolean existsByUsername(String username);

	/**
	 * 
	 * @param id
	 * @return
	 */
	@EntityGraph(value = "graph.User.projects.setting", type = EntityGraphType.LOAD)
	Optional<User> findById(Long id);
}
