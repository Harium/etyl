package com.harium.etyl.util;

import java.io.File;

public class StringUtils {

	public static final String NEW_LINE = "\n";

	public static int countOccurrences(String text, char match) {
		int count = 0;
		for(int i=0;i<text.length();i++) {
			if(text.charAt(i) == match) {
				count++;
			}
		}
		return count;
	}
	
	public static String fileExtension(String path) {
		int token = path.lastIndexOf(".");
		String ext = path.substring(token+1,path.length()).toLowerCase();
		return ext;
	}
	
	public static String fileName(String path) {
		int token = path.lastIndexOf(File.separator);
		String filename = path.substring(token+1,path.length());
		return filename;
	}

	public static String formatFloat(float x, int i) {
		String decimals = Integer.toString(i);
		return String.format(java.util.Locale.US,"%."+decimals+"f", x); 
	}
	
}
