package com.example.semesterplanner.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.semesterplanner.CourseData
import com.example.semesterplanner.R
import com.example.semesterplanner.RecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel



    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        searchViewModel =
                ViewModelProvider(this).get(SearchViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_search, container, false)
        //val textView: TextView = root.findViewById(R.id.text_dashboard)
        searchViewModel.text.observe(viewLifecycleOwner, Observer {
          //  textView.text = it

            //TODO: not sure if this code needs to be here or in MainActivity
            //MyCode:
            val courseList = generateDummyList(500)
            recycler_view.adapter = RecyclerViewAdapter(courseList)
            recycler_view.layoutManager = LinearLayoutManager(this.context)
            recycler_view.setHasFixedSize(true)


        })



        return root
    }


    //TODO: may delete this function again
    //This function is only used during development process until API data is available
    private fun generateDummyList(size: Int): ArrayList<CourseData> {
        val courseList = ArrayList<CourseData>()

        for (i in 0 until size) {
            val course = CourseData("VO App Development", "Tuesday 10:00 - 12:00", "Schöffmann K.", "623.950")
            courseList += course
        }
        return courseList
    }

}