package vn.com.fpt.clt.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "holiday", uniqueConstraints = { @UniqueConstraint(columnNames = { "holiday_date" }) })
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class Holiday extends Auditable<String> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "holiday_date")
	@Temporal(TemporalType.DATE)
	private Date holidayDate;

	@Column(name = "remarks")
	private String remarks;
}
