package us.syh.datasavingtest.RoomDatabaseClass;

import android.content.Context;

public class UserInsertThread extends Thread {
    private Context context;
    private String firstName,lastName;
    public UserInsertThread(Context context,String firstName,String lastName){
        this.context=context;
        this.firstName=firstName;
        this.lastName=lastName;
    }
    public void run(){
        AppDatabase mdb=AppDatabase.getAppDatabase(context);
        UserEntity newUser=new UserEntity(firstName,lastName);
        mdb.userDao().insertAll(newUser);
    }
}
