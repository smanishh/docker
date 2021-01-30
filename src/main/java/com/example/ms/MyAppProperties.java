package com.example.ms;

import java.util.Currency;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app")
public class MyAppProperties {
  private int refreshRate;
  private TimeUnit refreshTimeUnit;
  private Currency tradeCurrency;
public int getRefreshRate() {
	return refreshRate;
}
public TimeUnit getRefreshTimeUnit() {
	return refreshTimeUnit;
}
public Currency getTradeCurrency() {
	return tradeCurrency;
}
public void setRefreshRate(int refreshRate) {
	this.refreshRate = refreshRate;
}
public void setRefreshTimeUnit(TimeUnit refreshTimeUnit) {
	this.refreshTimeUnit = refreshTimeUnit;
}
public void setTradeCurrency(Currency tradeCurrency) {
	this.tradeCurrency = tradeCurrency;
}
  
  
	

}
