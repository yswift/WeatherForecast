package cn.edu.uoh.cs.weatherforecast;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 城市列表，提供信息保存，获取功能。
 * Created by yan on 2016/4/15.
 */
public class CityList {
    /**
     * 城市列表的修改监听器接口，当城市列表发生改变时，通知监听器。
     * 参考：观察者设计模式
     */
    public interface ChangeListener {
        void onChange();
    }

    /**
     * 城市列表存储key
     */
    public final static String StoreListKey = "cn.edu.uoh.cs.weatherforecast.citynameList";

    private ArrayList<String> cityList = new ArrayList<>();
    private ChangeListener listener;

    private CityList() {
        // 阻止New
    }

    /**
     * 从保存的数据中获取城市列表
     * @param activity activity
     * @return CityList
     */
    public static CityList load(Activity activity) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        String cityNames = sharedPref.getString(StoreListKey, "[蒙自]");
        // 转换字符串到数组
        Gson gson = new Gson();
        String[] cityArray = gson.fromJson(cityNames, String[].class);
        // 建立CityList对象并赋值
        CityList list = new CityList();
        Collections.addAll(list.cityList, cityArray);
        return list;
    }

    /**
     * 保存城市列表
     * @param activity
     */
    public void save(Activity activity) {
        // 转换成数组
        String[] cityArray = cityList.toArray(new String[cityList.size()]);
        // 转换成Json字符串
        Gson gson = new Gson();
        String cityJson = gson.toJson(cityArray);
        // 保存
        SharedPreferences sharedPref =activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(MainActivity.StoreListKey, cityJson);
        editor.apply();
    }

    /**
     * 城市列表
     * @return
     */
    List<String> getCityList() {
        return cityList;
    }

    /**
     * 列表中的城市数量
     * @return
     */
    public int size() {
        return cityList.size();
    }

    /**
     * 注册列表改变监听器。
     * @param listener 监听器
     */
    public void setChangeListener(ChangeListener listener) {
        this.listener = listener;
    }

    /**
     * 当城市列表改变时，调用此方法，在这个方法中通知已注册监听器
     */
    private void onChange() {
        if (listener != null) {
            this.listener.onChange();
        }
    }

    /**
     * 添加城市
     * @param city 城市名称
     */
    public void add(String city) {
        cityList.add(city);
        onChange();
    }

    /**
     * 删除指定位置的城市
     * @param position 指定城市位置
     */
    public void remove(int position) {
        cityList.remove(position);
        onChange();
    }
}
