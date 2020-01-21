package com.cleanup.todoc.repositories;

import android.arch.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.model.Project;

import java.util.List;

public class ProjectDataRepository {

    private final ProjectDao mProjectDao;

    public ProjectDataRepository(ProjectDao projectDao){
        this.mProjectDao = projectDao;
    }


    // --- GET ALL PROJECT ---
    public LiveData<List<Project>> getAllProjects(){
        return this.mProjectDao.getProjects();
    }
    // --- CREATE PROJECT ---
    public void createProject (Project project){
        this.mProjectDao.createProject(project);
    }
}
