package com.example.weatherview

class SampleCode {

    lateinit var s_string:String
    var s_int=0

    val ss_string="fixed string"

    init {
        s_string="constructor string"
    }
    fun samplefun(){

        println("s_string is $s_string")
        print("s_int is $s_int\n")
        print("another sample is "+ss_string)

    }


}