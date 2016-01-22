package com.english;

public class GlobalFunctions {
	
	public static boolean isEmailAddressValid(String email) {
		return !email.contains(" ") && email.matches(".+@.+\\.[a-z]+");
	}
	
	public static boolean isValidPesel(String pesel) {
		return (pesel.length()==11);
	}
	
	public static void DEBUG(String value) {
		
		System.out.println("D: " + value);
	}
}
