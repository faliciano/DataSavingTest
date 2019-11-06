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

public class MultiSharedPreferenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_shared_preference);
        //一个activty包含多个sharedpreference
        //fragment中使用getacitivty获取content
        //Content content=getActivity();
        //开启sharedpreference
        final SharedPreferences sharedPref=this.getSharedPreferences("us.syh.datasavingtest.PREFERENCE_FILE_KEY",Context.MODE_PRIVATE);
        //写数据
        findViewById(R.id.button_write_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor=sharedPref.edit();
                editor.putInt("hight",200);
                editor.commit();
            }
        });

        findViewById(R.id.button_read_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //读数据
                String allData=sharedPref.getAll().toString();
                //Int hight=sharedPref.getInt("hight",100);//default value
                TextView console=findViewById(R.id.textView_console_one);
                console.setText(allData);
            }
        });
    }

}
