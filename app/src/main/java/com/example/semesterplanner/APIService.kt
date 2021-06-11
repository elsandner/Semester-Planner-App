package com.example.semesterplanner

import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class APIService {

    private val SERVER_URL = "https://aau-semester-planner.herokuapp.com/courses"
    private val courseList = ArrayList<CourseData>()

    fun init(){
        //GlobalScope.launch { //Run in new Thread
            val apiData = fetchDataFromAPI()
            fetchCourseDataFromJSON(apiData)
        //}
    }


    //fetch Data from API
    private fun fetchDataFromAPI(): String{

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

        return apiData
    }

    //Fetch Data from JSON and store it in courseList
    private fun fetchCourseDataFromJSON(apiData: String){

        val jsonArray = JSONArray(apiData)

        for (i in 0 until jsonArray.length()) {
            val course = jsonArray.getJSONObject(i)

            val title: String = course.get("type").toString() + " " + course.get("title")
            val date: String =fetchDate(course)
            val teacher=course.get("prof").toString()
            val courseID =course.get("courseNumber").toString()

            courseList.add(CourseData(title, date, teacher, courseID))
        }
    }

    //get the date from the JSON Object as a pretty string
    private fun fetchDate(course: JSONObject): String{
        val weekday: String =course.get("weekDay").toString()

        val startTime: JSONObject = course.get("startTime") as JSONObject
        var startHour: String = startTime.get("hours").toString()
        var startMinute: String = startTime.get("minutes").toString()

        if(startHour.length==1){
            startHour += "0"
        }
        if(startMinute.length==1){
            startMinute += "0"
        }

        val endTime: JSONObject = course.get("endTime") as JSONObject
        var endHour: String = endTime.get("hours").toString()
        var endMinute: String = endTime.get("minutes").toString()

        if(endHour.length==1){
            endHour += "0"
        }
        if(endMinute.length==1){
            endMinute += "0"
        }

        return "$weekday $startHour:$startMinute-$endHour:$endMinute"
    }

    fun getCourseList(): ArrayList<CourseData>{
        return this.courseList
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