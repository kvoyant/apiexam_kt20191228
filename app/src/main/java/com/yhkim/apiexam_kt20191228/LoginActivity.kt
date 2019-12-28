package com.yhkim.apiexam_kt20191228

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.yhkim.apiexam_kt20191228.utils.ConnectServer
import com.yhkim.apiexam_kt20191228.utils.ContextUtil
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

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
//            Toast.makeText(mContext, "정상 입력이라 로그인 시도해야함.", Toast.LENGTH_SHORT).show()
            ConnectServer.postRequestLogin(mContext, inputId, inputPw, object : ConnectServer.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {
//test_user / qlalfqjsgh!
                    Log.d("서버응답 json", json.toString())
                    /*
{"code":200,"message":"로그인 성공","data":{"user":{"id":3,"login_id":"test_user","name":"Tester","phone":"01092924848","memo":"tttt","category":{"id":10,"title":"ㄷㄷㄱ","color":"#FF8EFF72"},"is_admin":true,"start_date":null,"expire_date":"2019-09-30","created_at":"2019-09-07 07:32:52","level":1},"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpZCI6MywibG9naW5faWQiOiJ0ZXN0X3VzZXIiLCJwYXNzd29yZCI6IjVlNmUyMzM1ZTMyNzQ1MTA4OTU3MDk5NGZkYmUwMTU2In0.g2NYwLbY2suxgH4sZsRWRCF40leWIzUaozZjwyFORK5QG1mWtaTDxWiWYGzC8-mP2WTPC6yUaIEr5x-SLZh5ww"}}
{"code":400,"message":"아이디가 존재하지 않습니다."}
{"code":400,"message":"비밀번호가 올바르지 않습니다."}
                    */

                    val code = json.getInt("code")
//                    code : 200 이면 로그인 성공 토스트 , code : 200 이 아니면 로그인 실패 토스트

                    runOnUiThread {
                        if( code == 200 ) {
                            Toast.makeText(mContext, "로그인 성공", Toast.LENGTH_SHORT).show()

                            var data = json.getJSONObject("data")
                            var token = data.getString("token")

//                            받아온 토큰을 내 폰에 반영구 저장
                            ContextUtil
                        }
                        else {
                            val message = json.getString("message")
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
    }

    override fun setValues() {
    }

}
