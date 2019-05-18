package vn.com.fpt.clt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FlagEnum {

	OFF("0", "OFF", "false"), 
	ON("1", "ON", "true");

	private final String value;
    private final String display;
    private final String name;
	
}
