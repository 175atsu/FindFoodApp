package com.example.findfood.ui.home

import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.carousel
import com.example.findfood.ListAllBindingModel_
import com.example.findfood.ListTopicBindingModel_

class HomeAllController(val callback: ClickListener) : EpoxyController() {


    interface ClickListener {
        fun itemClickListener(item: HomeViewModel)
    }

    interface CarouselListener {
        fun itemClickListener(item: HomeTopicModel)
    }

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
//                            .itemClickListener { _, _, _, _ ->
//                                callback.itemClickListener()
//                            }
                    }
                )
                .addTo(this)
        }


        list.forEach { list ->
            ListAllBindingModel_()
                .id(modelCountBuiltSoFar)
                .listAllModel(list)
                .itemClickListener { _, _, _, _ ->
                    callback.itemClickListener(list)
                }
                .addTo(this)
        }
    }
}