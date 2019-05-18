package vn.com.fpt.clt.entities;

import java.util.Date;

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
import vn.com.fpt.clt.enums.FlagEnum;

@Entity
@Table(name = "file", uniqueConstraints = { @UniqueConstraint(columnNames = { "file_name" }) })
@Getter
@Setter
@NamedEntityGraph(name = "graph.File.team.project.users_setting", 
	attributeNodes = @NamedAttributeNode(value = "team", subgraph = "team.project"), 
	subgraphs = { @NamedSubgraph(name = "team.project", attributeNodes = @NamedAttributeNode(value = "project", subgraph = "project.users_setting")), @NamedSubgraph(name = "project.users_setting", 
	attributeNodes = { @NamedAttributeNode("users"), @NamedAttributeNode("setting") }) })
@JsonInclude(Include.NON_NULL)
public class File extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "file_name")
	private String fileName;

	@Temporal(TemporalType.DATE)
	@Column(name = "file_last_update")
	private Date fileLastUpdate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Team team;

	@Column(name = "target_flg")
	@Enumerated(EnumType.ORDINAL)
	private FlagEnum targetFlg;

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
