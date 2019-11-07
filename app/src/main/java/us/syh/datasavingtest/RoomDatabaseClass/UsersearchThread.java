package us.syh.datasavingtest.RoomDatabaseClass;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

public class UsersearchThread extends Thread {
    private Context context;
    private TextView textView_db_console;
    public UsersearchThread(Context context, TextView textView_db_console){
        this.context=context;
        this.textView_db_console=textView_db_console;
    }
    public void run(){
        AppDatabase mdb=AppDatabase.getAppDatabase(context);
        List<UserEntity> list=mdb.userDao().getAll();
        String text="Key|||Value\n";
        for (int i=0;i<list.size();i++){
            text+=list.get(i).firstName+"|||"+list.get(i).lastName+"\n";
        }
        textView_db_console.setText(text);
    }
}