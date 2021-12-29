package com.kumoh.realtimekeyword

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.navigation.NavigationView
import com.kumoh.realtimekeyword.fragments.*
import ru.semper_viventem.backdrop.BackdropBehavior
import com.kumoh.realtimekeyword.findBehavior


class MainActivity : AppCompatActivity() {
    private lateinit var backdropBehavior: BackdropBehavior
    private lateinit var frontLayout: FrameLayout
    private lateinit var toolbar: Toolbar
    private lateinit var navigationView: NavigationView

    companion object {
        private const val ARG_LAST_MENU_ITEM = "last_menu_item"

        private const val MENU_HOTTOPIC         = R.id.itemHotTopic
        private const val MENU_POLITICS         = R.id.itemPolitics
        private const val MENU_ENTERTAINMENT    = R.id.itemEntertainment
        private const val MENU_SPORTS           = R.id.itemSports
        private const val MENU_LIFE_CULTURE     = R.id.itemLifeAndCulture
        private const val MENU_WORLD            = R.id.itemWorld
        private const val MENU_IT_SCIENCE       = R.id.itemITAndScience

        private const val FRAGMENT_CONTAINER = R.id.frontLayout

        private const val DEFAULT_ITEM = MENU_HOTTOPIC
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*****************************For Navigation Drawer********************************/

        frontLayout = findViewById(R.id.frontLayout)
        toolbar = findViewById(R.id.toolbar)
        navigationView = findViewById(R.id.navigationView)

        backdropBehavior = frontLayout.findBehavior()
        with(backdropBehavior) {
            attachBackLayout(R.id.backLayout)
        }
        with(toolbar) {
            title = "핫 토픽"
        }

        navigationView.setNavigationItemSelectedListener { item ->
            checkMenuPosition(item.itemId)
            backdropBehavior.close()
            true
        }

        val currentItem = savedInstanceState?.getInt(ARG_LAST_MENU_ITEM) ?: DEFAULT_ITEM
        navigationView.setCheckedItem(currentItem)
        checkMenuPosition(navigationView.checkedItem!!.itemId)


        /*********************************************************************************/
    }

    override fun onBackPressed() {
        if (!backdropBehavior.close()) {
            finish()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(ARG_LAST_MENU_ITEM, navigationView.checkedItem!!.itemId)

        super.onSaveInstanceState(outState)
    }

    private fun checkMenuPosition(@IdRes menuItemId: Int) {
        when (menuItemId) {
            MENU_HOTTOPIC         -> {
                showPage(HotTopicFragment())
                toolbar.title = "핫토픽"
            }
            MENU_POLITICS         -> {
                showPage(PoliticsFragment())
                toolbar.title = "정치"
            }
            MENU_ENTERTAINMENT    -> {
                showPage(EntertainmentFragment())
                toolbar.title = "연예"
            }
            MENU_SPORTS           -> {
                showPage(SportsFragment())
                toolbar.title = "스포츠"
            }
            MENU_LIFE_CULTURE     -> {
                showPage(LifeAndCultureFragment())
                toolbar.title = "생활/문화"
            }
            MENU_WORLD            -> {
                showPage(WorldFragment())
                toolbar.title = "세계"
            }
            MENU_IT_SCIENCE       -> {
                showPage(ITAndScienceFragment())
                toolbar.title = "IT/과학"
            }
        }
    }

    private fun showPage(page: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(FRAGMENT_CONTAINER, page)
            .commit()
    }
}

