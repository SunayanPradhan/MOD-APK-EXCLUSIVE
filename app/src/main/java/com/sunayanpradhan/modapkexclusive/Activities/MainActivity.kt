package com.sunayanpradhan.modapkexclusive.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.ads.MobileAds
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sunayanpradhan.modapkexclusive.Fragments.AppsFragment
import com.sunayanpradhan.modapkexclusive.Fragments.EditorsChoiceFragment
import com.sunayanpradhan.modapkexclusive.Fragments.GamesFragment
import com.sunayanpradhan.modapkexclusive.Fragments.PremiumFragment
import com.sunayanpradhan.modapkexclusive.R

class MainActivity : AppCompatActivity() {

    private var backPressedTime: Long = 0
    private var backToast: Toast? = null

    lateinit var bottomBar: BottomNavigationView
    lateinit var search:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this) {}

        bottomBar=findViewById(R.id.bottom_bar)
        search=findViewById(R.id.search)

        replaceFragment(AppsFragment())

        bottomBar.setOnNavigationItemSelectedListener {

            when(it.itemId){

                R.id.nav_apps ->replaceFragment(AppsFragment())
                R.id.nav_games ->replaceFragment(GamesFragment())
                R.id.nav_premium ->replaceFragment(PremiumFragment())
                R.id.nav_choice ->replaceFragment(EditorsChoiceFragment())

            }

            return@setOnNavigationItemSelectedListener true
        }

        search.setOnClickListener {

            startActivity(Intent(this,SearchActivity::class.java))
            overridePendingTransition(0, 0)

        }


    }

    private fun replaceFragment(fragment: Fragment){

        if (fragment!=null){

            val transaction=supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragments,fragment)

            transaction.commit()

        }

    }

    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast?.cancel()
            finishAffinity()
        }

        else {
            backToast = Toast.makeText(baseContext, "Double press to Exit", Toast.LENGTH_SHORT)
            backToast?.show()
        }
        backPressedTime = System.currentTimeMillis()

    }

}