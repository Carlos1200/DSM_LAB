package com.example.practica01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.practica01.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            toggle= ActionBarDrawerToggle(this@MainActivity,drawerLayout,R.string.open,R.string.close)
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()

            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            navView.setNavigationItemSelectedListener{
                when (it.itemId){
                    R.id.opcion1-> {
                        replaceFragment(FirstFragment(),it.title.toString())
                        drawerLayout.closeDrawer(GravityCompat.START)
                    }
                    R.id.opcion2-> {
                        replaceFragment(SecondFragment(),it.title.toString())
                        drawerLayout.closeDrawer(GravityCompat.START)
                    }
                    R.id.opcion3-> {
                        replaceFragment(ThirdFragment(),it.title.toString())
                        drawerLayout.closeDrawer(GravityCompat.START)
                    }
                    R.id.opcion4-> {
                        FirebaseAuth.getInstance().signOut().also {
                            val intent = Intent(this@MainActivity, HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
                true
            }
        }
    }

    private fun replaceFragment(fragment: Fragment,title:String){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView,fragment)
        fragmentTransaction.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            true
        }
        return super.onOptionsItemSelected(item)
    }
}