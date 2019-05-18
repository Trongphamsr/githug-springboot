package vn.com.fpt.clt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import vn.com.fpt.clt.entities.Role;
import vn.com.fpt.clt.enums.RoleEnum;

/**
 * 
 * @author ChienNV7
 *
 */
@RepositoryRestResource
public interface RoleRepository extends JpaRepository<Role, Long> {

	/**
	 * 
	 * @param roleUser
	 * @return
	 */
	@EntityGraph(value = "graph.Role.users" , type=EntityGraphType.LOAD)
	Optional<Role> findByRoleName(RoleEnum roleUser);
	
}
