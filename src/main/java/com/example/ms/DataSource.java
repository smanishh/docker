package com.example.ms;

/*@Component
@ConfigurationProperties(prefix="spring.datasource")*/
public class DataSource {
	 private String url;
     private String username;
     private String password;
	public String getUrl() {
		return url;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
     
     

}
