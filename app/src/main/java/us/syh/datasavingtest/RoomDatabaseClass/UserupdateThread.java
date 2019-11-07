package us.syh.datasavingtest.RoomDatabaseClass;

import android.content.Context;

import java.util.List;

public class UserupdateThread  extends Thread {
    private Context context;
    private String firstName,lastName;
    public UserupdateThread(Context context, String firstName, String lastName){
        this.context=context;
        this.firstName=firstName;
        this.lastName=lastName;
    }
    public void run(){
        AppDatabase mdb=AppDatabase.getAppDatabase(context);
        UserEntity one=mdb.userDao().getOne();
        mdb.userDao().getUser(one.uid).setFirstName(firstName);
        mdb.userDao().getUser(one.uid).setLastName(lastName);
    }
}