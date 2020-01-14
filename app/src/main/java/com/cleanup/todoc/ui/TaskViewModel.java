package com.cleanup.todoc.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class TaskViewModel extends ViewModel {

    // REPOSITORIES
    private final TaskDataRepository mTaskDataRepository;
    private final ProjectDataRepository mProjectDataRepository;
    private final Executor mExecutor;


    //DATA
    @Nullable
    private LiveData<Project> currentProject;

    // constructor
    public TaskViewModel(TaskDataRepository mTaskDataRepository, ProjectDataRepository mProjectDataRepository, Executor mExecutor){
        this.mTaskDataRepository = mTaskDataRepository;
        this.mProjectDataRepository = mProjectDataRepository;
        this.mExecutor = mExecutor;
    }

    public void init(long projectId){
        if (this.currentProject != null){
            return;
        }
        currentProject = mProjectDataRepository.getProject(projectId);
    }

    // -------------
    // FOR PROJECT
    // -------------

    public LiveData<Project>getProject(long projectId){
        return this.currentProject;
    }

    // -------------
    // FOR TASK
    // -------------

    public LiveData<List<Task>> getTask(long projectId){
        return mTaskDataRepository.getTask(projectId);
    }
    public void createTask(Task task){
        mExecutor.execute(()->{
            mTaskDataRepository.createTask(task);
        });
    }

    public void deleteTask (long taskId){
        mExecutor.execute(()->{
            mTaskDataRepository.deleteTask(taskId);
        });

    }
    public void updateTask(Task task){
        mExecutor.execute(()->{
            mTaskDataRepository.updateTask(task);
        });
    }

}
