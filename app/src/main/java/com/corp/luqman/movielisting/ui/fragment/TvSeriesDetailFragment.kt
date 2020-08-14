package com.corp.luqman.movielisting.ui.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.corp.luqman.movielisting.R
import com.corp.luqman.movielisting.data.models.Episode
import com.corp.luqman.movielisting.data.models.Season
import com.corp.luqman.movielisting.ui.adapter.EpisodeAdapter
import kotlinx.android.synthetic.main.tv_series_detail_fragment.*
import kotlinx.android.synthetic.main.tv_series_detail_fragment.view.*
import kotlinx.android.synthetic.main.tv_series_detail_fragment.view.rv_episode
import org.koin.android.ext.android.inject

class TvSeriesDetailFragment : Fragment() , AdapterView.OnItemSelectedListener{


    private val viewModel: TvSeriesDetailViewModel by inject()

    private val seasons : MutableList<Season> = mutableListOf()
    val listSeason : ArrayList<Int> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.tv_series_detail_fragment, container, false)

        val arguments = TvSeriesDetailFragmentArgs.fromBundle(requireArguments())

        viewModel.initializeDetail(arguments.idTvSeries)

        viewModel.tvSeriesDetail.observe(viewLifecycleOwner, Observer {
            it?.let {
                v.tv_input_cast.text = it.cast
                v.tv_input_creator.text = it.creator
                v.tv_input_genre.text = it.genre
                v.sp_seasons.onItemSelectedListener = this

                seasons.addAll(it.seasons!!)
                for(value in it.seasons!!){
                    listSeason.add(value.season)
                }
                val array_adapter = ArrayAdapter(v.context, android.R.layout.simple_spinner_item, listSeason)
                array_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                v.sp_seasons.adapter = array_adapter

            }
        })

        return v
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val seasonNumber = listSeason[position]
        val episodes : MutableList<Episode> = mutableListOf()

        for(season in seasons){
            if(season.season == seasonNumber){
                episodes.addAll(season.episodes!!)
            }
        }
        episodes.let {
            val layoutManager = LinearLayoutManager(this.requireContext())
            rv_episode.layoutManager = layoutManager
            val adapter = EpisodeAdapter(this.requireContext(), it)
            rv_episode.adapter = adapter
        }

    }


}