package cn.example.weather;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlSerializer;

import android.R.xml;
import android.app.Activity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.example.weather.R;

public class MainActivity extends Activity implements OnClickListener {
	private TextView select_city, select_weather, select_temp, select_wind,
			select_pm;
	private Map<String, String> map;
	private List<Map<String, String>> list;
	private String temp, weather, name, pm, wind;
	private ImageView icon;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// ��ʼ���ı��ؼ�
		select_city = (TextView) findViewById(R.id.select_city);
		select_weather = (TextView) findViewById(R.id.select_weather);
		select_temp = (TextView) findViewById(R.id.temp);
		select_wind = (TextView) findViewById(R.id.wind);
		select_pm = (TextView) findViewById(R.id.pm);
		icon = (ImageView) findViewById(R.id.icon);

		findViewById(R.id.city_sh).setOnClickListener(this);
		findViewById(R.id.city_bj).setOnClickListener(this);
		findViewById(R.id.city_Harbin).setOnClickListener(this);

		try {
			// �����ϱ�д�õĽ�������,weather.xml�������Ŀ¼�£�ʹ������������м���
			// infos����ÿ�����е�������Ϣ���ϣ��������������Ҫ���������ݡ�
			List<WeatherInfo> infos = WeatherService
					.getWeatherInfos(MainActivity.class.getClassLoader()
							.getResourceAsStream("weather.xml"));
			// ѭ����ȡinfos�е�ÿһ������
			File file=new File("/sdcard/weather.xml");
			FileOutputStream fos=new FileOutputStream(file);
			XmlSerializer serializer = Xml.newSerializer();
			serializer.setOutput(fos, "UTF-8");
			serializer.startDocument("UTF-8", true);
				serializer.startTag(null, "infos");
					for (WeatherInfo info:infos) {
						serializer.startTag(null, "city");
						serializer.attribute(null, "id", ""+info.getId());
							serializer.startTag(null, "temp");
							serializer.text(info.getTemp());
							serializer.endTag(null, "temp");
							
							serializer.startTag(null, "weather");
							serializer.text(info.getWeather());
							serializer.endTag(null, "weather");
							
							serializer.startTag(null, "name");
							serializer.text(info.getName());
							serializer.endTag(null, "name");
							
							serializer.startTag(null, "pm");
							serializer.text(info.getPm());
							serializer.endTag(null, "pm");
							
							serializer.startTag(null, "wind");
							serializer.text(info.getWind());
							serializer.endTag(null, "wind");
						serializer.endTag(null, "city");
						
					}
				
				serializer.endTag(null, "infos");
			serializer.endDocument();
			
			list = new ArrayList<Map<String, String>>();
			for (WeatherInfo info : infos) {
				map = new HashMap<String, String>();
				map.put("temp", info.getTemp());
				map.put("weather", info.getWeather());
				map.put("name", info.getName());
				map.put("pm", info.getPm());
				map.put("wind", info.getWind());
				list.add(map);
			}
			// ��ʾ������Ϣ���ı��ؼ���
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "������Ϣʧ��", 0).show();
		}

		getMap(1, R.drawable.sun);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.city_sh:
			getMap(0, R.drawable.cloud_sun);
			break;
		case R.id.city_bj:
			getMap(1, R.drawable.sun);
			break;
		case R.id.city_Harbin:
			getMap(2, R.drawable.clouds);
			break;
		}
	}

	private void getMap(int number, int iconNumber) {
		Map<String, String> bjMap = list.get(number);
		temp = bjMap.get("temp");
		weather = bjMap.get("weather");
		name = bjMap.get("name");
		pm = bjMap.get("pm");
		wind = bjMap.get("wind");
		select_city.setText(name);
		select_weather.setText(weather);
		select_temp.setText("" + temp);
		select_wind.setText("����  : " + wind);
		select_pm.setText("pm: " + pm);
		icon.setImageResource(iconNumber);
	}
}
