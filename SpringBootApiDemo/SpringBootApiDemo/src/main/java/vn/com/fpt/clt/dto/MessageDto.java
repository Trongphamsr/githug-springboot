package vn.com.fpt.clt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
	
	/** The message code */
    private String code;

    /** The message content */
    private String message;

    /** The message cause. Null if not error */
    private String cause;
    
}
