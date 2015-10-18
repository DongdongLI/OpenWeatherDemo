package com.example.openweatherdemo;

public class Weather {
	/*
	 * */
	private double max_temp;
	private double min_temp;
	private String weatherDesc;
	private String date;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	private double humidity;
	public double getMax_temp() {
		return max_temp;
	}
	public void setMax_temp(double max_temp) {
		this.max_temp = max_temp;
	}
	public double getMin_temp() {
		return min_temp;
	}
	public void setMin_temp(double min_temp) {
		this.min_temp = min_temp;
	}
	public String getWeatherDesc() {
		return weatherDesc;
	}
	public void setWeatherDesc(String weatherDesc) {
		this.weatherDesc = weatherDesc;
	}
	public double getHumidity() {
		return humidity;
	}
	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}
	@Override
	public String toString() {
		return "Weather [max_temp=" + max_temp + ", min_temp=" + min_temp
				+ ", weatherDesc=" + weatherDesc + ", date=" + date
				+ ", humidity=" + humidity + "]";
	}
	
	
}
