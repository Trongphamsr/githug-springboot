package vn.com.fpt.clt.entities;

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
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import vn.com.fpt.clt.enums.ActiveEnum;

@Entity
@Table(name = "settings", uniqueConstraints = { @UniqueConstraint(columnNames = { "project_id" }) })
@Getter
@Setter
@NamedEntityGraph(name = "graph.Setting.project_referenceLinks.users", includeAllAttributes = true, attributeNodes = {
		@NamedAttributeNode("referenceLinks"),
		@NamedAttributeNode(value = "project", subgraph = "project.users") }, subgraphs = {
				@NamedSubgraph(name = "project.users", attributeNodes = @NamedAttributeNode(value = "users")) })
@JsonInclude(Include.NON_NULL)
public class Setting extends Auditable<String> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id", updatable = false)
	@JsonIgnore
	private Project project;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "setting")
	private Set<SettingReferenceLink> referenceLinks = new HashSet<>();

	@Column(name = "path_output_collect")
	private String pathOutputCollect;

	@Column(name = "smtp_server_mail")
	private String smtpServerMail;

	@Column(name = "account_mail")
	private String accountMail;

	@Column(name = "password_mail")
	private String passwordMail;

	@Column(name = "path_output_logs")
	private String pathOutputLogs;

	@Column(name = "mail_to")
	private String mailTo;

	@Column(name = "subject_mail")
	private String subjectMail;

	@Column(name = "active")
	@Enumerated(EnumType.ORDINAL)
	private ActiveEnum active;

}
