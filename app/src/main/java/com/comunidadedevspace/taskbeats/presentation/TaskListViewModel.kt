package com.comunidadedevspace.taskbeats.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.comunidadedevspace.taskbeats.TaskBeatsApplication
import com.comunidadedevspace.taskbeats.data.local.Task
import com.comunidadedevspace.taskbeats.data.local.TaskDao


//                                  showing a dependency = depends on the TBApplliication
class TaskListViewModel(taskDao: TaskDao):ViewModel() {

    //                                          it comes from (bellow)
    val taskListLiveData: LiveData<List<Task>> = taskDao.getAll()



    companion object {

        fun create (application:Application) : TaskListViewModel{
            val dataBaseInstance = (application as TaskBeatsApplication).getAppDataBase()
            val dao = dataBaseInstance.taskDao()
            //to create an VM a "dao" must be used as a construction parameter.
            return TaskListViewModel(dao)

        }
    }

}