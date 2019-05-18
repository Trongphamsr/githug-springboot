package vn.com.fpt.clt.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import vn.com.fpt.clt.entities.Team;

@Getter @Setter
public class TeamDto extends Team {
	
	private Long projectId;
	private Long userId;
	private Date fromStartDate;
	private Date toStartDate;
	
}
