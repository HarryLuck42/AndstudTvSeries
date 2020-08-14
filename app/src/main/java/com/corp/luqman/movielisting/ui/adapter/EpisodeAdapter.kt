package com.corp.luqman.movielisting.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.corp.luqman.movielisting.R
import com.corp.luqman.movielisting.data.models.Episode
import kotlinx.android.synthetic.main.item_episode_tv_series.view.*

class EpisodeAdapter (val context: Context, val episodes: MutableList<Episode>) :
    RecyclerView.Adapter<EpisodeAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun setData(episode: Episode){

            itemView.tv_episode.setText(episode.episode.toString() + ".")
            itemView.tv_title_episode.text = episode.title
            itemView.tv_duration.text = episode.duration

        }


        companion object {
            fun from(parent: ViewGroup): EpisodeAdapter.ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.item_episode_tv_series, parent, false)

                return ViewHolder(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return episodes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val episodeSelected = episodes[position]
        holder.setData(episodeSelected)
    }
}