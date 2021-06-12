package com.css.pos.common.util;


import java.util.Locale;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.context.FacesContext;

public class POSUtil {
	public static String getRandomLogoTitle(){
		return UUID.randomUUID().toString().replaceAll("-","").substring(1,10)+new java.util.Date().getSeconds();
	}

	public static Object getPropertyFromResource(String key) {
		ResourceBundle b = ResourceBundle.getBundle("com/css/pos/common/resources/settings");
		return b.getString(key);
	}
	public static Object getLocalizedMessage(String key, FacesContext fc) {
		Object lang = fc.getExternalContext().getSessionMap().get("current_lang");
		String currentLnguage  = (lang!=null)? lang.toString() : Locale.getDefault().toString();
		ResourceBundle b = ResourceBundle.getBundle("com/css/pos/common/resources/messages", new Locale(currentLnguage));
		return b.getString(key);
	}
	public static void main(String[] args) {
		System.out.println(getPropertyFromResource("company.logo.upload_folder"));
	}
	public static boolean isItAvalidEmail(String email) {
		Pattern pattern = Pattern.compile(POSConstants.EMAIL_PATTERN);
		try {
			Matcher matcher = pattern.matcher(email);
			return matcher.matches();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
}
