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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import vn.com.fpt.clt.enums.ActiveEnum;

@Entity
@Table(name = "team", uniqueConstraints = { @UniqueConstraint(columnNames = { "team_name", "project_id" }) })
@Getter
@Setter
@NamedEntityGraph(name = "graph.Team.project.users_setting", 
	attributeNodes = @NamedAttributeNode(value = "project", subgraph = "project.users_setting"), 
	subgraphs = @NamedSubgraph(name = "project.users_setting", attributeNodes = { @NamedAttributeNode("users"), @NamedAttributeNode("setting") }))
@JsonInclude(Include.NON_NULL)
public class Team extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "team_name")
	private String teamName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Project project;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "team")
	@JsonIgnore
	private Set<File> files = new HashSet<>();

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Date startDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "active")
	@Enumerated(EnumType.ORDINAL)
	private ActiveEnum active;
}
