package vn.com.fpt.clt.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import vn.com.fpt.clt.enums.RoleEnum;

/**
 * 
 * @author ChienNV7
 *
 */

@Entity
@Table(name = "role", uniqueConstraints = { @UniqueConstraint(columnNames = { "role_name" }) })
@Setter 
@Getter
@NamedEntityGraph(name = "graph.Role.users", attributeNodes = @NamedAttributeNode("users"))
@JsonInclude(Include.NON_NULL)
public class Role extends Auditable<String> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "role_name", nullable = false)
	@Enumerated(EnumType.STRING)
	private RoleEnum roleName;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "role_user", 
			joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), 
			inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
	@JsonIgnore
	private Set<User> users = new HashSet<>();

}
