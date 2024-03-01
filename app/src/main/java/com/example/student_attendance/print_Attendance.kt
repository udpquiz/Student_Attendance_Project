package com.example.student_attendance
import Student
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

@Suppress("DEPRECATION")
class print_Attendance : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_print_attendance)

        val students = intent.getParcelableArrayExtra("students") as? Array<Student>

        val displayTextView = findViewById<TextView>(R.id.displayTextView)

        // Display the selected students' data
        val displayText = if (students != null) {
            students.map { student ->
                "Name: ${student.name}, Roll Number: ${student.rollNumber}"
            }.joinToString("\n")
        } else {
            "No students selected"
        }

        displayTextView.text = displayText
    }
}
