package com.example.semesterplanner

import android.content.Context
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class APIService {

    private val SERVER_URL = "https://aau-semester-planner.herokuapp.com/courses"


    fun init(){
        fetchData()
    }


    private fun fetchData(){

        //Fetch Data:
        GlobalScope.launch { //Run in new Thread
            val url = URL(SERVER_URL)
            val con: HttpURLConnection = url.openConnection() as HttpURLConnection
            con.requestMethod = "GET"

            var inputLine: String?
            val br = BufferedReader(InputStreamReader(con.inputStream))
            var apiData: String = "";

            while (br.readLine().also { inputLine = it } != null) {
                apiData+=inputLine
            }
            br.close()

            var test = apiData

            //check variable apiData

        }
    }

    //This function is only used during development process until API data is available
    fun generateDummyList(size: Int): ArrayList<CourseData> {
        val courseList = ArrayList<CourseData>()

        for (i in 0 until size) {
            val course = CourseData("VO App Development", "Tuesday 10:00 - 12:00", "Schöffmann K.", "623.950")
            courseList += course
        }
        return courseList
    }


}