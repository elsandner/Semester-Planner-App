package com.example.semesterplanner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.course_container.view.*
import java.util.*
import kotlin.collections.ArrayList


class RecyclerViewAdapter(private val courseList: MutableList<CourseData>): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(), Filterable {

    var courseListAll: MutableList<CourseData> = ArrayList()

    init {
        courseListAll.addAll(courseList)
    }

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


    //Stuff for searching courses:

    override fun getFilter(): Filter {
        return myFilter
    }

    var myFilter: Filter = object : Filter() {

        //Automatic on background thread
        override fun performFiltering(charSequence: CharSequence): FilterResults {
            val filteredList: MutableList<CourseData> = ArrayList()

            if (charSequence.isEmpty()) {
                filteredList.addAll(courseListAll)
            } else {
                for (course in courseListAll) {
                    if (
                        //Locale.ROOT as a param of toLowerCase was suggested by Android Studio
                        //since missing that is a common source of bugs?!
                    course.title.toLowerCase(Locale.ROOT).contains(charSequence.toString().toLowerCase(Locale.ROOT))
                    ){
                        filteredList.add(course)
                    }

                }
            }

            val filterResults = FilterResults()
            filterResults.values = filteredList
            return filterResults
        }

        //Automatic on UI thread
        override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
            courseList.clear()
            courseList.addAll(filterResults.values as Collection<CourseData>)

            notifyDataSetChanged()
        }
    }


}