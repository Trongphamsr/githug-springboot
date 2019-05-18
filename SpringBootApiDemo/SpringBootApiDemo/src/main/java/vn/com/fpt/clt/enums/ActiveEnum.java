package vn.com.fpt.clt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ActiveEnum{

	IN_ACTIVE("0", "In-Active", "false"), 
	ACTIVE("1", "Active", "true");

	private final String value;
    private final String display;
    private final String name;
	
}
