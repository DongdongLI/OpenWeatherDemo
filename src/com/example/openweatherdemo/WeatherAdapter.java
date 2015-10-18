package com.example.openweatherdemo;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class WeatherAdapter extends ArrayAdapter<Weather>{
	List<Weather> mData;
	Context mContext;
	int mResource;
	
	public WeatherAdapter(Context context, int resource,List<Weather> objects) {
		super(context, resource, objects);
		this.mContext=context;
		this.mData=objects;
		this.mResource=resource;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(mResource, parent, false);
		}
		Weather weather = mData.get(position);

		TextView weatherDesc = (TextView) convertView
				.findViewById(R.id.weatherDesc);// set text
		weatherDesc.setText(weather.getWeatherDesc());

		/*
		 * More to go*/
		TextView dateView=(TextView)convertView.findViewById(R.id.dateTxt);
		dateView.setText(weather.getDate());
		
		TextView maxView=(TextView)convertView.findViewById(R.id.maxTemp);
		maxView.setText(weather.getMax_temp()+"");
		
		TextView minView=(TextView)convertView.findViewById(R.id.minTemp);
		minView.setText(weather.getMin_temp()+"");
		
		TextView humidityView=(TextView)convertView.findViewById(R.id.humidity);
		humidityView.setText(weather.getHumidity()+ "");
		
		return convertView;
	}
}
