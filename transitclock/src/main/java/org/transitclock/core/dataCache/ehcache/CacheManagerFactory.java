package org.transitclock.core.dataCache.ehcache;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.ehcache.CacheManager;
import org.ehcache.StateTransitionException;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;
import org.ehcache.xml.exceptions.XmlConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.transitclock.config.StringConfigValue;

public class CacheManagerFactory {
	
	public static CacheManager singleton = null;
	
	private static StringConfigValue configPath=new StringConfigValue("org.transitclock.core.dataCache.ehcache.configpath",null,"Path to ehcache.xml config file"); 
	private static final Logger logger = LoggerFactory
			.getLogger(CacheManagerFactory.class);
	public static CacheManager getInstance() {
															
		if (singleton == null) {
			URL xmlConfigUrl = null;
			if(configPath.getValue()==null||configPath.getValue().isEmpty())
				xmlConfigUrl = CacheManagerFactory.class.getClassLoader().getResource("ehcache.xml");
			else
			{
				try {
					xmlConfigUrl =  new File(configPath.getValue()).toURI().toURL();
				} catch (MalformedURLException e) {
					logger.error("Could not access ehcache config file.", e);
				}
			}			
			if(xmlConfigUrl!=null)
			{
				try {
					XmlConfiguration xmlConfig = new XmlConfiguration(xmlConfigUrl);				
					singleton = CacheManagerBuilder.newCacheManager(xmlConfig);
					singleton.init();
				} catch (XmlConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (StateTransitionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}		
		return singleton;
	}
}
