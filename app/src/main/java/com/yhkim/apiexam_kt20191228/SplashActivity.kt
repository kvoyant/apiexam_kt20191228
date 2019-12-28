package com.yhkim.apiexam_kt20191228

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.bumptech.glide.Glide
import com.yhkim.apiexam_kt20191228.utils.ContextUtil
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupEvents()
        setValues()
    }
    override fun setupEvents() {

    }

    override fun setValues() {
        Glide.with(mContext)
//            .load("https://ncache.ilbe.com/files/attach/new/20160302/377678/2709220375/7625736155/8726846255829e6a14551b4b87e0c086.gif")
            .load(R.drawable.gugucat01)
            .into(splashImgview)
        openProperActivity()
    }

    fun openProperActivity() {
        Handler().postDelayed({
            if(ContextUtil.getUserToken(mContext) == "") {
//            로그인 안한 상태 라면 (로그인 토큰이 저장되지 않았다 => 새로 로그인 해야함 )
                val intent = Intent(mContext, LoginActivity::class.java)
                startActivity(intent)
                finish()

            }
            else {
//            저장된 토큰 있음 , 메인화면으로 이동
                val intent = Intent(mContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 1500)
    }

}
