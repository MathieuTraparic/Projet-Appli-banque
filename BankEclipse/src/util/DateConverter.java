/*
 * Author: Sylvain Labasse / AtelierFX
 */

package util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateConverter {
	
	/**Converting localdate to date
	 * @param local date
	 * @return date
	 */
	public static Date LocalDate2Date(LocalDate local) {
		return Date.from(local.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
	/**Converting date to localdate
	 * @param date
	 * @return local date
	 */
	public static LocalDate DateToLocalDate(Date date) {
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}	

}
