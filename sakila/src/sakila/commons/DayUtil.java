package sakila.commons;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DayUtil {
	public String getDay() {

		// calendar를 사용 할려면 date.gettime()을 사용해야 한다.
		Calendar date = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		String day = formater.format(date.getTime());
		return day; 
	}
}
