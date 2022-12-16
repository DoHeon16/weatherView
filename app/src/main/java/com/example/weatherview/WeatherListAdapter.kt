package com.example.weatherview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WeatherListAdapter(val weatherList:List<Weather>)
    :RecyclerView.Adapter<WeatherListAdapter.ViewHolder>(){

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

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.weatherbox,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.place.text=weatherList[position].place
        holder.temp.text=weatherList[position].temperature
        holder.humi.text=weatherList[position].humidity
        holder.rain.text=weatherList[position].rainfall
    }
}