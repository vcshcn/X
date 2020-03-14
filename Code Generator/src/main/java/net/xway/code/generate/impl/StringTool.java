package net.xway.code.generate.impl;

public  class StringTool {

	public String toLowerCase(String s) {
		return s.toLowerCase();
	}
	
	public String toUpperCase(String s) {
		return s.toUpperCase();
	}
	
	public String firstLetterUpperCase(String s) {
		return Character.toUpperCase(s.charAt(0)) + s.substring(1);
	}
	
	public String el(String el) {
		return "${" + el + "}";
	}
	
	public String firstLetterLowerCase(String s) {
		return Character.toLowerCase(s.charAt(0)) + s.substring(1);
	}
	
	public boolean startsWith(String s, String prefix) {
		return s.startsWith(prefix);
	}
	
}
