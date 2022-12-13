package com.example.weatherview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sampleObject=SampleCode()
        sampleObject.samplefun()

        init()
    }

    fun init(){
        val nickname=findViewById<TextView>(R.id.sampleSentence)
        val nicknameText=findViewById<EditText>(R.id.nickname)
        val btn1=findViewById<Button>(R.id.changebtn)

        val btn2=findViewById<Button>(R.id.searchbtn)

        btn1.setOnClickListener {
            nickname.text=nicknameText.text.toString()
        }

        btn2.setOnClickListener {
            val i=Intent(this,DataActivity::class.java)
            i.putExtra("today",getDate())
            startActivity(i)
        }
    }

    fun getDate():String{
        val now=System.currentTimeMillis()
        val currentDate=Date(now)
        val dateFormat=SimpleDateFormat("yyyyMMdd")

        return dateFormat.format(currentDate).toString()
    }

}