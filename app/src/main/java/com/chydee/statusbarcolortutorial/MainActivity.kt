package com.chydee.statusbarcolortutorial

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.chydee.statusbarcolortutorial.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.nav_host_fragment)
        setSelectedBottomNavItem()
        setupNavigationViewController()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (navController.currentDestination?.id != R.id.homeFragment) {
            navController.navigateUp()
        } else {
            super.onBackPressed()
        }
    }

    /**
     * This function sets the selected Item on the BottomNaviagtionView
     * to Home on Draw.
     */
    private fun setSelectedBottomNavItem() {
        binding.bottomNavView.selectedItemId = R.id.homeFragment
    }

    private fun setupNavigationViewController() {
        navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val navView: BottomNavigationView = binding.root.findViewById(R.id.bottomNavView)
        navView.setupWithNavController(navController)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.favoritesFragment, R.id.homeFragment,
                R.id.androidFragment
            )
        )
        NavigationUI.setupActionBarWithNavController(
            this,
            navController,
            appBarConfiguration
        )

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> {
                    supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.DKGRAY))
                    setStatusBarColor(Color.DKGRAY)
                }
                R.id.favoritesFragment -> {
                    supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.BLUE))
                    setStatusBarColor(Color.BLUE)
                }

                R.id.androidFragment -> {
                    supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.BLACK))
                    setStatusBarColor(Color.BLACK)
                }

                else -> {
                    //Do nothing
                }
            }
        }
    }

    /**
     * change the status bar color programmatically (and provided the device has Android 5.0)
     * then you can use Window.setStatusBarColor().
     * It shouldn't make a difference whether the activity is derived from Activity or ActionBarActivity.
     */
    private fun setStatusBarColor(color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = color
        }
    }
}