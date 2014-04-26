package org.jboss.tools.gwt.util;

import java.util.Date;

public class DateUtil {
	
	@SuppressWarnings("deprecation")
	public Date yearToDate(Integer year){
		Date date = new  Date(year - 1990, 1, 1);
		
		return date;
	}
	
	@SuppressWarnings("deprecation")
	public Integer dateToYear(Date date)
	{
	
		return date.getYear() + 1900;
	}

}
