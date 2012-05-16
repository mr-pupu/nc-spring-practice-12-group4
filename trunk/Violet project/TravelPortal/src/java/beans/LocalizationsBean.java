package beans;

import java.nio.charset.Charset;
import java.util.*;

public class LocalizationsBean {
	private static Map<String, String> localizations = new HashMap<String,String>();
	private static String bundlePath = "localizations.text";
	public LocalizationsBean(){
        Locale.setDefault(new Locale("en", "US"));
		List<Locale> locales = new ArrayList<Locale>();
		locales.add(new Locale("en", "EN"));   //default
		locales.add(new Locale("ru", "RU"));
		locales.add(new Locale("ua", "UA"));
		for (Locale locale : locales) {
			ResourceBundle resourceBundle = ResourceBundle.getBundle(bundlePath, locale);
			localizations.put(resourceBundle.getString("locale.name"), resourceBundle.getString("locale.code"));
		}
	}
	public Map<String, String> getLocalizations() {
		return localizations;
	}
	public static boolean checkBundle(String boundle){
		return localizations.containsValue(boundle);
	}
	public String getBundlePath() {
		return bundlePath;
	}
}
