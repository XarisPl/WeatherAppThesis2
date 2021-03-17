package com.example.weatherappthesis.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherappthesis.R
import com.example.weatherappthesis.model.Hour
import com.example.weatherappthesis.util.*
import com.example.weatherappthesis.util.getTemp
import kotlinx.android.synthetic.main.item_forecast_hour.view.*

class ForecastHourAdapter(private val hoursList: ArrayList<Hour>) :
    RecyclerView.Adapter<ForecastHourAdapter.HourHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ForecastHourAdapter.HourHolder {
        val inflatedView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_forecast_hour, parent, false)
        return HourHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ForecastHourAdapter.HourHolder, position: Int) {
        holder.bind(hoursList[position])
    }

    override fun getItemCount() = hoursList.size

    inner class HourHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(hour: Hour) {
            itemView.tv_hour.text = hour.time?.let { getHour(it) }
            itemView.tv_air_temp.text = hour.airTemperature?.let { getTemp(it) }
            itemView.tv_wave_height.text = hour.waveHeight?.let { getHeight(it) }
            itemView.tv_wave_period.text = hour.wavePeriod?.let { getSeconds(it) }
            itemView.tv_wind_speed.text = hour.windSpeed?.let { getBF(it) }
            itemView.tv_wind_direction.text = hour.windDirection?.let { getWind(it) }
        }

    }

}
