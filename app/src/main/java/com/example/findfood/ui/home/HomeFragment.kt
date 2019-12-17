package com.example.findfood.ui.home

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.findfood.R
import com.example.findfood.RetrofitInstance
import com.example.findfood.db.Shop
import com.example.findfood.ui.WebViewFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class HomeFragment : Fragment(),HomeAllController.ClickListener {

    private lateinit var homeViewModel: HomeViewModel

    var mContext: Context? = null
    val Retrofit = RetrofitInstance()
    val homeAllList = mutableListOf<HomeViewModel>()
    val homeTopicList = mutableListOf<HomeTopicModel>()

    //謎
    companion object {
        fun createInstance(mc: Context): HomeFragment {
            val listFragment = HomeFragment()
            listFragment.mContext = mc
            return listFragment
        }
    }

    private val controller by lazy {
        HomeAllController(this)
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

    override fun itemClickListener(item: HomeViewModel) =
        CustomTabsIntent.Builder()
            .setShowTitle(true)
            .setToolbarColor(ContextCompat.getColor(this.activity!!, R.color.title))
            .build().launchUrl(this.activity, Uri.parse(item.url))

    private fun getData() = searchGitHubRepositoryByCoroutines()

    //coroutine利用
    suspend fun foodAllRepositoriesByCoroutines(
        key: String,
        largeArea: String,
        count: Int,
        format: String
    ): List<Shop> {
        return Retrofit.createService().apiDemo(key = key, largeArea = largeArea, count = count, format = format)
            .results.shop
    }

    val coroutineScope = CoroutineScope(context = Dispatchers.Main)
    fun searchGitHubRepositoryByCoroutines() {
        Log.d("テスト1", homeAllList.toString())
        coroutineScope.launch {
            try {
                val foodAllRepositoriesData = foodAllRepositoriesByCoroutines(
                    key = "eafd5fb1321096c8",
                    largeArea = "Z011",
                    count = 50,
                    format = "json"
                )
                foodAllRepositoriesData.let {
                    for (item in it) {
                        val data: HomeViewModel = HomeViewModel()
                            .also {
                                it.name = item?.name
                                it.lunch = item?.lunch
                                it.url = item?.urls?.pc
                                Log.d("テスト4", item.logo_image.toString())
                                val image = it.shopImage
                                if (image != null) {
                                    Glide.with(HomeFragment())
                                        .load(item.logo_image)
                                        .error(android.R.drawable.btn_star_big_on)
                                        .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
                                        .override(300, 300)
                                        .into(image)
                                }
                                Log.d("テスト2", image.toString())
                            }
                        val topicData: HomeTopicModel = HomeTopicModel()
                            .also {
                                it.name = item?.name
                                it.lunch = item?.lunch
                                it.url = item?.urls?.pc
                            }
                        homeAllList.add(data)
                        homeTopicList.add(topicData)
                    }
                    //更新
                    controller.list = homeAllList
                    controller.categoryList = homeTopicList
                }
            } catch (e: HttpException) {
                // リクエスト失敗時の処理を行う
            }
        }
        Log.d("テスト3", homeAllList.toString())
    }


    //詳細ページへの遷移
    fun toDetail(urlData: HomeViewModel) {
        val fragment = WebViewFragment()
        val bundle = Bundle().apply {
            putString("URL", urlData.url)
        }
        fragment.setArguments(bundle)
        // FragmentをFragmentManagerにセットする
        getFragmentManager()!!.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.detailContainer, fragment)
            .commit()
    }

}