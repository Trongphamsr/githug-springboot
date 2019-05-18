package vn.com.fpt.clt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 
 * @author ChienNV7
 *
 */

@AllArgsConstructor
@Getter
public enum PatternDateTime {
	
	YYYY_MM_DDTHH_MM_SS("yyyy-MM-dd'T'HH:mm:ss")
    , YYYYMMDDHHMM("yyyyMMddHHmm")
    , YYYYMMDDHHMMSS("yyyyMMddHHmmss")
    , YYYYMMDD("yyyyMMdd")
    , YYYYMMDD_SLASH("yyyy/MM/dd")
    , YYYYMMDD_HYPHEN("yyyy-MM-dd")
    , YYYYMMDDHHMM_SLASH("yyyy/MM/dd HH:mm")
    , YYYYMMDDHHMM_HYPHEN("yyyy-MM-dd HH:mm")
    , YYYY_MM_DD_HH_MM_SS("yyyy/MM/dd HH:mm:ss")
    , HHMM("HH:mm");
    
    private final String value;

}
