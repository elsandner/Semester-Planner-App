package com.example.semesterplanner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.course_container.view.*


class RecyclerViewAdapter(private val courseList: List<CourseData>): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.course_container,
            parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentCourse = courseList[position]

        holder.tvTitle.text = currentCourse.title
        holder.tvDate.text = currentCourse.date
        holder.tvTeacher.text = currentCourse.teacher
        holder.tvID.text = currentCourse.courseID


    }

    override fun getItemCount() = courseList.size



    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.tv_title
        val tvDate: TextView = itemView.tv_date
        val tvTeacher: TextView = itemView.tv_teacher
        val tvID: TextView = itemView.tv_ID
    }

}