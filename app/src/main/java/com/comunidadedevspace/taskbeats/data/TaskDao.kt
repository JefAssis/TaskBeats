package com.comunidadedevspace.taskbeats.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert (task: Task)

    @Query("Select * from task")
    fun getAll():List<Task>

    //Update - encontrar a tarefa que queremos mudar

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(task: Task)
    //Delete - to delete we got to find the task by an ID

    @Query("Delete from task WHERE id =:id")
    fun deleteById(id:Int)

    @Query("Delete from task")
    fun deleteAll()
}