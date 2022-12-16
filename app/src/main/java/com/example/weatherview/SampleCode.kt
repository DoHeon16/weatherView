package com.example.weatherview

class SampleCode {

    var a:Int=7
    var b:Double=10.5
    var c:String="greenjoa"
    var d=0

    val aa:Int=7
    val bb:Double=10.5
    val cc:String="bluejoa"
    val dd="fixed string"

    lateinit var g:String

    fun samplefun(){
        g="constructor string"
        println(g)

        var str:String?="greenjoa"
        str=null

        val e:String
        //e=null //Error
        val f:String?=null

        val h:String?=null
        var uppercase
        = if(str!=null) h else null //null
        //uppercase.toUpperCase() //error
        uppercase=h?.toUpperCase() //null

        val name:String?="greenjoa"
        //val name2:String=name //error
        val name3:String?=name
        val name4:String=name!!

        val x=6
        when(x){
            1 -> { println("x=1") }
            2,3 -> { println("x==2 or x==3") }
            in 4..7 -> { print("4부터 7사이") }
            !in 8..10 -> { println("8부터 10 사이가 아님")}
            else -> { println("x는 1이나 2가 아님") }
        }

        val num=1
        val numStr=when(num%2){
            0->"짝"
            else->"홀"
        }
        println(numStr)

        val numbers= arrayOf(1,2,3,4,5)
        for(num in numbers){
            println(num)
        }
        for(index in numbers.indices){
            println("number at $index is ${numbers[index]}")
        }

        val numbers1= listOf(1,2,3,4,5)
        for(index in numbers1.indices){
            println("number at $index is ${numbers1[index]}")
        }

        for(i in 1..3){ }
        for(i in 0..10 step 2){ }
        for(i in 10 downTo 0 step 2){ }
        for(i in 1 until 10){}
        repeat(10){ }

        println("s_string is $g")
        print("s_int is $d\n")
        print("another sample is "+dd)

        greet("Hello World!")
        addNumbers(2.3,1.4)
    }

    fun greet(str:String){
        println(str)
    }

    fun addNumbers(n1:Double, n2:Double):Int{
        val sum=n1+n2
        val sumInteger=sum.toInt()
        return sumInteger
    }

    fun makeList(){

        val foods:List<String> = listOf("라면","밥","치킨")
        val foods2= listOf("라면","밥","치킨")

        val foods3:MutableList<String>
        = mutableListOf("라면","밥","치킨")
        val foods4= mutableListOf("라면","밥","치킨")
        foods3.add("피자")
        foods3.removeAt(0)
        foods3[1]="부대찌개"
        foods4.set(1,"김치찌개")
    }

    fun dataClass(){

        val seoul=Coordinates("seoul","55","127")
        println("place=${seoul.place}")
        println("x=${seoul.x}")
        println("y=${seoul.y}")
    }
}