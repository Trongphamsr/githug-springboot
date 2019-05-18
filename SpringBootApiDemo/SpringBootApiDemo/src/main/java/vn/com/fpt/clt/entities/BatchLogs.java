package vn.com.fpt.clt.entities;

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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

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
@Table(name = "batch_logs", uniqueConstraints = { @UniqueConstraint(columnNames = { "project_id" }) })
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class BatchLogs extends Auditable<String> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	@NotNull
	private Project project;

	@Column(name = "action_number")
	@Enumerated(EnumType.ORDINAL)
	private ActiveEnum actionNumber;

	@Column(name = "error_log")
	private String errorLog;
	
	@Column(name = "info_log")
	private String infoLog;
	
	@Column(name = "status")
	@Enumerated(EnumType.ORDINAL)
	private FlagEnum status;
	
}
