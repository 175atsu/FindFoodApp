package com.example.findfood.ui.home

import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyController
import com.example.findfood.ListAllBindingModel_
import com.example.findfood.ListTopicBindingModel_

class HomeAllController : EpoxyController() {
    //リストの設定
    var list: List<HomeViewModel> = emptyList()
        set(vlaue) {
            field = vlaue
            requestModelBuild()
        }

    var categoryList: List<HomeTopicModel> = emptyList()
        set(vlaue) {
            field = vlaue
            requestModelBuild()
        }

    override fun buildModels() {

        // carousel header
        if (list.isNotEmpty()) {
            //val spacing = context.dpToPx(8)
            CarouselModel_()
                .padding(Carousel.Padding(8, 0, 0, 0, 8))
                .id("carousel")
                .spanSizeOverride { _, _, _ -> 2 }
                .models(
                    categoryList.map {
                        ListTopicBindingModel_()
                            .id(it.id)
                    }
                )
                .addTo(this)
        }


        list.forEach { list ->
            ListAllBindingModel_()
                .id(modelCountBuiltSoFar)
                .listAllModel(list)
                .addTo(this)
        }
    }
}