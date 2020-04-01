package com.example.motivation.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowId
import com.example.motivation.R
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.infra.SecurityPreference
import com.example.motivation.repository.Mock
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.reflect.Constructor
import java.security.Key

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSecurityPreference: SecurityPreference
    private var mPhraseFilter: Int = MotivationConstants.FILTERPHRASE.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

// remove actionbar

        if (supportActionBar != null) {
            supportActionBar!!.hide() }

        mSecurityPreference = SecurityPreference(this)
        val name = mSecurityPreference.getString(MotivationConstants.KEY.PERSON_NAME)
        textName.text = "Olá $name!"


// efeito do button de newphrase


        image_all.setColorFilter(resources.getColor(R.color.colorPrimaryDark))
        handlerNewPhase()

        bt_new_phase.setOnClickListener(this)
        image_all.setOnClickListener(this)
        image_happy.setOnClickListener(this)
        image_morning.setOnClickListener(this)

    }

    override fun onClick(view: View) {
        val id = view.id

        val listFilter = listOf(R.id.image_all, R.id.image_happy, R.id.image_morning)

        if (id == R.id.bt_new_phase) {
            handlerNewPhase()

        } else if (id in listFilter) {
            handlerfilterAll(id)
        }
    }

    private fun handlerfilterAll(id: Int) {

        image_all.setColorFilter(resources.getColor(R.color.colorAccent))
        image_happy.setColorFilter(resources.getColor(R.color.colorAccent))
        image_morning.setColorFilter(resources.getColor(R.color.colorAccent))

        when (id) {
            R.id.image_all -> {
                image_all.setColorFilter(resources.getColor(R.color.colorPrimaryDark))
                mPhraseFilter = MotivationConstants.FILTERPHRASE.ALL
            }

            R.id.image_happy -> {
                image_happy.setColorFilter(resources.getColor(R.color.colorPrimaryDark))
                mPhraseFilter = MotivationConstants.FILTERPHRASE.HAPPY
            }

            R.id.image_morning -> {
                image_morning.setColorFilter(resources.getColor(R.color.colorPrimaryDark))
                mPhraseFilter = MotivationConstants.FILTERPHRASE.MORNING
            }
        }


    }


    private fun handlerNewPhase() {
        textfrase.text = Mock().getPhrase(mPhraseFilter)

    }
}
