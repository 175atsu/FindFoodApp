package com.example.findfood.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.findfood.R
import com.example.findfood.RetrofitInstance
//import com.example.findfood.db.FoodResponse
import com.example.findfood.db.Shop
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    var mContext: Context? = null
    val Retrofit = RetrofitInstance()

    //謎
    companion object {
        fun createInstance(mc: Context): HomeFragment {
            val listFragment = HomeFragment()
            listFragment.mContext = mc
            return listFragment
        }
    }

    private val controller by lazy {
        HomeAllController()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView.let {
            it.adapter = controller.adapter
            it.layoutManager = LinearLayoutManager(this.activity, RecyclerView.VERTICAL, false)
            it.addItemDecoration(
                DividerItemDecoration(
                    this.activity,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
        getData()
    }

    private fun getData() = searchGitHubRepositoryByCoroutines()

    //coroutine利用
    suspend fun qiitRepositoriesByCoroutines(
        key: String,
        largeArea: String,
        format: String
    ): List<Shop> {
        return Retrofit.createService().apiDemo(key = key, largeArea = largeArea, format = format).results.shop
    }

    val coroutineScope = CoroutineScope(context = Dispatchers.Main)
    fun searchGitHubRepositoryByCoroutines() {
        val dataList = mutableListOf<HomeViewModel>()
        Log.d("テスト1", dataList.toString())
        coroutineScope.launch {
            try {
                val qiitaRepositoriesData = qiitRepositoriesByCoroutines(
                    key = "eafd5fb1321096c8",
                    largeArea = "Z011",
                    format = "json"
                )
                qiitaRepositoriesData.let {
                    for (item in it) {
                        val data: HomeViewModel = HomeViewModel()
                            .also {
                                //item.shop?.add(Shop(name = it.name, id = null, lunch = it.lunch))
                                it.name = item?.name
                                it.lunch = item?.lunch
//                                if (item.shop.log_image != null) {
//                                    Glide.with(HomeFragment())
//                                        .load(item.shop.log_image)
//                                        .error(android.R.drawable.btn_star_big_on)
//                                        .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
//                                        .override(300, 300)
//                                        .into(it.shopImage)
//                                }
                            }
                        dataList.add(data)
                        Log.d("テスト2", dataList.toString())
                    }
                    //更新
                    controller.list = dataList
                }
            } catch (e: HttpException) {
                Log.d("TAGres", "onFailure")
                // リクエスト失敗時の処理を行う
            }
        }
        Log.d("テスト3", dataList.toString())
    }

}