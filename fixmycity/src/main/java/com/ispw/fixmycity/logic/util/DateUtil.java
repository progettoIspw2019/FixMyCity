package com.ispw.fixmycity.logic.util;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javafx.scene.control.DatePicker;

public class DateUtil {

	public static Date dateFromDatePicker(DatePicker datePicker) {
		LocalDate ld = datePicker.getValue();
		Calendar c = Calendar.getInstance();
		c.set(ld.getYear(), ld.getMonthValue() - 1, ld.getDayOfMonth());
		Date date = c.getTime();
		return date;
	}

}
