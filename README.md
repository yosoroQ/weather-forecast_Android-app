# weather-forecast_Android-app
<ul>
<li>一个简单的天气预报安卓app</li>
<li>主要的知识点是“XML解析”。</li>
<li>res/layout文件夹下的activity_main是其布局文件,利用LinearLayout线性布局包含底部三个按钮，单击切换不同城市信息，TextView和imageview显示城市和天气状况，图片显示当前天气。</li>
<li>布局右侧利用LinearLayout线性布局包含三个TextView分别显示温度，风力和pm2.5值。</li>
<li>src目录下的weather.xml文件包含三个城市的信息，标签有“id,temp,weather,name,pm,wind”。</li>
<li>src目录下WeatherInfo类，将weather.xml的六个属性封装成一个javabean代码。</li>
<li>src目录下WeatherService工具类，WeatherService工具类定义了getWeatherInfos()方法，包含解析xml文件的逻辑代码。</li>
<li>src目录下的界面交互（MainActivity.java），调用WeatherService工具类中的getWeatherInfos()方法解析weather.xml中的信息，并把读取到的数据存入List<WeatherInfo>集合，遍历数据.</li>
<li>（MainActivity.java）代码第24行调用了WeatherService的解析XML 文件方法,返回的是保存有天气信息的集合，然后把集合中的数据按照三个城市的信息分别放在不同的Map集合中，
  再把Map集合都存人 List集合中。当我们点击按钮时，会触发getMap(int number, int iconNumber)方法，三个不同的按钮会传进不同的int值用于取出 List中相对应的Map集合。
  最后从Map集合中把城市信息取出来分条展示在界面上。</li>
  <br>
  <b>演示效果</b>
![image](https://github.com/yosoroQ/weather-forecast_Android-app/blob/main/%E5%A4%A9%E6%B0%94%E9%A2%84%E6%8A%A5.png)

</ul>
