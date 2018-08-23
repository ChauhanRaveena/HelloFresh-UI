package common.Config;

import java.util.Properties;


public class BaseConfiguration {

	    public Properties configFile;
	    
	    
	    public BaseConfiguration() 
	    {
	    	//System.setProperty("org.uncommons.reportng.escape-output", "false");
	        configFile = new java.util.Properties();
	        
	        try {
	            configFile.load(this.getClass().getClassLoader().getResourceAsStream("common/Resources/config.properties"));
	        } catch(Exception ex){
	            ex.printStackTrace();
	        }
	        
	        
	        try {
	            configFile.load(this.getClass().getClassLoader().getResourceAsStream("common/Resources/data.properties"));
	           
	        } catch(Exception ex){
	            ex.printStackTrace();
	        }
	    }

	    public String getPropertyConfig(String key)
	    {
	        return this.configFile.getProperty(key);
	    }
	    
	    public String getPropertyData(String key)
	    {
	        return this.configFile.getProperty(key);
	    }
	    	
	    
	}

