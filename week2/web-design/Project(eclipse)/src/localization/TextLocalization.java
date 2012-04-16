package localization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class TextLocalization {
	
	private static List<Locale> locales = new ArrayList<Locale>();
	private static List<String> localeNames = new ArrayList<String>();
	private static Map<Locale,ResourceBundle> boundles = new HashMap<Locale,ResourceBundle>();
	private static Map<String,String> namesAndCodes = new HashMap<String, String>();
 
	static {	
		locales.add(new Locale("en", "EN"));   //default
		locales.add(new Locale("ru", "RU"));
		locales.add(new Locale("ua", "UA"));
		
		for (Locale locale : locales) {
			ResourceBundle resourceBundle = ResourceBundle.getBundle("localization.text", locale);
			boundles.put(locale, resourceBundle);	
			localeNames.add(resourceBundle.getString("locale.name"));
			namesAndCodes.put(resourceBundle.getString("locale.name"), resourceBundle.getString("locale.code"));
		}
	}
	
	public static ResourceBundle getBoundle(String localeString){
		if ((localeString != null) && (localeString.length() == 5)){
			Locale currLocale = new Locale(localeString.substring(0,2),localeString.substring(3,5));
			for (Locale locale : locales) {
				if (locale.equals(currLocale))
					return boundles.get(locale);
			}
		}
		System.out.println(localeString.length());
		return boundles.get(locales.get(0));
	}
	
	public static ResourceBundle getDefoultBoundle(){
		return boundles.get(locales.get(0));
	}
	
	public static Map<String,String> getLocaleNamesAndCodes(){
		return namesAndCodes;
	}	
}
