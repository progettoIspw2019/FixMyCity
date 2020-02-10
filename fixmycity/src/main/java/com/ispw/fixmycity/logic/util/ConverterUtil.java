package com.ispw.fixmycity.logic.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.control.DatePicker;

public class ConverterUtil {

	public static Date dateFromDatePicker(DatePicker datePicker) {
		LocalDate ld = datePicker.getValue();
		Calendar c = Calendar.getInstance();
		c.set(ld.getYear(), ld.getMonthValue() - 1, ld.getDayOfMonth());
		return c.getTime();
	}

	public static byte[] byteArrayFromImage(File file) {

		byte[] b = null;

		try (FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath())) {
			b = fileInputStream.readAllBytes();
		} catch (IOException e) {
			Logger.getLogger("fixmycity").log(Level.SEVERE, e.toString());
		}
		return b;

	}

	private ConverterUtil() {

	}

}