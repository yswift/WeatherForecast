package cn.edu.uoh.cs.weatherforecast;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class CityListActivity extends AppCompatActivity {
    private CityList cityList;

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
        cityList = CityList.load(this);
        // 建立ListView的数据adapter
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cityList.getCityList());
        // 注册城市列表改变事件, 当城市列表改变时，通知adapter，
        cityList.setChangeListener(new CityList.ChangeListener() {
            @Override
            public void onChange() {
                adapter.notifyDataSetChanged();
            }
        });
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
        if (position < 0 || position >= cityList.size()) {
            return;
        }
        cityList.remove(position);
        cityList.save(this);
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
            cityList.add(cityName);
            cityList.save(this);
        }
        // 清空输入
        edtCity.setText("");
        // 隐藏输入框
        View addLayout = findViewById(R.id.addLayout);
        addLayout.setVisibility(View.GONE);
    }

}
