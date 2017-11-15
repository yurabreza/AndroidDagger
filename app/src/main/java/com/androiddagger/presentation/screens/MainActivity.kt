package com.androiddagger.presentation.screens

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
    private lateinit var viewModel: MainViewModel
    private lateinit var contentView: ActivityMainBinding

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
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        contentView = DataBindingUtil.setContentView(this, R.layout.activity_main)
        contentView.activity = this
    }

    fun makeGraphQlCall(view: View) {
        view.disableAfterClick()
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
        ft.replace(contentView.flRoot.id, fragment, fragment.javaClass.simpleName)
        if (addToBackStack) ft.addToBackStack(null)
        ft.commit()
    }

    companion object {
        val FEED_SIZE = 20
        val TAG = "GraphQlTag"
    }
}
