package cn.edu.uoh.cs.weatherforecast;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;

public class CityListActivity extends AppCompatActivity {
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        InitListView();
    }

    /**
     * 初始化ListView，显示已保存的城市
     */
    private void InitListView() {
        // 获取保存的城市列表
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String cityNames = sharedPref.getString(MainActivity.StoreListKey, "[蒙自]");
        // 转换字符串到数组
        Gson gson = new Gson();
        String[] cityArray = gson.fromJson(cityNames, String[].class);
        // 建立List adapter，simple_list_item_1是已定义的layout
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        // 添加城市列表
        adapter.addAll(cityArray);
        // 设置ListView
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_city_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        // 单击删除按钮时删除选中的城市
        if (id == R.id.remove_city) {
            removeCity();
            return true;
        }
        // 单击添加按钮是显示添加UI
        if (id == R.id.add_city) {
            View view = findViewById(R.id.addLayout);
            view.setVisibility(View.VISIBLE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 删除选中的城市
     */
    private void removeCity() {
        ListView listView = (ListView) findViewById(R.id.listView);
        int position = listView.getCheckedItemPosition();
        if (position < 0 || position >= adapter.getCount()) {
            return;
        }
        String city = adapter.getItem(position);
        adapter.remove(city);
        saveCityList();
    }

    /**
     * 添加城市
     * @param view view
     */
    public void addCity(View view) {
        // 取得输入框中的城市名称
        EditText edtCity = (EditText) findViewById(R.id.edtCity);
        String cityName = edtCity.getText().toString();
        // 如果输入不是空，添加到列表
        if (cityName.trim().length() > 0) {
            adapter.add(cityName);
            saveCityList();
        }
        // 清空输入
        edtCity.setText("");
        // 隐藏输入框
        View addLayout = findViewById(R.id.addLayout);
        addLayout.setVisibility(View.GONE);
    }

    /**
     * 保存城市列表，当添加、删除完成后调用此方法。
     */
    private void saveCityList() {
        // 获取城市列表
        String[] cityArray = new String[adapter.getCount()];
        for (int i = 0; i < cityArray.length; i++) {
            cityArray[i] = adapter.getItem(i);
        }
        // 转换成Json字符串
        Gson gson = new Gson();
        String cityJson = gson.toJson(cityArray);
        // 保存
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(MainActivity.StoreListKey, cityJson);
        editor.apply();
    }
}
