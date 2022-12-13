package com.example.weatherview

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.util.jar.Manifest

class DataActivity : AppCompatActivity() {

    val serviceKey="O17EX7gdN3IhRhEPUn7x3%2Fndd0%2Boq4smJrUMpslKSbbUXw7lVBcorB3YLArZ0mXL%2FqQRaL9xiKmTNlTim5Xeuw%3D%3D"
    lateinit var recyclerView:RecyclerView
    lateinit var weatherAdapter:WeatherListAdapter
    var weatherList= mutableListOf<Weather>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        val memo=intent.getStringExtra("memo")
        val title=findViewById<TextView>(R.id.title)
        title.text=memo
        init()
    }

    fun init(){

        recyclerView=findViewById(R.id.recyclerView)
        recyclerView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        CoroutineScope(Dispatchers.Main).launch {
            connectAPI()
        }

    }

    suspend fun connectAPI(){
        //coroutine 사용
        //CoroutineScope(Dispatchers.Main).launch {
           val job= CoroutineScope(Dispatchers.IO).async {

                val urlBuilder = StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst") /*URL*/ //초단기 실황 조회
                urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8").toString() + "="+serviceKey)/*Service Key-decoding*/
                urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8").toString() + "=" + URLEncoder.encode("1", "UTF-8")) /*페이지번호*/
                urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8").toString() + "=" + URLEncoder.encode("1000", "UTF-8")) /*한 페이지 결과 수*/
                urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8").toString() + "=" + URLEncoder.encode("JSON", "UTF-8")) /*요청자료형식(XML/JSON) Default: XML*/
                urlBuilder.append("&" + URLEncoder.encode("base_date", "UTF-8").toString() + "=" + URLEncoder.encode("20221213", "UTF-8")) /*최근 3일만 정보 지원*/
                urlBuilder.append("&" + URLEncoder.encode("base_time", "UTF-8").toString() + "=" + URLEncoder.encode("0600", "UTF-8")) /*06시 발표(정시단위) */
                urlBuilder.append("&" + URLEncoder.encode("nx", "UTF-8").toString() + "=" + URLEncoder.encode("59", "UTF-8")) /*예보지점의 X 좌표값*/
                urlBuilder.append("&" + URLEncoder.encode("ny", "UTF-8").toString() + "=" + URLEncoder.encode("125", "UTF-8")) /*예보지점의 Y 좌표값*/
                //대방동(59,125) -> 엑셀 확인

                Log.i("urlBuilder.toString ",urlBuilder.toString())

                val url = URL(urlBuilder.toString())
                val conn: HttpURLConnection = url.openConnection() as HttpURLConnection //error
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
                    sb.append(line+"\n")
                }
                rd.close()
                conn.disconnect()

                //baseDate : 실황날짜, category : 종류(온도,습도,시간당 강수량), nx : x좌표, ny : y좌표, obsrValue : 해당 코드 값
                var baseDate=""
                var category=""
                var temperature=""
                var humidity=""
                var rainfall=""
                var x=""
                var y=""

                //string to JSON
                val root=JSONObject(sb.toString())
                val response=root.getJSONObject("response").getJSONObject("body").getJSONObject("items")
                val item=response.getJSONArray("item")

                for(i in 0 until item.length()){
                    val jObject=item.getJSONObject(i)
                    baseDate=jObject.getString("baseDate")
                    category=jObject.getString("category")
                    when(category){
                        "T1H"->{ temperature=jObject.getString("obsrValue")+"℃" }
                        "REH"->{ humidity=jObject.getString("obsrValue")+"%" }
                        "RN1"->{ rainfall=jObject.getString("obsrValue")+"mm" }
                    }
                    x=jObject.getString("nx")
                    y=jObject.getString("ny")
                }

                weatherList.add(Weather(baseDate,temperature,humidity,rainfall,x,y))
                Log.i("weather check ","$baseDate $temperature $humidity $rainfall $x $y")
                println(sb.toString())
            }

        CoroutineScope(Dispatchers.Main).launch {
            job.await()
            Log.i("weatherList size ",weatherList.size.toString())

            weatherAdapter= WeatherListAdapter(weatherList)
            recyclerView.adapter=weatherAdapter
        }



    }
}