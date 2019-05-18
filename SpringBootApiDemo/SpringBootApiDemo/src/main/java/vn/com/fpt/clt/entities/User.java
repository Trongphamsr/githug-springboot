package vn.com.fpt.clt.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
import javax.persistence.NamedSubgraph;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import vn.com.fpt.clt.enums.ActiveEnum;
import vn.com.fpt.clt.validation.IsCorrect;

/**
 * 
 * @author ChienNV7
 *
 */

@Entity
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
@Setter
@Getter
@NamedEntityGraph(name = "graph.User.roles", attributeNodes = @NamedAttributeNode("roles"))
@NamedEntityGraph(name = "graph.User.projects.setting", includeAllAttributes = true, attributeNodes = {
		@NamedAttributeNode(value = "projects", subgraph = "projects.setting"),
		@NamedAttributeNode("roles") }, subgraphs = {
				@NamedSubgraph(name = "projects.setting", attributeNodes = @NamedAttributeNode(value = "setting")) })
@JsonInclude(Include.NON_NULL)
@IsCorrect
public class User extends Auditable<String> implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToMany(mappedBy = "users")
	private Set<Role> roles = new HashSet<>();

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_project", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"))
	@JsonIgnore
	private Set<Project> projects = new HashSet<>();

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	@JsonBackReference
	private String password;

	@Column(name = "active")
	@Enumerated(EnumType.ORDINAL)
	private ActiveEnum active;

	@Override
	@JsonBackReference
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName().getValue()))
				.collect(Collectors.toList());
	}

	@Override
	@JsonBackReference
	public boolean isAccountNonExpired() {
		return ActiveEnum.ACTIVE == active;
	}

	@Override
	@JsonBackReference
	public boolean isAccountNonLocked() {
		return ActiveEnum.ACTIVE == active;
	}

	@Override
	@JsonBackReference
	public boolean isCredentialsNonExpired() {
		return ActiveEnum.ACTIVE == active;
	}

	@Override
	@JsonBackReference
	public boolean isEnabled() {
		return ActiveEnum.ACTIVE == active;
	}

}
