package com.example.motivation.Ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.motivation.R
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.infra.SecurityPreference
import kotlinx.android.synthetic.main.activity_splash_.*

class Splash_Activity : AppCompatActivity(),View.OnClickListener {

    private lateinit var mSecurityPreference : SecurityPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_)

        mSecurityPreference = SecurityPreference(this)

        if (supportActionBar != null){
            supportActionBar!!.hide()
        }

        bt_salvar.setOnClickListener(this)

        verifyName()
    }

    private fun verifyName() {
        val name = mSecurityPreference.getString(MotivationConstants.KEY.PERSON_NAME)
        if (name != "")  {
          val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.bt_salvar){
              handlosalva()
        }
    }

    private fun handlosalva() {
        val name  = text_nome.text.toString()
        if (name != "" ) {
            mSecurityPreference.storeString(MotivationConstants.KEY.PERSON_NAME, name)
            startActivity(Intent(this, MainActivity::class.java)  )
            finish()

        }else {
            Toast.makeText(this,"Informe seu Nome", Toast.LENGTH_SHORT).show()
        }
    }
}
