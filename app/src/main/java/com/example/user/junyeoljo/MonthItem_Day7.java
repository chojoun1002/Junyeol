package com.example.user.junyeoljo;

/**
 * 일자 정보를 담기 위한 클래스 정의
 */
public class MonthItem_Day7 {

	private int dayValue;
	private String weatherValue=null;//날씨정보

	public MonthItem_Day7() {

	}
	//날씨정보 저장을 위해 인자와 변수를 만들었다.
	public MonthItem_Day7(int day, String weather) {
		dayValue = day;
		weatherValue = weather;
	}

	public int getDay() {
		return dayValue;
	}

	public void setDay(int day) {
		this.dayValue = day;
	}

	public String getWeather() {//날씨가져오기
		return weatherValue;
	}

	public void setWeather(String weather) {	//날씨 넣어주기
		this.weatherValue = weather;
	}

}
