package vn.com.fpt.clt.dto;

import lombok.Getter;
import lombok.Setter;
import vn.com.fpt.clt.entities.File;

@Setter @Getter
public class FileDto extends File {

	private Long projectId;
	
	private Long teamId;
	
}
