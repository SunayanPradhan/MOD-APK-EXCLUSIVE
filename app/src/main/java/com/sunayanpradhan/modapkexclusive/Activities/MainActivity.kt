package com.sunayanpradhan.modapkexclusive.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sunayanpradhan.modapkexclusive.Fragments.AppsFragment
import com.sunayanpradhan.modapkexclusive.Fragments.EditorsChoiceFragment
import com.sunayanpradhan.modapkexclusive.Fragments.GamesFragment
import com.sunayanpradhan.modapkexclusive.Fragments.PremiumFragment
import com.sunayanpradhan.modapkexclusive.R

class MainActivity : AppCompatActivity() {

    lateinit var bottomBar: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(AppsFragment())

        bottomBar=findViewById(R.id.bottom_bar)

        bottomBar.setOnNavigationItemSelectedListener {

            when(it.itemId){

                R.id.nav_apps ->replaceFragment(AppsFragment())
                R.id.nav_games ->replaceFragment(GamesFragment())
                R.id.nav_premium ->replaceFragment(PremiumFragment())
                R.id.nav_choice ->replaceFragment(EditorsChoiceFragment())

            }

            return@setOnNavigationItemSelectedListener true
        }



    }

    private fun replaceFragment(fragment: Fragment){

        if (fragment!=null){

            val transaction=supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragments,fragment)

            transaction.commit()

        }

    }
}