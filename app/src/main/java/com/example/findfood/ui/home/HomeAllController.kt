package com.example.findfood.ui.home

import com.airbnb.epoxy.EpoxyController
import com.example.findfood.ListAllBindingModel_

class HomeAllController : EpoxyController() {
    //多分、リストの設定
    var list: List<HomeViewModel> = emptyList()
        set(vlaue) {
            field = vlaue
            requestModelBuild()
        }

    override fun buildModels() {
        list.forEach { list ->
            ListAllBindingModel_()
                .id(modelCountBuiltSoFar)
                .listAllModel(list)
                .addTo(this)
        }
    }
}