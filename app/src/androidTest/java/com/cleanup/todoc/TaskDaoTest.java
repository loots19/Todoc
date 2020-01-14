package com.cleanup.todoc;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.cleanup.todoc.database.dao.TodocDatabase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(AndroidJUnit4.class)
public class TaskDaoTest {

    // For DATA
    private TodocDatabase mDatabase;
    // DATA SET FOR TEST PROJECT
    private static long PROJECT_ID = 1;
    private static Project PROJECT_DEMO = new Project(PROJECT_ID,"Tartempion",0xFFEADAD1);
    // DATA SET FOR TEST TASK
    private static Task TASK_DEMO = new Task(PROJECT_ID,"nettoyer salle de bain",50000);

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception {
        this.mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                TodocDatabase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void closeDb() throws Exception {
        mDatabase.close();
    }
    @Test
    public void insertAndGetProject() throws InterruptedException{
        // BEFORE : Adding a new project
        this.mDatabase.mProjectDao().createProject(PROJECT_DEMO);
        // TEST
        Project project = LiveDataTestUtil.getValue(this.mDatabase.mProjectDao().getProject(PROJECT_ID));
        assertTrue(project.getName().equals(PROJECT_DEMO.getName()) && project.getId() == PROJECT_ID);
    }
    @Test
    public void getTaskWhenNoTaskInserted() throws InterruptedException{
        // TEST
        List<Task> tasks = LiveDataTestUtil.getValue(this.mDatabase.mTaskDao().getAllTasks());
        assertTrue(tasks.isEmpty());
    }
    @Test
    public void insertAndGetTasks() throws InterruptedException{
        // BEFORE Adding demo project and demo task
        this.mDatabase.mProjectDao().createProject(PROJECT_DEMO);
        this.mDatabase.mTaskDao().insertTask(TASK_DEMO);
        // TEST
        List<Task> tasks = LiveDataTestUtil.getValue(this.mDatabase.mTaskDao().getTask(PROJECT_ID));
        assertTrue(tasks.size() == 1);
    }
    @Test
    public void insertAndUpdateTask() throws InterruptedException{
        // BEFORE : Adding demo project & demo task. Next, get the task updated
        this.mDatabase.mProjectDao().createProject(PROJECT_DEMO);
        this.mDatabase.mTaskDao().insertTask(TASK_DEMO);
        List<Task> tasks = LiveDataTestUtil.getValue(this.mDatabase.mTaskDao().getAllTasks());
        Task task1 = tasks.get(0);
        task1.setName("réparer porte");
        this.mDatabase.mTaskDao().updateTask(task1);
        assertEquals("réparer porte",tasks.get(0).getName());

    }
    @Test
    public void deleteTask() throws InterruptedException{
        // BEFORE : Adding demo project & demo task. Next, get the task deleted
        this.mDatabase.mProjectDao().createProject(PROJECT_DEMO);
        this.mDatabase.mTaskDao().insertTask(TASK_DEMO);
        Task task = LiveDataTestUtil.getValue(this.mDatabase.mTaskDao().getTask(PROJECT_ID)).get(0);
        this.mDatabase.mTaskDao().deleteTask(task.getId());

        List<Task> tasks = LiveDataTestUtil.getValue(this.mDatabase.mTaskDao().getTask(PROJECT_ID));
        assertTrue(tasks.isEmpty());
    }


}
