package com.cleanup.todoc.injection;

import android.content.Context;

import com.cleanup.todoc.database.dao.TodocDatabase;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Injection {
    public static TaskDataRepository provideTaskDataRepository(Context context){
        TodocDatabase mDatabase = TodocDatabase.getInstance(context);
        return new TaskDataRepository(mDatabase.mTaskDao());
    }
    public static ProjectDataRepository provideProjectDataRepository(Context context) {
        TodocDatabase mDatabase = TodocDatabase.getInstance(context);
        return new ProjectDataRepository(mDatabase.mProjectDao());
    }
    public static Executor provideExecutor(){
        return Executors.newSingleThreadExecutor();
    }
    public static ViewModelFactory provideViewModelFactory(Context context){
        TaskDataRepository mTaskDataRepository = provideTaskDataRepository(context);
        ProjectDataRepository mProjectDataRepository = provideProjectDataRepository(context);
        Executor mExecutor = provideExecutor();
        return new ViewModelFactory(mTaskDataRepository,mProjectDataRepository,mExecutor);
    }

}
