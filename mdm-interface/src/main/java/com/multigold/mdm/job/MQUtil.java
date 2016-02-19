package com.multigold.mdm.job;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.multigold.common.util.common.WebAppContext;

public class MQUtil {
	
	
	
	/**
	 * 
	 * 获取MQ的 配置文件。
	 * @return
	 */
	public static Properties getProperties() {   
		Properties props = null;
		InputStream in = null;
        try {   
        	 props = new Properties();   
        	 String path = WebAppContext.getInstance().getAppPathString() + "WEB-INF"+File.separator+"classes" +File.separator+"config"+File.separator+"mq.properties";
        	 System.out.println("#######################[mq.properties PATH:"+path+"](MQUtil.java)#####################");
        	 in = new BufferedInputStream(new FileInputStream(path)); 
        	 props.load(in);   
        } catch (FileNotFoundException e) {   
            e.printStackTrace();   
        } catch (IOException e) {   
            e.printStackTrace();   
        } finally{
        	try {
				if(in != null){
					in.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }  
        return props;   
    }
}
