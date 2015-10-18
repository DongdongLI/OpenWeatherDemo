package com.example.openweatherdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText cityTxt;
	Button searchBtn;
	ListView weatherListview;
	ArrayAdapter<Weather> adapter;
	ArrayList<Weather> weatherList=new ArrayList<Weather>();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        cityTxt=(EditText)findViewById(R.id.CityText);
        searchBtn=(Button)findViewById(R.id.searchBtn);
        weatherListview=(ListView)findViewById(R.id.weatherListview);
        adapter=new WeatherAdapter(this, R.layout.weatherlistitem, weatherList);
        
        weatherListview.setAdapter(adapter);
        adapter.setNotifyOnChange(true);
        
        searchBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String cityName="";
				if(cityTxt.getText().toString().trim().length()==0)
					Toast.makeText(getApplicationContext(), "Please type in the name of the city...", Toast.LENGTH_SHORT).show();
				else
				{
					cityName=cityTxt.getText().toString().trim();
					new GetData().execute("http://api.openweathermap.org/data/2.5/forecast/?q="+cityName+",us&APPID=c2f71a8823f6469b8d0d9078d3cdb3ad");
				}
				
			}
		});
    }
    
    class GetData extends AsyncTask<String, Void, String>
    {
    	BufferedReader br;
		@Override
		protected String doInBackground(String... params) {
			Log.d("demo1","start in background");
			try {
				URL url=new URL(params[0]);
				HttpURLConnection con=(HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");//POST
				
				br=new BufferedReader(new InputStreamReader(con.getInputStream()));
				
				StringBuilder sb=new StringBuilder();
				String line="";
				while((line=br.readLine())!=null)
				{
					sb.append(line+"\n");
				}
				
				return sb.toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			return null;
		}
		@Override
		protected void onPostExecute(String result) {
			if(result!=null && result.length()!=0){
				Log.d("demo1","the result is: "+result);
				try {
					ArrayList<Weather> temp=WeatherParser.parsePersons(result);
					//Log.d("demo1",WeatherParser.parsePersons(result).toString());
//					weatherList.clear();
//					weatherList.addAll(temp);
					adapter.clear();
					adapter.addAll(temp);
					adapter.notifyDataSetChanged();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}else{
				Log.d("demo1","result empty");
			}
		}
		
	}
}
