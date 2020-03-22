package com.rezkyb.deadlinelist;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface DeadlineDao {
    @Query("Select * from deadline")
    LiveData<List<Deadline>> getDeadlineList();
    @Insert
    void insertDeadline(Deadline mdeadline);
    @Update
    void updateDeadline(Deadline mdeadline);
    @Delete
    void deleteDeadline(Deadline mdeadline);
    @Query("Delete From deadline")
    void deleteAllDeadline();
}
