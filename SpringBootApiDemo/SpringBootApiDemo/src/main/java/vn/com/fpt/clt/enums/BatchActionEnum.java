package vn.com.fpt.clt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BatchActionEnum {

	ACTION1("1", "ACTION1", "detail action 1"), 
	ACTION2("2", "ACTION2", "detail action 2");

	private final String value;
    private final String display;
    private final String name;
}
