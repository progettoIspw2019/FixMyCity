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

import com.ispw.fixmycity.logic.view.javafx.Alerter;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Alert.AlertType;

public class ConverterUtil {

	public static Date dateFromDatePicker(DatePicker datePicker) {
		LocalDate ld = datePicker.getValue();
		if (ld != null) {
			Calendar c = Calendar.getInstance();
			c.set(ld.getYear(), ld.getMonthValue() - 1, ld.getDayOfMonth());
			return c.getTime();
		}
		return null;
	}

	public static byte[] byteArrayFromImage(File file) {
		byte[] b = null;
		if(file == null) {
			Alerter.alert("No image uploaded!", "First, pick a picture for your user profile", AlertType.INFORMATION);
			return b;
		}

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
				hashtext = "0".concat(hashtext);
			}
			return hashtext;
		}

		catch (NoSuchAlgorithmException e) {
			Logger.getLogger("fixmycity").severe(e.toString());
			return null;
		}
	}

	private ConverterUtil() {

	}

}
