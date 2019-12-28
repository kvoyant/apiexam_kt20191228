package com.yhkim.apiexam_kt20191228

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.yhkim.apiexam_kt20191228.datas.User
import com.yhkim.apiexam_kt20191228.utils.ConnectServer
import com.yhkim.apiexam_kt20191228.utils.ContextUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }
    override fun setupEvents() {

//        로그아웃 버튼이 눌리면
//        로그아웃 확인 (제목) / 정말 로그아웃 하시겠습니까? (내용)
//        확인 => 토스트로 로그아웃 / 취소 => 아무일도 X , 버튼 보유
//        사용자 의사확인
        logoutBtn.setOnClickListener {

            val alert = AlertDialog.Builder(mContext)
            alert.setTitle("로그아웃 확인")
            alert.setMessage("정말 로그아웃 하시겠습니까?")
            alert.setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which ->
                Toast.makeText(mContext, "로그아웃", Toast.LENGTH_SHORT).show()

                ContextUtil.setUserToken(mContext, "") //로그아웃( 빈값으로 토큰값 세팅)

                val intent = Intent(mContext, LoginActivity::class.java)
                startActivity(intent)
                finish()
            })
            alert.setNegativeButton("취소", null)
            alert.show()

        }
    }

    override fun setValues() {

        ConnectServer.getRequestMyInfo(mContext, object : ConnectServer.JsonResponseHandler {
            override fun onResponse(json: JSONObject) {
                Log.d("내정보 서버응답", json.toString())
//내정보 서버응답: {"code":200,"message":"메인화면 조회 성공","data":{"user":{"id":3,"login_id":"test_user","name":"Tester","phone":"01092924848","memo":"tttt","category":{"id":10,"title":"ㄷㄷㄱ","color":"#FF8EFF72"},"is_admin":true,"start_date":null,"expire_date":"2019-09-30","created_at":"2019-09-07 07:32:52","level":1}}}

                val code = json.getInt("code")

                runOnUiThread {
                    if(code == 200) {
                        val data = json.getJSONObject("data")
                        val user = data.getJSONObject("user")
                        val userName = user.getString("name")
                        val userPhoneNum = user.getString("phone")

                        val loginUser = User.getUserFromJson(user)

                        nameTxt.text = loginUser.name//nameTxt.text = userName
                        phoneTxt.text = loginUser.phoneNum//phoneTxt.text = userPhoneNum
                        loginIdTxt.text = loginUser.loginId

                        //무한루프 !! 주의
//                        val intent = Intent(mContext, MainActivity::class.java)
//                        startActivity(intent)
//                        finish()

                    }
                    else {
                        Toast.makeText(mContext, "서버에 문제가 있습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        })
    }

}
