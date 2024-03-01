package com.example.attendance

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class crudFaculty(context: Context):SQLiteOpenHelper(context,"NewFaculty",null,1)
{
    companion object{
        const val TAB = "faculty"
        const val F_Code = "f_code"
        const val F_Password = "f_password"
        const val F_Name = "f_name"
    }




    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table $TAB(F_Code INTEGER PRIMARY KEY,FName TEXT, FPassword TEXT)")
//      db?.execSQL(    "create table TAB(ID INTEGER PRIMARY KEY AUTOINCREMENT,U_NAME TEXT,PASSWORD TEXT)")
    }

    fun insertFaculty(fcode: Int, fname:String?, fpassword:String? ):Boolean
    {
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put("F_Code",fcode)
        cv.put("FName",fname)
        cv.put("FPassword",fpassword)
        val ins = db.insert(TAB,null,cv)
        return if (ins > 0)
            true
        else
            false
    }

    fun viewFaculty(): Cursor?
    {
        val db = this.writableDatabase
        return db.rawQuery("select * from $TAB",null)
    }

    fun updateFaculty(f_code:Int,f_name:String?,f_password: String?):Int
    {
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put("F_Code",f_code)
        cv.put("FName",f_name)
        cv.put("FPassword",f_password)
        return db.update(TAB,cv,"ID = $f_code",null)
    }

    fun deleteAdmin(f_code:Int):Int{
        val db = this.writableDatabase
        return db.delete(TAB,"ID = $f_code" ,null)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

}