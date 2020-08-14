package com.corp.luqman.movielisting.ui.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.corp.luqman.movielisting.R
import com.corp.luqman.movielisting.ui.adapter.TvSeriesAdapter
import com.corp.luqman.movielisting.ui.adapter.TvSeriesListener
import com.corp.luqman.movielisting.utils.Helpers
import com.corp.luqman.movielisting.utils.NetworkHelper
import com.corp.luqman.movielisting.utils.UiState
import com.corp.luqman.movielisting.utils.custom.CustomProgressDialog
import kotlinx.android.synthetic.main.progress_dialog.*
import kotlinx.android.synthetic.main.tv_series_fragment.*
import org.koin.android.ext.android.inject

class TvSeriesFragment : Fragment() {

    private lateinit var progressDialog : CustomProgressDialog

    private val viewModel: TvSeriesViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.tv_series_fragment, container, false)
        progressDialog = CustomProgressDialog(v.context, getString(R.string.loading))
        initObserver(v)
        viewModel.getTvSeriesValuesAPI()
        viewModel.navigateToDetail.observe(viewLifecycleOwner, Observer {
            it?.let {
                this.findNavController().navigate(TvSeriesFragmentDirections.actionTvSeriesFragmentToTvSeriesDetailFragment(it))
                viewModel.onTvSeriesNavigated()
            }
        })
        return v
    }


    private fun initObserver(v : View){
        viewModel.picsState.observe(viewLifecycleOwner, Observer {
            when (it){
                is UiState.Loading -> {
                    progressDialog.show()
                }
                is UiState.Success -> {
                    progressDialog.dismiss()
                    initObserverDetail(v)
                    viewModel.getTvSeriesDetailAPI()
                }
                is UiState.Error -> {
                    progressDialog.dismiss()
                    val message = NetworkHelper().getErrorMessage(it.throwable)
                    Helpers.showGeneralOkDialog(
                        v.context,
                        getString(R.string.perhatian),
                        message
                    )
                }
            }
        })
    }

    private fun initObserverDetail(v : View){
        viewModel.picsStateDetail.observe(viewLifecycleOwner, Observer {
            when(it){
                is UiState.Loading -> {
                    progressDialog.show()
                }
                is UiState.Success -> {
                    progressDialog.dismiss()
                    viewModel.getAllTvSeries!!.observe(viewLifecycleOwner, Observer {
                        it.let {
                            rv_tv_series.visibility = View.VISIBLE
                            tv_not_found.visibility = View.GONE
                            val layoutManager = GridLayoutManager(this.requireContext(), 2)
                            rv_tv_series.layoutManager = layoutManager
                            rv_tv_series.setHasFixedSize(true)
                            val adapter = TvSeriesAdapter(
                                this.requireContext(),
                                it, TvSeriesListener{id ->
                                    viewModel.onTvSeriesClicked(id)
                                }
                            )
                            rv_tv_series.adapter = adapter


                        } ?: run {
                            rv_tv_series.visibility = View.VISIBLE
                            tv_not_found.visibility = View.GONE
                        }
                    })
                }
                is UiState.Error -> {
                    progressDialog.dismiss()
                    val message = NetworkHelper().getErrorMessage(it.throwable)
                    Helpers.showGeneralOkDialog(
                        v.context,
                        getString(R.string.perhatian),
                        message
                    )
                }
            }
        })
    }

}