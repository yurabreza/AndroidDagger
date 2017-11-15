package com.androiddagger.presentation.screens.detail

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androiddagger.R
import com.androiddagger.data.DetailFragmentService
import com.androiddagger.databinding.FragmentDetailBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class DetailFragment : Fragment() {
    @Inject lateinit var detailFragService: DetailFragmentService

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentDetailBinding>(inflater, R.layout.fragment_detail, container, false)
        val viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        binding.model = viewModel
        viewModel.text.set(detailFragService.getData())
        return binding.root
    }

    companion object {
        fun newInstance() = DetailFragment()
    }
}