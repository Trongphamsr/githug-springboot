package vn.com.fpt.clt.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import vn.com.fpt.clt.enums.ActiveEnum;

/**
 * 
 * @author ChienNV7
 *
 */

@Entity
@Table(name = "project", uniqueConstraints = { @UniqueConstraint(columnNames = { "project_name" }) })
@Getter
@Setter
@NamedEntityGraph(name = "graph.Project.setting", attributeNodes = @NamedAttributeNode("setting"))
@NamedEntityGraph(name = "graph.Project.users_setting", attributeNodes = { @NamedAttributeNode("users"),
		@NamedAttributeNode("setting") })
@JsonInclude(Include.NON_NULL)
public class Project extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "project_name", updatable = false)
	private String projectName;

	@ManyToMany(mappedBy = "projects")
	@JsonIgnore
	private Set<User> users = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
	@JsonIgnore
	private Set<Team> teams = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
	@JsonIgnore
	private Set<ProjectHoliday> projectHolidays = new HashSet<>();

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "project")
	@JsonIgnore
	private Setting setting;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
	@JsonIgnore
	private Set<BatchLogs> batchLogs = new HashSet<>();

	@Column(name = "start_date")
	@Temporal(TemporalType.DATE)
	private Date startDate;

	@Column(name = "end_date")
	@Temporal(TemporalType.DATE)
	private Date endDate;

	@Column(name = "area_name")
	private String areaName;

	@Column(name = "active")
	@Enumerated(EnumType.ORDINAL)
	private ActiveEnum active;

}
