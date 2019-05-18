package vn.com.fpt.clt.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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

@Entity
@Table(name = "project_holiday", uniqueConstraints = { @UniqueConstraint(columnNames = { "holiday_date" }) })
@Getter
@Setter
@NamedEntityGraph(name = "graph.ProjectHoliday.project.users_setting", 
	attributeNodes = @NamedAttributeNode(value = "project", subgraph = "project.users_setting"), 
	subgraphs = @NamedSubgraph(name = "project.users_setting", attributeNodes = { @NamedAttributeNode("users"), @NamedAttributeNode("setting") }))
@JsonInclude(Include.NON_NULL)
public class ProjectHoliday extends Auditable<String> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Project project;

	@Temporal(TemporalType.DATE)
	@Column(name = "holiday_date")
	private Date holidayDate;

	@Column(name = "remarks")
	private String remarks;
	
}
