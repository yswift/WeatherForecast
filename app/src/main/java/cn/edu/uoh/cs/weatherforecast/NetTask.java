package cn.edu.uoh.cs.weatherforecast;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

/**
 * 定义异步任务，用于通过网络获取数据（Android不能在主线程中访问网络）
 * 重构后定义了一个独立的类，原来是MainActivity的内部类
 * Created by yan on 2016/4/15.
 */
class NetTask extends AsyncTask<String, Void, String> {
    MainActivity activity;
    IOException exception = null;

    NetTask(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            // 获取调用execute方法时传入的参数：城市名
            String cityName = params[0];
            Log.i(MainActivity.tag, "Get weather： " + cityName);
            // 获取天气信息，得到的是Json字符串
            String json = HttpTools.getWeatherInfo(cityName);
            Log.i(MainActivity.tag, json);
            return json;
        } catch (IOException e) {
            // 如果有异常，记录
            exception = e;
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (exception == null) {
            // 显示获取的信息
            // TextView txtInfo = (TextView) findViewById(R.id.txtInfo);
            // txtInfo.setText(result);
            activity.showForecastList(result);
        } else {
            // 如果有异常，显示错误消息
            activity.showError("获取天气信息失败：" + exception.getMessage());
        }
    }
}
