package us.syh.datasavingtest.RoomDatabaseClass;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase db;
    public static AppDatabase getAppDatabase(Context context){
        if(db==null) {
            db = Room.databaseBuilder(context, AppDatabase.class, "app_database").build();
        }
            return db;
    }
    public static void onDestroy() {
        db = null;
    }
    public abstract UserDao userDao();
}
