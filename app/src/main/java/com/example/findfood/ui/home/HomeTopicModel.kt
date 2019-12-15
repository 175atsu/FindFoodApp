package com.example.findfood.ui.home

import android.widget.ImageView

data class HomeTopicModel (
    var id: String? = null,
    var name: String? = null,
    var url: String? = null,
    var shopImage: ImageView? = null,
    var address: String? = null,
    var lunch: String? = null
)