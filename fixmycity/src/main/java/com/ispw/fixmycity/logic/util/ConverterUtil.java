package com.ispw.fixmycity.logic.util;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;

public class ConverterUtil {

	public static Date dateFromDatePicker(DatePicker datePicker) {
		LocalDate ld = datePicker.getValue();
		Calendar c = Calendar.getInstance();
		c.set(ld.getYear(), ld.getMonthValue() - 1, ld.getDayOfMonth());
		return c.getTime();
	}

	public static byte[] byteArrayFromImage(Image image) {

		int h = (int) image.getHeight();
		int w = (int) image.getWidth();

		byte[] buf = new byte[w * h * 4];

		image.getPixelReader().getPixels(0, 0, w, h, PixelFormat.getByteBgraInstance(), buf, 0, w * 4);

		return buf;
	}

}
