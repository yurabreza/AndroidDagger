package com.androiddagger.presentation.screens.main

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.adnroiddagger.FeedQuery
import com.adnroiddagger.type.FeedType
import com.androiddagger.R
import com.androiddagger.databinding.ActivityMainBinding
import com.androiddagger.presentation.screens.detail.DetailFragment
import com.androiddagger.presentation.utils.disableAfterClick
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.apollographql.apollo.fetcher.ApolloResponseFetchers
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    @Inject lateinit var graphQlClient: ApolloClient
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MainViewModel
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector

    val callback: ApolloCall.Callback<FeedQuery.Data> = object : ApolloCall.Callback<FeedQuery.Data>() {
        override fun onResponse(response: Response<FeedQuery.Data>) {
            Log.d(TAG, response.toString())
        }

        override fun onFailure(e: ApolloException) {
            Log.d(TAG, e.toString())
            e.printStackTrace()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        activityMainBinding.activity = this
    }

    fun onButtonClick(view: View) {
        view.disableAfterClick()
        displayFragment(DetailFragment())
    }

    private fun makeGraphQlCall() {
        val feedQuery = FeedQuery.builder()
                .limit(FEED_SIZE)
                .type(FeedType.HOT)
                .build()

        graphQlClient.query(feedQuery)
                .responseFetcher(ApolloResponseFetchers.NETWORK_FIRST)
                .enqueue(callback)
    }

    private fun displayFragment(fragment: Fragment, addToBackStack: Boolean = false) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(activityMainBinding.flRoot.id, fragment, fragment.javaClass.simpleName)
        if (addToBackStack) ft.addToBackStack(null)
        ft.commit()
    }

    companion object {
        val FEED_SIZE = 20
        val TAG = "GraphQlTag"
    }
}
