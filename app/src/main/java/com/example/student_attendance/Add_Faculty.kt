package com.example.student_attendance

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.example.attendance.crudFaculty

class Add_Faculty : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_faculty)
        val btnadd: Button = findViewById(R.id.btn_add_faculty)
        val btnview: Button = findViewById(R.id.btn_view_faculty)


        val edfname: EditText = findViewById(R.id.ed_f_name)
        val edfcode: EditText = findViewById(R.id.ed_f_code)
        val edfpassword: EditText = findViewById(R.id.ed_f_password)

        val sql = crudFaculty(this)

        btnadd.setOnClickListener {
            val r: Boolean = sql.insertFaculty(
                edfcode.text.toString().toInt(),
                edfname.text.toString(),
                edfpassword.text.toString()
            )
            if (r == true) {
                Toast.makeText(this, " Faculty added" + "{$edfname}", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,View_Faculty::class.java))
            } else {
                Toast.makeText(this, "Faculty add failed", Toast.LENGTH_SHORT).show()
            }

        }
//    val sql = sqlConnecion(this)
//
//        btnADD.setOnClickListener {
//            val r:Boolean = sql.insertRecord(editNAME.text.toString(),editDIVISION.text.toString())
//            if (r == true)
//                Toast.makeText(this,"Record Added!!",Toast.LENGTH_SHORT).show()
//            else
//                Toast.makeText(this,"Add Failed!!",Toast.LENGTH_SHORT).show()
//        }
    }
}