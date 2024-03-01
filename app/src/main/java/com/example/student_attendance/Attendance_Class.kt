package com.example.student_attendance
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
class Attendance_Class : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendance_class)
        val icaa6 = findViewById<EditText>(R.id.ICAA6)
        val open = findViewById<Button>(R.id.open)
        val c = Bundle()
        open.setOnClickListener {
            c.putString("file",icaa6.text.toString())
            val intent = Intent(this,Attendance_recording::class.java)
            intent.putExtras(c)
            startActivity(intent)
        }
    }
}