package us.syh.datasavingtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;

import us.syh.datasavingtest.RoomDatabaseClass.AppDatabase;

public class DatabaseSaveDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datebase_save_data);
        this.setTitle("数据库保存数据");
        //创建或打开数据库
        AppDatabase db= Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"app_database").build();
    }
}
