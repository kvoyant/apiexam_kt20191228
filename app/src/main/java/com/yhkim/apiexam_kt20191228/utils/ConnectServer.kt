package com.yhkim.apiexam_kt20191228.utils

import android.content.Context
import android.util.Log
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ConnectServer {

    interface JsonResponseHandler {
        fun onResponse(json: JSONObject)
    }

    companion object {

//        연결할 서버의 주소 (도메인 / IP )를 변수로 저장.
        val BASE_URL = "http://192.168.0.10:5000"

//        [ 로그인 기능 ]
        fun postRequestLogin(context: Context, id:String, pw:String, handler: JsonResponseHandler?) {
            val client = OkHttpClient()
            val url = "${BASE_URL}/auth"
            val formData = FormBody.Builder().add("login_id", id).add("password",pw).build()

            var request = Request.Builder().url(url)
//          .header("X-Http-Token", inputToken) //헤더에 실어 보낼 정보가 있다면
//                    헤더(header)로 담아야 하는 데이터가 있다면 여기에 .header("이름", 값) 으로 추가해줘야함
                .post(formData).build()// 모두 첨부 완료했으면 build()로 마무리. 완성.

            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
                    Log.d("서버 연결 실패", e.localizedMessage)
                }

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body()!!.string()
                    val json = JSONObject(body) //string으로 저장된 응답을 JSON양식으로 가공.
//                    json변수의 내용을 분석해서 상황에 따른 처리를 할 수 있게됨.

//                    JSON 상세 분석 or 결과에 따른 처리를 화면(Activity)로 이관.
                    handler?.onResponse(json) //? handler가 있다면 (널이 아니라면)
                }

            })

        }
    }
}