package com.wergnet.wergnetoil.api.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("wergnet")
public class WergnetOilApiProperty {
	
	private String allowedOrigin = "http:localhost:8000";
	
	private final Safety safety = new Safety();
	
	public Safety getSafety() {
		return safety;
	}
	
	public String getAllowedOrigin() {
		return allowedOrigin;
	}

	public void setAllowedOrigin(String allowedOrigin) {
		this.allowedOrigin = allowedOrigin;
	}

	public static class Safety {

		private boolean enableHttps;

		public boolean isEnableHttps() {
			return enableHttps;
		}

		public void setEnableHttps(boolean enableHttps) {
			this.enableHttps = enableHttps;
		}
		
	}
	
}
