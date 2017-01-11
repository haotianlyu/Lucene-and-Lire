package com.luceneutil;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
//Listener类，用于在项目启动时读取参数
public class ListenerLoadConfig implements ServletContextListener {

	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		String path=event.getServletContext().getRealPath("/WEB-INF/config/luceneconfig.properties");
		Loadluceneconfig.load(path);
		System.out.println(luceneUtil.getSearch());
	}
}

