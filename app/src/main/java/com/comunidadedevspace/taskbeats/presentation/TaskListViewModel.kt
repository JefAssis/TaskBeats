package com.comunidadedevspace.taskbeats.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comunidadedevspace.taskbeats.TaskBeatsApplication
import com.comunidadedevspace.taskbeats.data.Task
import com.comunidadedevspace.taskbeats.data.TaskDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


//                                  showing a dependency = depends on the TBApplliication
class TaskListViewModel(private val taskDao: TaskDao):ViewModel() {

    //                                          it comes from (bellow)
    val taskListLiveData: LiveData<List<Task>> = taskDao.getAll()
    fun execute(taskAction: TaskAction){
        when (taskAction.actionType) {
            ActionType.DELETE.name -> deleteAById(taskAction.task!!.id)
            ActionType.CREATE.name -> insertIntoDataBase(taskAction.task!!)
            ActionType.UPDATE.name -> updateIntoDataBase(taskAction.task!!)
            ActionType.DELETE_ALL.name -> deleteAll()
        }
    }
    private fun deleteAById(id: Int) {
        viewModelScope.launch(Dispatchers.IO){
            taskDao.deleteById(id)
        }
    }

    private fun insertIntoDataBase(task: Task) {
        viewModelScope.launch(Dispatchers.IO){
            taskDao.insert(task)
        }
    }
    private fun updateIntoDataBase(task: Task) {
        viewModelScope.launch(Dispatchers.IO){
            taskDao.update(task)
        }
    }
    private fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO){
            taskDao.deleteAll()
        }
    }

    companion object {

        fun create (application:Application) : TaskListViewModel{
            val dataBaseInstance = (application as TaskBeatsApplication).getAppDataBase()
            val dao = dataBaseInstance.taskDao()
            //to create an VM a "dao" must be used as a construction parameter.
            return TaskListViewModel(dao)

        }
    }

}