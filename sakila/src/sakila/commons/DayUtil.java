package sakila.commons;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DayUtil {
	public String getDay() {

		// calendar�� ��� �ҷ��� date.gettime()�� ����ؾ� �Ѵ�.
		Calendar date = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		String day = formater.format(date.getTime());
		return day; 
	}
}
