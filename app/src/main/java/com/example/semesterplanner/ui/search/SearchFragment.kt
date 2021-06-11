package com.example.semesterplanner.ui.search

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.semesterplanner.APIService
import com.example.semesterplanner.MainActivity
import com.example.semesterplanner.R
import com.example.semesterplanner.RecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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


            fillRecyclerView()

        })

        return root
    }


    private fun fillRecyclerView(){
        val context = this.context
        GlobalScope.launch { //Run in new Thread

            APIService.init()
            val courseList = APIService.getCourseList()

            //Run in main thread again
            Handler(Looper.getMainLooper()).post {
                recycler_view.adapter = RecyclerViewAdapter(courseList)
                recycler_view.layoutManager = LinearLayoutManager(context)
                recycler_view.setHasFixedSize(true)
            }

        }
    }




}