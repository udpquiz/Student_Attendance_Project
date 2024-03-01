package com.example.student_attendance
// Attendance_recording.kt
import Student
import StudentAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.opencsv.CSVReader
import java.io.InputStreamReader
import java.util.ArrayList

class Attendance_recording : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendance_recording)
        val studentattendance = findViewById<Button>(R.id.submitattendance)
        val studentRecyclerView: RecyclerView = findViewById(R.id.studentRecyclerView)
        val b = intent.extras
        val a = b?.getString("file")
        val inputStream = assets.open(a.toString()+".csv")
        val reader = CSVReader(InputStreamReader(inputStream))
        val studentList = ArrayList<Student>()

        // Read CSV content and populate the studentList
        var nextLine: Array<String>?
        while (reader.readNext().also { nextLine = it } != null) {
            // Assuming your CSV file has columns like "Name", "Roll Number", etc.
            val name = nextLine!![0]
            val rollNumber = nextLine!![1]

            // Create a Student object and add it to the list
            val student = Student(name, rollNumber, isPresent = false)
            studentList.add(student)
        }
        // Close the CSV reader
        reader.close()

        // Create StudentAdapter and pass the list of students
        val studentAdapter = StudentAdapter(studentList)

        studentRecyclerView.layoutManager = LinearLayoutManager(this)
        studentRecyclerView.adapter = studentAdapter
        studentattendance.setOnClickListener {
            val selectedStudents = studentAdapter.getSelectedStudents()
            Log.d("SelectedStudents", "Selected students: ${selectedStudents.joinToString {it.name  + "  "  + it.rollNumber + "\n"}} ")
//            val intent = Intent(this, print_Attendance::class.java)
//            intent.putExtra("students", selectedStudents.toTypedArray())
//            startActivity(intent)
//        }
        }
    }
}
