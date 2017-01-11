package com.factory;

import dao.FactoryDao;

public class Factory {
	public static FactoryDao getFactoryInstance(String type){
		FactoryDao f=null;
		try{
			f=(FactoryDao)Class.forName("dao."+type).newInstance();
		} catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return f;
	}
}
