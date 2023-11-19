package com.helllo.recyclerviewconceptappday24

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.day24recyclerview.MyAdapter
import com.example.day24recyclerview.NewsDetailActivity

class MainActivity : AppCompatActivity() {

    //Yha per "News" App bna rhe h

    //Jaisa ki hum jante h ki jab kisi chiz ko hame code me baad me "use" karna hota h to uske liye "lateinit"
    // variable bnana padta h, Joki maine bna liya yha per
    lateinit var myRecyclerView : RecyclerView

    lateinit var newsArrayList : ArrayList<News>  //Yha maine ek "ArrayList" bnaya Jisme maine "News" name ka
                                                  //Kotlin Data Class pass kiya, Or iss News Class se News ka Information
                                                   //as a String Form me lega

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        myRecyclerView = findViewById(R.id.recyclerView)

        val newsImageArray = arrayOf(
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
            R.drawable.img5,
            R.drawable.img6,
        )


        //Yha maine "lateinit" Variable "newsheadingArray" ko initialize kiya
        val newsHeadingArray = arrayOf(
            "U.K. Foreign Secretary James Cleverly raises issue of BBC tax searches with EAM Jaishankar",
            "Cooking gas prices hiked by ₹50 for domestic, ₹350.50 for commercial cylinders",
            "Joe Biden appoints two prominent Indian-American corporate leaders to his Export Council",
            "Sergey Lavrov will raise suspected bombing of Nord Stream II at G20: Russian Foreign Ministry",
            "Belarusian leader Lukashenko visits China amid Ukraine tensions",
            "China rips new U.S. House committee on countering Beijing",
            "Largest gathering of Foreign Ministers hosted by any G20 presidency: Foreign Secretary Vinay Kwatra"
        )



        val newsContent = arrayOf(
            getString(R.string.news_content), getString(R.string.news_content),
            getString(R.string.news_content), getString(R.string.news_content),
            getString(R.string.news_content), getString(R.string.news_content)
        )



        //"RecyclerView.LayoutManager" ka matlab h ki RecyclerView ke under chize kaisi dikh rhi h like:- vertcially scrolling,
        //horizontally, uniform grid
        myRecyclerView.layoutManager = LinearLayoutManager(this)

        newsArrayList = arrayListOf<News>() //Sabhi News ko uske Images ke sath Add kar rhe h, iska Alogrithm For loop me h

        for( index in newsImageArray.indices){
            val news = News(newsHeadingArray[index], newsImageArray[index], newsContent[index])
            newsArrayList.add(news)
        }



        var myAdapter = MyAdapter(newsArrayList, this)
        myRecyclerView.adapter = myAdapter

        myAdapter.setOnItemClickListener(object : MyAdapter.onItemClickListener {
            override fun onItemClicking(position: Int) {
                // on clicking each item , what action do you want to perform

                val intent = Intent(this@MainActivity, NewsDetailActivity::class.java)
                intent.putExtra("heading", newsArrayList[position].newsHeading)
                intent.putExtra("imageId", newsArrayList[position].newsImage)
                intent.putExtra("newscontent", newsArrayList[position].newsContent)
                startActivity(intent)
            }
        })


    }
}