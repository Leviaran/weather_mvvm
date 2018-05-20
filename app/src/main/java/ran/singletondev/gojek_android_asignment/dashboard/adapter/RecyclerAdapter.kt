package ran.singletondev.gojek_android_asignment.dashboard.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import kotlinx.android.synthetic.main.item_recycler.view.*
import ran.singletondev.gojek_android_asignment.R
import ran.singletondev.gojek_android_asignment.common.domain.model.Forecast
import ran.singletondev.gojek_android_asignment.common.domain.model.ForecastDay
import ran.singletondev.gojek_android_asignment.dashboard.common.DateTranformator
import ran.singletondev.gojek_android_asignment.dashboard.common.Utils
import timber.log.Timber

/**
 * Created by ran on 5/18/18.
 */

class RecyclerAdapter (context: Context, var list : List<ForecastDay>?) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {


    var blackTypeface = Utils(context).regularTypeface

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler,parent,false)
        var viewHolder = ViewHolder(itemView)
        return viewHolder.apply {
            txtTemp.typeface = blackTypeface
            txtDay.typeface = blackTypeface
        }
    }

    override fun getItemCount(): Int {
        Timber.e(list?.size.toString())
        return 4

    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtDay.text = DateTranformator.convertToDay(list?.get(position)?.date)
        holder.txtTemp.text = "${list?.get(position)?.day?.avgtemp_c}°"
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        var txtDay : TextView

        var txtTemp : TextView

        init {
            txtTemp = itemView.findViewById(R.id.txt_temp)
            txtDay = itemView.findViewById(R.id.txt_day)
        }
    }
}