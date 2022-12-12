package com.example.weatherview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sampleObject=SampleCode()
        sampleObject.samplefun()

        init()
    }

    fun init(){
        val memo=findViewById<TextView>(R.id.sampleSentence)


        val btn=findViewById<Button>(R.id.button)

        btn.setOnClickListener {
            val i=Intent(this,DataActivity::class.java)
            i.putExtra("memo",memo.text)
            startActivity(i)
        }
    }
}