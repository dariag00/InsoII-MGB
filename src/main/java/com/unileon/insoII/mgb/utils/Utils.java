package com.unileon.insoII.mgb.utils;

public class Utils {

	
	public static String getCurrencySimbol(int currency) {
		
		switch(currency) {
			case Constants.CURRENCY_EURO:
				return "€";
			case Constants.CURRENCY_DOLLAR:
				return "$";
			case Constants.CURRENCY_POUND:
				return "";
			default:
				return "$";
		}
		
	}
}
