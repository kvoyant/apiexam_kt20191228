package com.yhkim.apiexam_kt20191228

import android.os.Bundle
import com.yhkim.apiexam_kt20191228.datas.User
import kotlinx.android.synthetic.main.activity_user_info.*

class UserInfoActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
    }

    override fun setValues() {
        val sendUser = intent.getSerializableExtra("userInfo") as User

        userIdTxt.text = sendUser.loginId
        userNameTxt.text = sendUser.name
        userPhoneNumTxt.text = sendUser.phoneNum
        userMemoTxt.text = sendUser.memo
    }

}
