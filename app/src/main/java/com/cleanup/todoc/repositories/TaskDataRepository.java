package com.cleanup.todoc.repositories;

import android.arch.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Task;

import java.util.List;

public class TaskDataRepository {

    private final TaskDao mTaskDao;

    public TaskDataRepository(TaskDao taskDao){
        this.mTaskDao = taskDao;

    }
    // --- GET ---
    public LiveData<List<Task>> getTask(long ProjectId){
        return this.mTaskDao.getTask(ProjectId);
    }
    // --- CREATE ---
    public void createTask(Task task){
        mTaskDao.insertTask(task);
    }
    // --- DELETE ---
    public void deleteTask(long taskId){
        mTaskDao.deleteTask(taskId);
    }
    // --- UPDATE ---
    public void updateTask(Task task){
        mTaskDao.updateTask(task);
    }


}
