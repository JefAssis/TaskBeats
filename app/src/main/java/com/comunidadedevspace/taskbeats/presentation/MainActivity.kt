package com.comunidadedevspace.taskbeats.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.comunidadedevspace.taskbeats.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavView = findViewById<BottomNavigationView>(R.id.botton_nav_view)
        val floatingActionButton = findViewById<FloatingActionButton>(R.id.floatingActionButton)

        floatingActionButton.setOnClickListener{
            openTaskListDetail()
        }

        val taskListFragment = TaskListFragment.newInstance()
        val notesListFragment = NewsListFragment.newInstance()

        supportFragmentManager.commit {

            replace(R.id.fragment_container_view, taskListFragment)
            setReorderingAllowed(true)

        }
        bottomNavView.setOnItemSelectedListener { menuItem ->
            val fragment = when (menuItem.itemId) {
                R.id.task_list -> taskListFragment
                R.id.task_notes -> notesListFragment
                else -> taskListFragment
            }
            supportFragmentManager.commit {

                replace(R.id.fragment_container_view, fragment)
                setReorderingAllowed(true)

            }
            true
        }

    }
    private fun openTaskListDetail() {
        val intent = TaskDetailActivity.start(this, null)
        startActivity(intent)

    }
}