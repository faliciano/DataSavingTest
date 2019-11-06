package us.syh.datasavingtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OneSavePreferenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_save_preference);
        //一个acitivty仅需要一个sharedpreference时可通过以下方法省略sharedpreference文件名
        final SharedPreferences sharedPref=this.getPreferences(Context.MODE_PRIVATE);
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
