package vn.com.fpt.clt.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import vn.com.fpt.clt.enums.PatternDateTime;

@Component
public class DateUtil {

	/** The logger */
	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    /**
     * convert date to string
     *
     * @param value
     * @param pattern
     * @return
     */
    public String convertDateToString(Date sysDate, PatternDateTime pattern) {

        if (sysDate == null || pattern == null) {
            return null;
        }

        SimpleDateFormat sdfDate = new SimpleDateFormat(pattern.getValue());
        return sdfDate.format(sysDate);
    }

    /**
     * Convert LocalDateTime object to String
     * 
     * @param dateTime
     *          dateTime : LocalDateTime object to convert
     * @param DateTimeFormat
     *          pattern : time format of target character string
     * @return
     */
    public String convertDateTimeToString(LocalDateTime dateTime, PatternDateTime pattern) {
        String result = null;
        if (pattern == null) {
        	logger.error("The date format of the second argument is not set.");
        }
        
        DateTimeFormatter dateTimeFormatter = null;
        try {
            dateTimeFormatter = DateTimeFormatter.ofPattern(pattern.getValue());
        } catch (Exception e) {
        	logger.error("The date format of the second argument is incorrect.");
        }
        
        try {
            result = dateTime.format(dateTimeFormatter);
        } catch (DateTimeException e) {
        	logger.error(e.getMessage());
        }
        
        return result;
    }

    /**
     * convert String to java.util.Date
     * 
     * @param dateTime
     * @param pattern
     * @return
     */
    public Date convertStringToDate(String dateTime, PatternDateTime pattern) {
        if (StringUtils.isEmpty(dateTime)) {
            return null;
        }
        
        Date result = null;
        try {
            result = new SimpleDateFormat(pattern.getValue()).parse(dateTime);
        } catch (ParseException e) {
        	logger.error("The date format of the second argument is not set.");
        }

        return result;
    }

    /**
     * convert timeStamp to java.util.Date
     * 
     * @param timeStamp
     * @param pattern
     * @return
     */
    public Date convertTimeStampToDate(Timestamp dateTime, PatternDateTime pattern) {
        if (StringUtils.isEmpty(dateTime)) {
            return null;
        }
        
        Date result = null;
        try {
            result = new SimpleDateFormat(pattern.getValue()).parse(dateTime.toString());
        } catch (ParseException e) {
        	logger.error("The date format of the second argument is not set.");
        }

        return result;
    }
    /**
     *<b> Calculator total hours, date time to date time</b><br/>
     *<p>date to - date from</p>
     * @param date from
     * @param date to
     * @return total hour
     */
    public long calDatetoDate(Date from, Date to) {
        //milliseconds
        long different = to.getTime() - from.getTime();

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        
        //calculator total hours
        return different / hoursInMilli;
    }
    
    /**
     *<b> Compare two dates</b><br/>
     *<p>If date to > date from then return 1</p>
     *<p>Else date to = date from then return 0</p>
     *<p>Else date to < date from then return -1</p>
     * @param from
     * @param to
     * @return boolean
     */
    public int compareDate(Date from, Date to) {
        if(from.before(to)) {
            return -1;
        }

        if(from.equals(to)) {
            return 0;
        }

        return 1;

    }
    
}
