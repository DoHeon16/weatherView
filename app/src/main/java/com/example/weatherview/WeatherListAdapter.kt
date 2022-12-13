package com.example.weatherview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WeatherListAdapter(val weatherList:List<Weather>)
    :RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        var image:ImageView
        var place:TextView
        var temp:TextView
        var humi:TextView
        var rain:TextView

        init {
            image=view.findViewById(R.id.weatherImage)
            place=view.findViewById(R.id.place)
            temp=view.findViewById(R.id.T1H)
            humi=view.findViewById(R.id.REH)
            rain=view.findViewById(R.id.RN1)
        }

        fun bind(weather: Weather){
            place.text=weather.place
            temp.text=weather.temperature
            humi.text=weather.humidity
            rain.text=weather.rainfall
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.weatherbox,parent,false)
        return ViewHolder(v)
    }

//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        if(weatherList[position].x=="59"&&weatherList[position].y=="125"){
//            holder.place.text="동작구"
//            holder.temp.text=weatherList[position].temperature
//            holder.humi.text=weatherList[position].humidity
//            holder.rain.text=weatherList[position].rainfall
//        }
//    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(weatherList[position])
    }
}