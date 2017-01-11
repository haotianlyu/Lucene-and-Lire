package com.luceneutil;
//用于读取lucene的基本参数
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;

public class Loadluceneconfig {
	private static HashMap<String, String> LuMap=new HashMap<String,String>();
	
	public static String getluceneValue(String name){
		return LuMap.get(name);
	}
	
	public static void load(String path){
		try {
			//鍔犺浇閰嶇疆dbconfig.properties
			Properties pro=new Properties();
			//鍔犺浇鏂囦欢
			pro.load(new FileInputStream(path));
			//鍙栧��
			String dirposition=pro.getProperty("dirposition");
			System.out.println(dirposition);
			String lireposition=pro.getProperty("lireposition");
			LuMap.put("dirposition", dirposition);
			LuMap.put("lireposition", dirposition);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
