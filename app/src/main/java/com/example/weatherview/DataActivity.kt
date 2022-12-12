package com.example.weatherview

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class DataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        val memo=intent.getStringExtra("memo")
        val title=findViewById<TextView>(R.id.title)
        title.text=memo
        init()
    }

    fun init(){
        connectAPI()
    }

    fun connectAPI(){
        val urlBuilder = StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst") /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8").toString() + "=O17EX7gdN3IhRhEPUn7x3%2Fndd0%2Boq4smJrUMpslKSbbUXw7lVBcorB3YLArZ0mXL%2FqQRaL9xiKmTNlTim5Xeuw%3D%3D")/*Service Key-decoding*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8").toString() + "=" + URLEncoder.encode("1", "UTF-8")) /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8").toString() + "=" + URLEncoder.encode("1000", "UTF-8")) /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8").toString() + "=" + URLEncoder.encode("XML", "UTF-8")) /*요청자료형식(XML/JSON) Default: XML*/
        urlBuilder.append("&" + URLEncoder.encode("base_date", "UTF-8").toString() + "=" + URLEncoder.encode("20221212", "UTF-8")) /*최근 3일만 정보 지원*/
        urlBuilder.append("&" + URLEncoder.encode("base_time", "UTF-8").toString() + "=" + URLEncoder.encode("0600", "UTF-8")) /*06시 발표(정시단위) */
        urlBuilder.append("&" + URLEncoder.encode("nx", "UTF-8").toString() + "=" + URLEncoder.encode("59", "UTF-8")) /*예보지점의 X 좌표값*/
        urlBuilder.append("&" + URLEncoder.encode("ny", "UTF-8").toString() + "=" + URLEncoder.encode("125", "UTF-8")) /*예보지점의 Y 좌표값*/
        //대방동(59,125) -> 엑셀 확인

        Log.i("urlBuilder.toString ",urlBuilder.toString())

        val url = URL(urlBuilder.toString())
        val conn: HttpURLConnection = url.openConnection() as HttpURLConnection
        conn.setRequestMethod("GET")
        conn.setRequestProperty("Content-type", "application/json")
        System.out.println("Response code: " + conn.getResponseCode())
        val rd: BufferedReader
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = BufferedReader(InputStreamReader(conn.getInputStream()))
        } else {
            rd = BufferedReader(InputStreamReader(conn.getErrorStream()))
        }
        val sb = StringBuilder()
        var line: String?
        while (rd.readLine().also { line = it } != null) {
            sb.append(line)
        }
        rd.close()
        conn.disconnect()
        println(sb.toString())
    }
}