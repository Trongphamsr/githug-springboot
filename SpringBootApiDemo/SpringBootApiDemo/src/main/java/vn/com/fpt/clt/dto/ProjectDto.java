package vn.com.fpt.clt.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import vn.com.fpt.clt.entities.Project;

@Getter @Setter
public class ProjectDto extends Project {
	
	private Long userId;
	private Date fromStartDate;
	private Date toStartDate;

}
