package us.syh.datasavingtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import us.syh.datasavingtest.RoomDatabaseClass.AppDatabase;
import us.syh.datasavingtest.RoomDatabaseClass.UserEntity;
import us.syh.datasavingtest.RoomDatabaseClass.UserInsertThread;
import us.syh.datasavingtest.RoomDatabaseClass.UserdeleteThread;
import us.syh.datasavingtest.RoomDatabaseClass.UsersearchThread;
import us.syh.datasavingtest.RoomDatabaseClass.UserupdateThread;

public class DatabaseSaveDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datebase_save_data);
        this.setTitle("数据库保存数据");
        //创建或打开数据库
        findViewById(R.id.button_db_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
                sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// a为am/pm的标记
                Date date = new Date();// 获取当前时间
                new UserInsertThread(DatabaseSaveDataActivity.this,sdf.format(date),sdf.format(date)).start();
                Toast.makeText(DatabaseSaveDataActivity.this,"已添加！",Toast.LENGTH_LONG).show();
            }
        });
        findViewById(R.id.button_db_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UserdeleteThread(DatabaseSaveDataActivity.this).start();
                Toast.makeText(DatabaseSaveDataActivity.this,"已删除第一条记录！",Toast.LENGTH_LONG).show();
            }
        });
        findViewById(R.id.button_db_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
                sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// a为am/pm的标记
                Date date = new Date();// 获取当前时间
                new UserupdateThread(DatabaseSaveDataActivity.this,sdf.format(date),sdf.format(date)).start();
                Toast.makeText(DatabaseSaveDataActivity.this,"已更新第一条记录！",Toast.LENGTH_LONG).show();
            }
        });
        findViewById(R.id.button_db_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView_db_console=findViewById(R.id.textView_db_console);
                new UsersearchThread(DatabaseSaveDataActivity.this,textView_db_console).start();
                Toast.makeText(DatabaseSaveDataActivity.this,"查询完成！",Toast.LENGTH_LONG).show();
            }
        });
    }
}
