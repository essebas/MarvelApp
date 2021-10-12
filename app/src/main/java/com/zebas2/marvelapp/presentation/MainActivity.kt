package com.zebas2.marvelapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.zebas2.marvelapp.R
import com.zebas2.marvelapp.data.util.Prefers
import com.zebas2.marvelapp.databinding.ActivityMainBinding
import com.zebas2.marvelapp.presentation.adapter.CharacterAdapter
import com.zebas2.marvelapp.presentation.viewmodel.CharactersViewModel
import com.zebas2.marvelapp.presentation.viewmodel.CharactersViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: CharactersViewModelFactory

    @Inject
    lateinit var characterAdapter: CharacterAdapter

    @Inject
    lateinit var preferences: Prefers

    lateinit var viewModel: CharactersViewModel

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBar)
        setUpNavigation()

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(CharactersViewModel::class.java)

        preferences.resetOffsetDB()
    }

    private fun setUpNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration.Builder(navController.graph).build()
        supportActionBar?.apply {
            setupActionBarWithNavController(navController, appBarConfiguration)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}