package vn.com.fpt.clt.dto;

import lombok.Getter;
import lombok.Setter;
import vn.com.fpt.clt.entities.Setting;

@Getter @Setter
public class SettingDto extends Setting {
	
	private Long projectId;
}
