package com.harium.etyl.util;

import java.lang.reflect.Field;

public class ReflectionUtils {

	/**
	 * Equivalent to set method 
	 * Based on answer at: http://stackoverflow.com/a/14374995
	 * @param object
	 * @param fieldName
	 * @param fieldValue
	 * @return success
	 */
	public static boolean set(Object object, String fieldName, Object fieldValue) {
	    Class<?> clazz = object.getClass();
	    while (clazz != null) {
	        try {
	            Field field = clazz.getDeclaredField(fieldName);
	            field.setAccessible(true);
	            field.set(object, fieldValue);
	            return true;
	        } catch (NoSuchFieldException e) {
	            clazz = clazz.getSuperclass();
	        } catch (Exception e) {
	            throw new IllegalStateException(e);
	        }
	    }
	    return false;
	}
	
	/**
	 * Equivalent to get method 
	 * Based on answer at: http://stackoverflow.com/a/14374995
	 * @param object
	 * @param fieldName
	 * @return field value
	 */
	@SuppressWarnings("unchecked")
	public static <V> V get(Object object, String fieldName) {
	    Class<?> clazz = object.getClass();
	    while (clazz != null) {
	        try {
	            Field field = clazz.getDeclaredField(fieldName);
	            field.setAccessible(true);
	            return (V) field.get(object);
	        } catch (NoSuchFieldException e) {
	            clazz = clazz.getSuperclass();
	        } catch (Exception e) {
	            throw new IllegalStateException(e);
	        }
	    }
	    return null;
	}
	
	public static Class<?> getArrayClass(Class<?> clazz) {
		Class<?> arrayClass = clazz;
		
		try {
			arrayClass = Class.forName("[L" + arrayClass.getName() + ";");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return arrayClass;
	}
	
	
}
