package com.example.kotlinsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try{
            val myDatabase = this.openOrCreateDatabase("Musicians", MODE_PRIVATE,null)

            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS musicians(id INTEGER PRIMARY KEY,name VARCHAR, age INT)")

            //myDatabase.execSQL("INSERT INTO musicians(name,age) VALUES ('Mike',31)")
            //myDatabase.execSQL("INSERT INTO musicians(name,age) VALUES ('Ben',34)")
            //myDatabase.execSQL("INSERT INTO musicians(name,age) VALUES ('Josh',49)")

            //myDatabase.execSQL("UPDATE musicians SET age=35 WHERE name = 'Ben'")
            //myDatabase.execSQL("UPDATE musicians SET name='Josh Homme' WHERE id = 3")
            myDatabase.execSQL("DELETE FROM musicians WHERE id=3 ")

            val cursor = myDatabase.rawQuery("SELECT * FROM musicians",null)

            val nameIx = cursor.getColumnIndex("name")
            val ageIx = cursor.getColumnIndex("age")
            val idIx = cursor.getColumnIndex("id")

            println("Id     Name     Age")

            while (cursor.moveToNext()){
                println(cursor.getString(idIx)+"      "+
                        cursor.getString(nameIx)+"      "+
                        cursor.getString(ageIx))
            }


        }catch (e : Exception) {
            e.printStackTrace()
        }




    }
}