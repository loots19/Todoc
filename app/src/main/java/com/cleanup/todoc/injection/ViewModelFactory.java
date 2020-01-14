package com.cleanup.todoc.injection;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;
import com.cleanup.todoc.ui.TaskViewModel;

import java.util.concurrent.Executor;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final TaskDataRepository mTaskDataRepository;
    private final ProjectDataRepository mProjectDataRepository;
    private final Executor mExecutor;

    public ViewModelFactory(TaskDataRepository mTaskDataRepository, ProjectDataRepository mProjectDataRepository, Executor mExecutor) {
        this.mTaskDataRepository = mTaskDataRepository;
        this.mProjectDataRepository = mProjectDataRepository;
        this.mExecutor = mExecutor;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(TaskViewModel.class)){
            return (T) new TaskViewModel(mTaskDataRepository,mProjectDataRepository,mExecutor);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
