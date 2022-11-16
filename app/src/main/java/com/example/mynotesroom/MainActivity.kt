package com.example.mynotesroom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mynotesroom.controller.UserNoteFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(
                    R.id.fragment_container,
                    UserNoteFragment.newInstance()
                )
                .addToBackStack("")
                .commit()
        }
    }
}