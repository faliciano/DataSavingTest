package us.syh.datasavingtest.RoomDatabaseClass;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class UserEntity {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name="first_name")
    public String firstName;
    @ColumnInfo(name="last_name")
    public String lastName;
    /*初始化实体使用*/
    public UserEntity(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    /*更新实体属性*/
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) { this.lastName = lastName; }
}
