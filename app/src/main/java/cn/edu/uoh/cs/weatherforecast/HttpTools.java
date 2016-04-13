package cn.edu.uoh.cs.weatherforecast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;

/**
 * 获取天气预报信息的类，
 * <p/>
 * Created by yan on 2016/4/12.
 */
public class HttpTools {
    /**
     * 获取天气预报信息的方法，调用此方法可以获取指定城市的天气信息。
     * 用这个地区获取：http://wthrcdn.etouch.cn/weather_mini?city=城市名
     *
     * @param cityName 城市名称
     * @return 获取到的字符串JSON格式。
     */
    public static String getWeatherInfo(String cityName) throws IOException {
        // 获取天气的URL
        URL url = new URL("http://wthrcdn.etouch.cn/weather_mini?city=" + cityName);
        URLConnection urlc = url.openConnection();
        InputStream is = urlc.getInputStream();
        // 检查获取的结果是否是被压缩过的
        if ("gzip".equals(urlc.getContentEncoding())) {
            // 如果是用GZIPInputStream包裹节压缩
            is = new GZIPInputStream(is);
        }
        String json = readToEnd(is);
        return json;
    }

    /**
     * 从流中读取所有内容，直到流结束，并转换为String格式。
     * @param is 输入流
     * @return 读取到字符串
     * @throws IOException
     */
    private static String readToEnd(InputStream is) throws IOException {
        final int len = 1024;
        // 定义读取的缓冲区
        byte data[] = new byte[len];
        // 用ByteArrayOutputStream把byte[] 转换为String
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while (true) {
            int rlen = is.read(data);
            if (rlen == -1) {
                break;
            }
            bos.write(data, 0, rlen);
        }
        return bos.toString("UTF-8");
    }
}
