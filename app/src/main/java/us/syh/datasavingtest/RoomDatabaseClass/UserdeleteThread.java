package us.syh.datasavingtest.RoomDatabaseClass;

import android.content.Context;

import java.util.List;

public class UserdeleteThread  extends Thread {
    private Context context;
    public UserdeleteThread(Context context){
        this.context=context;
    }
    public void run(){
        AppDatabase mdb=AppDatabase.getAppDatabase(context);
        mdb.userDao().delete(mdb.userDao().getOne());
    }
}
