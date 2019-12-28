package com.yhkim.apiexam_kt20191228

import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

//        로그인 버튼이 눌리면
//        1. ID입력이 빈칸이라면 "ID를 입력해 주세요" 토스트 출력
//        2. PW입력이 8글자 미만이라면 '바번이 너무 짧습니다.' 토스트 출력
//        3. 둘다 괜찮다면 별개로 분기만 준비.
        loginBtn.setOnClickListener {

            val inputId = idEdt.text.toString()
            val inputPw = pwDdt.text.toString()

            if(inputId == "") {
                Toast.makeText(mContext, "ID를 입력해 주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(inputPw.length < 8) {
                Toast.makeText(mContext, "바번이 너무 짧습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

//            ID/PW 가 정상 이어야만 실행 가능
            Toast.makeText(mContext, "정상 입력이라 로그인 시도해야함.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun setValues() {
    }

}
