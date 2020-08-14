package com.corp.luqman.movielisting.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.corp.luqman.movielisting.R
import com.corp.luqman.movielisting.data.models.TvSeries
import com.corp.luqman.movielisting.utils.Helpers
import kotlinx.android.synthetic.main.item_tv_series.view.*

class TvSeriesAdapter (val context: Context, val tvSeries: MutableList<TvSeries>, val clickListener: TvSeriesListener ) : RecyclerView.Adapter<TvSeriesAdapter.ViewHolder>(){

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun setData(tvSeries: TvSeries, clickListener: TvSeriesListener){
            Glide.with(itemView.context).load(tvSeries.img_url).into(itemView.iv_tv_series)
            itemView.title_tv_series.text = tvSeries.title
            itemView.year_tv_series.text = tvSeries.year
            itemView.btn_detail.setOnClickListener {
                clickListener.onClick(tvSeries)
            }
            itemView.btn_sinopsis.setOnClickListener {
                Helpers.showGeneralOkDialog(
                    itemView.context,
                    itemView.context.getString(R.string.sinopsis),
                    tvSeries.sinopsis!!
                )
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.item_tv_series, parent, false)

                return ViewHolder(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return tvSeries.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvSeriesSelected = tvSeries[position]
        holder.setData(tvSeriesSelected, clickListener)
    }
}

class TvSeriesListener(val clickListener: (tvSeriesId: Long) -> Unit) {
    fun onClick(tvSeries : TvSeries) = clickListener(tvSeries.id)
}