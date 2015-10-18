package com.example.openweatherdemo;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class WeatherParser {
	static ArrayList<Weather> parsePersons(String in) throws JSONException
	{
		ArrayList<Weather> weatherlist= new ArrayList<Weather>();
		JSONObject root=new JSONObject(in);
		JSONArray weatherJSONArray=root.getJSONArray("list");
		
		Log.d("demo1","the length is"+weatherJSONArray.length());
		
		for(int i=0;i<weatherJSONArray.length();i++)
		{
			JSONObject weathersonObject=weatherJSONArray.getJSONObject(i);
//			Person person=new Person();
			Weather weather=new Weather();
//			person.setName(personsJsonObject.getString("name"));
			weather.setDate(weathersonObject.getString("dt_txt"));
			
			JSONObject main=weathersonObject.getJSONObject("main");
			weather.setMax_temp(main.getDouble("temp_max"));
			weather.setMin_temp(main.getDouble("temp_min"));
			weather.setHumidity(main.getDouble("humidity"));
			
			JSONArray wea=weathersonObject.getJSONArray("weather");
			weather.setWeatherDesc(wea.getJSONObject(0).getString("description"));
			
//			person.setId(personsJsonObject.getInt("id"));
//			person.setDepartment(personsJsonObject.getString("department"));
//			person.setAge(personsJsonObject.getInt("age"));
		
			
			//personslist.add();
			weatherlist.add(weather);
		}
		
		return weatherlist;
	}
}
