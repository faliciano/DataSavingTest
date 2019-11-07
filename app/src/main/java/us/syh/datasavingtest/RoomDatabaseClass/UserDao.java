package us.syh.datasavingtest.RoomDatabaseClass;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<UserEntity> getAll();

    @Query("SELECT * FROM user LIMIT 1")
    UserEntity getOne();

    @Query("SELECT * FROM user WHERE uid = :uid LIMIT 1")
    UserEntity getUser(int uid);

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<UserEntity> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE first_name LIKE :first_name AND last_name LIKE :last_name LIMIT 1")
    UserEntity findByName(String first_name,String last_name);

    @Insert
    void insertAll(UserEntity... users);

    @Delete
    void delete(UserEntity user);
}
