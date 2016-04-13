package cn.edu.uoh.cs.weatherforecast;

import com.google.gson.Gson;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by yan on 2016/4/13.
 */
public class UTJson2Object {
    @Test
    public void Json2Ojbect() throws Exception {
        // 获取Json
        String json = HttpTools.getWeatherInfo("蒙自");
        // 使用Gson工具
        Gson gson = new Gson();
        // 把Json字符串转换成对象
        WeatherInfo weatherInfo = gson.fromJson(json, WeatherInfo.class);
        // 测试status必须是1000
        assertEquals(Integer.valueOf(1000), weatherInfo.getStatus());
        // 测试desc必须是OK
        assertEquals("OK", weatherInfo.getDesc());

        Data data = weatherInfo.getData();
        System.out.println("输出当天天气：");
        System.out.println("城市：" + data.getCity());
        System.out.println("温度：" + data.getWendu());
        System.out.println("感冒指数：" + data.getGanmao());
        // 输出后几天的天气情况
        List<Forecast> forecastList = data.getForecast();
        for (Forecast f : forecastList) {
            System.out.println("date = " + f.getDate());
            System.out.println("温度 = " + f.getLow() + " - " + f.getHigh());
        }
    }
}
