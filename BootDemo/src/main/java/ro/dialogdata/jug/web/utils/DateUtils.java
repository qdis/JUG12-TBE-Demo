package ro.dialogdata.jug.web.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	private final static SimpleDateFormat SDF = new SimpleDateFormat(
			"EEE, d MMM yyyy HH:mm");

	public static String formatDate(Date date) {
		return SDF.format(date);
	}
}
