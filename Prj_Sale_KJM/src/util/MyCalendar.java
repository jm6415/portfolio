package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.jdatepicker.JDatePicker;

public class MyCalendar {
	
	static public String getCal(JDatePicker dPic) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		int h = c.get(Calendar.HOUR_OF_DAY);
		int m = c.get(Calendar.MINUTE);
		int s = c.get(Calendar.SECOND);
		String date = sdf.format(
				((Calendar) dPic.getModel().getValue()).getTime()) + " "
				+ ((h < 10) ? "0" + h : h) + ":" 
				+ ((m < 10) ? "0" + m : m) + ":" 
				+ ((s < 10) ? "0" + s : s
		);
		return date;
	}
	
}
