package com.codeenginestudio.elearning.util;

public class CommonUtil {

	public static int getInt(Object number) {

		if (number == null) {

			return 0;
		}

		if (number instanceof Integer) {

			return ((Integer) number).intValue();
		}

		if (number instanceof Long) {

			return ((Long) number).intValue();
		}

		return Integer.valueOf(String.valueOf(number));
	}
}
