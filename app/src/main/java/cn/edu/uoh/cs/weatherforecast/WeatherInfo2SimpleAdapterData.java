package cn.edu.uoh.cs.weatherforecast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 把WeatherInfo转换成List<? extends Map<String, ?>>类型的对象，用于为ListView的SimpleAdapter提供数据
 *
 * Created by yan on 2016/4/14.
 */
public class WeatherInfo2SimpleAdapterData {
    public static List<Map<String, Object>> convert(WeatherInfo weatherInfo) {
        List<Map<String, Object>> list = new ArrayList<>();
        if (weatherInfo == null || weatherInfo.getData() == null || weatherInfo.getData().getForecast() == null) {
            return list;
        }
        // 获取每天的天气信息
        List<Forecast> forecastList = weatherInfo.getData().getForecast();
        // 把Forecast转换成Map格式，添加到List<Map<String, Object>>中
        for (Forecast f : forecastList) {
            // 创建每行的map对象
            Map<String, Object> data = new HashMap<>();
            // 设置值
            data.put("date", f.getDate());
            // 温度区间，用正则表达式去掉不必要的文字
            String t = f.getLow() + "-" + f.getHigh();
            t = t.replaceAll("高温|低温|\\s*", "");
            data.put("temperature", t);
            data.put("weather", f.getType());
            data.put("wind", f.getFengli());
            list.add(data);
        }
        return list;
    }
}
