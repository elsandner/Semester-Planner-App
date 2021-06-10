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
import com.example.semesterplanner.APIService
import com.example.semesterplanner.CourseData
import com.example.semesterplanner.R
import com.example.semesterplanner.RecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel

    private var APIService: APIService = APIService();


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


            //MyCode:
            APIService.init()

            fillRecyclerView()


            //TODO:
            // since fillRecyclerView needs the data from the API and this data is fetched
            // asynchronously, we need wait with executing fillRecyclerView() until
            // APIService.init() is done.
        })

        return root
    }


    fun fillRecyclerView(){
        val courseList = APIService.getCourseList()

        recycler_view.adapter = RecyclerViewAdapter(courseList)
        recycler_view.layoutManager = LinearLayoutManager(this.context)
        recycler_view.setHasFixedSize(true)
    }




}