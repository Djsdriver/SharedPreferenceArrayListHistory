package com.example.sharedpreferencearraylisthistory

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Main : AppCompatActivity() {
    
    lateinit var courseNameEdt: EditText
     lateinit var courseDescEdt: EditText
     lateinit var addBtn: Button
     lateinit var saveBtn: Button
     lateinit var courseRV: RecyclerView

    var adapter= CourseAdapter()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initializing our variables.
        courseNameEdt = findViewById(R.id.idEdtCourseName)
        courseDescEdt = findViewById(R.id.idEdtCourseDescription)
        addBtn = findViewById(R.id.idBtnAdd)
        saveBtn = findViewById(R.id.idBtnSave)
        courseRV = findViewById(R.id.idRVCourses)


        loadData()



        buildRecyclerView()

        addBtn.setOnClickListener{
            adapter.courseModalArrayList!!.add(
                CourseModal(
                    courseNameEdt.text.toString(),
                    courseDescEdt.text.toString()
                )
            )

            adapter?.notifyItemInserted(adapter.courseModalArrayList!!.size)
        }

        saveBtn.setOnClickListener {
            saveData()
        }
    }

    private fun buildRecyclerView() {

        val manager = LinearLayoutManager(this)
        courseRV!!.setHasFixedSize(true)

        courseRV!!.layoutManager = manager

        courseRV!!.adapter = adapter
    }

    private fun loadData() {

        val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
        val json =sharedPreferences.getString("courses"," null")
        val type = object : TypeToken<ArrayList<CourseModal>>() {}.type
        adapter.courseModalArrayList= Gson().fromJson(json, type)

    }

    private fun saveData() {

        val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
        val json = Gson().toJson(adapter.courseModalArrayList)
        sharedPreferences.edit().putString("courses", json).apply()
        Toast.makeText(this, "Saved Array List to Shared preferences. ", Toast.LENGTH_SHORT).show()
    }
}
