package com.example.findfood.ui.home

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {
    var id: String? = null
    var name: String? = null
    var url: String? = null
    var shopImage: ImageView? = null
    var address: String? = null
    var lunch: String? = null
}

//data class HomeViewModel (
//    var id: String? = null,
//    var name: String? = null,
//    var url: String? = null,
//    var shopImage: ImageView? = null,
//    var address: String? = null,
//    var lunch: String? = null
//)