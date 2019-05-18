/**
 * 
 */
package vn.com.fpt.clt.dto;

import lombok.Getter;
import lombok.Setter;
import vn.com.fpt.clt.entities.ProjectHoliday;

/**
 * @author HoangL4
 *
 */

@Getter @Setter
public class ProjectHolidayDto extends ProjectHoliday{
	
	private Long projectId;
	
}
