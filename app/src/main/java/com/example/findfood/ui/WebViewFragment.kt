package com.example.findfood.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.activity.OnBackPressedCallback
import com.example.findfood.MainActivity

import com.example.findfood.R

class WebViewFragment : Fragment() {

    val mainActivity: MainActivity
        get() = (activity as MainActivity)
    val isOverrideBack = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_web_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webShow()

    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//        mainActivity.onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback {
//            override fun handleOnBackPressed(): Boolean {
//                if (isOverrideBack) {
//                    return true
//                }else {
//                    return false
//                }
//            }
//        })
//    }

    fun webShow(){
        val url = getArguments()!!.getString("URL")
        val webView: WebView = view!!.findViewById(R.id.webView)
        webView.loadUrl(url)

    }
}
