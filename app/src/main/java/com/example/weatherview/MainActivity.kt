package com.example.weatherview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
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
        val image=findViewById<ImageView>(R.id.face)

        val btn2=findViewById<Button>(R.id.searchbtn)

        val btn1=findViewById<Button>(R.id.changebtn)

        btn1.setOnClickListener {
            nickname.text=nicknameText.text.toString()
            image.setImageResource(R.drawable.ic_baseline_sentiment_satisfied_alt_24)
        }

        btn2.setOnClickListener {
            Toast.makeText(this, "안녕하세요",Toast.LENGTH_SHORT).show()
//            val i=Intent(this,DataActivity::class.java)
//            i.putExtra("today",getDate())
//            i.putExtra("nickname",nickname.text.toString())
//            startActivity(i)
        }
    }

    fun getDate():String{
        val now=System.currentTimeMillis()
        val currentDate=Date(now)
        val dateFormat=SimpleDateFormat("yyyyMMdd")

        return dateFormat.format(currentDate).toString()
    }

}