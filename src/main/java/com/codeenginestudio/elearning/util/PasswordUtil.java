package com.codeenginestudio.elearning.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtil {

	public static String encode(String password) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);
		return passwordEncoder.encode(password);
	}
}
