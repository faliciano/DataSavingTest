package us.syh.datasavingtest;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MultiSharedPreferenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_shared_preference);
        this.setTitle("方法一，多SharedPreference");
        //一个activty包含多个sharedpreference
        //fragment中使用getacitivty获取content
        //Content content=getActivity();
        //开启sharedpreference
        final SharedPreferences sharedPref=this.getSharedPreferences("us.syh.datasavingtest.PREFERENCE_FILE_KEY",Context.MODE_PRIVATE);
        final SharedPreferences sharedPref2=this.getSharedPreferences("us.syh.datasavingtest.OTHER_FILE_KEY",Context.MODE_PRIVATE);
        //写数据
        findViewById(R.id.button_write_multi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
                sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// a为am/pm的标记
                Date date = new Date();// 获取当前时间
                SharedPreferences.Editor editor=sharedPref.edit();
                editor.putInt(sdf.format(date)+"hight",200);
                editor.commit();
            }
        });

        findViewById(R.id.button_read_multi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //读数据
                String allData=sharedPref.getAll().toString();
                //Int hight=sharedPref.getInt("hight",100);//default value
                TextView console=findViewById(R.id.textView_console_multi);
                console.setText(allData);
            }
        });
        //写数据
        findViewById(R.id.button_write_multi_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
                sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// a为am/pm的标记
                Date date = new Date();// 获取当前时间
                SharedPreferences.Editor editor=sharedPref2.edit();
                editor.putInt(sdf.format(date)+"width",100);
                editor.commit();
            }
        });

        findViewById(R.id.button_read_multi_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //读数据
                String allData=sharedPref2.getAll().toString();
                //Int hight=sharedPref.getInt("hight",100);//default value
                TextView console=findViewById(R.id.textView_console_multi_2);
                console.setText(allData);
            }
        });
    }

}
