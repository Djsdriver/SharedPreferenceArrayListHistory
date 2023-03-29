package com.example.sharedpreferencearraylisthistory

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CourseAdapter : RecyclerView.Adapter<CourseAdapter.ViewHolder>() {

    var courseModalArrayList= ArrayList<CourseModal>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.course_rv_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(courseModalArrayList[position])

    }

    override fun getItemCount(): Int {

        return courseModalArrayList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val courseNameTV: TextView
         val courseDescTV: TextView

        init {

            courseNameTV = itemView.findViewById(R.id.idTVCourseName)
            courseDescTV = itemView.findViewById(R.id.idTVCourseDescription)
        }

        fun bind(curs: CourseModal){
            courseNameTV.text=curs.courseName
            courseDescTV.text=curs.courseDescription
        }
    }
}