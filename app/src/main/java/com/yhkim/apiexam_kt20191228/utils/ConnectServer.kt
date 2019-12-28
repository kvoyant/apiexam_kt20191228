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

/*

{
    "code": 200,
    "message": "사용자 목록 조회",
    "data": {
        "users": [
            {
                "id": 77,
                "login_id": "t12",
                "name": "t12",
                "phone": "1212121",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 05:41:26"
            },
           {
                "id": 75,
                "login_id": "test001",
                "name": "1111",
                "phone": "1111",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 04:48:34"
            },
            {
                "id": 74,
                "login_id": "t1",
                "name": "t1",
                "phone": "8187383",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 04:34:25"
            },
            {
                "id": 1,
                "login_id": "cho881020",
                "name": "조경진",
                "phone": "01051123237",
                "memo": "ㅇㅇ",
                "category": {
                    "id": 3,
                    "title": "식당",
                    "color": "#0000FF"
                },
                "is_admin": false,
                "start_date": "2019-11-01",
                "expire_date": "2019-12-31",
                "created_at": "2019-08-23 17:32:12"
            }
        ]
    }
}
 */

//        사용자목록 조회

        fun getRequestUserList(context: Context, handler: JsonResponseHandler?) {
            val client = OkHttpClient()

            val urlBuilder = HttpUrl.parse("${BASE_URL}/admin/user")!!.newBuilder()
            urlBuilder.addEncodedQueryParameter("active", "ALL")//get 방식 파리미터 있을경우
            val url = urlBuilder.build().toString()

            val request = Request.Builder()
                .url(url)
//                .header("X-Http-Token", ContextUtil.getUserToken(context))
                .build()

            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
                    Log.d("서버 연결 실패", e.localizedMessage)
                }

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body()!!.string()
                    val json = JSONObject(body) //string으로 저장된 응답을 JSON양식으로 가공.

                    handler?.onResponse(json) //? handler가 있다면 (널이 아니라면)
                }
            })
        }



/*
{
    "code": 200,
    "message": "메인화면 조회 성공",
    "data": {
        "user": {
            "id": 3,
            "login_id": "test_user",
            "name": "Tester",
            "phone": "01092924848",
            "memo": "tttt",
            "category": {
                "id": 10,
                "title": "ㄷㄷㄱ",
                "color": "#FF8EFF72"
            },
            "is_admin": true,
            "start_date": null,
            "expire_date": "2019-09-30",
            "created_at": "2019-09-07 07:32:52",
            "level": 1
        }
    }
}
*/
//http://192.168.0.10:5000/my_info => 내정보 가져오기
//        X-Http-Token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpZCI6MywibG9naW5faWQiOiJ0ZXN0X3VzZXIiLCJwYXNzd29yZCI6IjVlNmUyMzM1ZTMyNzQ1MTA4OTU3MDk5NGZkYmUwMTU2In0.g2NYwLbY2suxgH4sZsRWRCF40leWIzUaozZjwyFORK5QG1mWtaTDxWiWYGzC8-mP2WTPC6yUaIEr5x-SLZh5ww

        fun getRequestMyInfo(context: Context, handler: JsonResponseHandler?) {
            val client = OkHttpClient()

//            GET 방식에 맞는 URL 생성 => 파라미터가 전부 주소에 노출되도록 작성해야함.
//            urlBuil
            val urlBuilder = HttpUrl.parse("${BASE_URL}/my_info")!!.newBuilder()
//            urlBuilder.addEncodedQueryParameter("device_token", "임시값")//get 방식 파리미터 있을경우

//            urlBuilder를 이용해 첨부된 파라미터 들을 활용, url, String으로 저장.
            val url = urlBuilder.build().toString()

            val request = Request.Builder().url(url).header("X-Http-Token", ContextUtil.getUserToken(context))
//            GET  방식은 제일 기본이 되는 API 통신방식 => 메소드를 별도로 명시 하지 않음
                .build()

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