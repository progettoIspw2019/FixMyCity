package com.ispw.fixmycity.logic.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

	public static String md5FromString(String input) {
		try {

			MessageDigest md = MessageDigest.getInstance("MD5");
	
			byte[] messageDigest = md.digest(input.getBytes());

			BigInteger no = new BigInteger(1, messageDigest);

			String hashtext = no.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		}

		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}


	private ConverterUtil() {

	}

}
