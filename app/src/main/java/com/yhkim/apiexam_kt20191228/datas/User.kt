package com.yhkim.apiexam_kt20191228.datas

import org.json.JSONObject
import java.io.Serializable

class User : Serializable {
/*
    "id": 3,
    "login_id": "test_user",
    "name": "Tester",
    "phone": "01092924848",
    "memo": "tttt",
 */
    var id = -1 // Int임을 명시 + id가 -1 이라면 파싱이 제대로 안됐다는 구별.
    var loginId = ""
    var name = ""
    var phoneNum = ""
    var memo = ""

    companion object {
//        JSONOBJECT를 기반으로 => User 변수로 변환해 주는 기능

        fun getUserFromJson(json: JSONObject) : User {
            val parsedUser = User()
//             기본 데이터 => json벼눗에서 따온 값으로 대체

            parsedUser.id = json.getInt("id")
            parsedUser.loginId = json.getString("login_id")
            parsedUser.name = json.getString("name")
            parsedUser.phoneNum = json.getString("phone")
            parsedUser.memo = json.getString("memo")

            return parsedUser
        }

    }
}