package com.cleanup.todoc.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.cleanup.todoc.model.Task;

import java.util.List;
@Dao
public interface TaskDao {

    /**
     *
     * @return list of task
     */
    @Query("SELECT * FROM Task")
    LiveData<List<Task>> getTasks();


    /**create
     * insert
     * @param task
     * @return
     */
    @Insert
    long insertTask(Task task);

    /**
     * delete task
     * @param taskId
     * @return
     */
    @Query("DELETE FROM Task WHERE id = :taskId")
    int deleteTask(long taskId);

    /**
     * update task
     * @param task
     * @return
     */
    @Update
    int updateTask(Task task);
}
