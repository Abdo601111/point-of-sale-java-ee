package com.css.pos.common.util;

public class Decoder {
	private static String keyStr = new String("12css12");
	private static int level = 0;
	public static String encode(String value)
	{
		IceKey ik = new IceKey(level);
        return ik.encode(value);  
	}
	public static void main(String[] args) {
		System.out.println(">>>  "+encode("1234")); 
		System.out.println("<<<<<"+decode("9A9BC8CBEC0103EB"));
		
	}
	
	public static  String decode(String value)
	{
		IceKey ik = new IceKey(level);
        return ik.decode(value);  
	}
}