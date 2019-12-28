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
                "id": 76,
                "login_id": "T11",
                "name": "T11",
                "phone": "811",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 05:12:33"
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
                "id": 73,
                "login_id": "test1013t",
                "name": "Test001",
                "phone": "01012345578",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 04:34:03"
            },
            {
                "id": 72,
                "login_id": "test1012",
                "name": "Test001",
                "phone": "01012345578",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 04:33:55"
            },
            {
                "id": 71,
                "login_id": "yhkim04",
                "name": "chris",
                "phone": "01022233333",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 04:33:51"
            },
            {
                "id": 70,
                "login_id": "test1011",
                "name": "Test001",
                "phone": "01012345578",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 04:33:21"
            },
            {
                "id": 69,
                "login_id": "gyu1",
                "name": "ghlee",
                "phone": "01011112222",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 04:33:16"
            },
            {
                "id": 68,
                "login_id": "notest2",
                "name": "notest",
                "phone": "2222",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 04:32:36"
            },
            {
                "id": 67,
                "login_id": "notest",
                "name": "notest",
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
                "created_at": "2019-12-22 03:49:56"
            },
            {
                "id": 66,
                "login_id": "gyu6",
                "name": "ghlee",
                "phone": "01066667777",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 03:49:37"
            },
            {
                "id": 65,
                "login_id": "qwerqwerqq",
                "name": "qwe",
                "phone": "111122223333",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 03:49:05"
            },
            {
                "id": 64,
                "login_id": "asdfqwer",
                "name": "asdf",
                "phone": "01012345678",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 03:49:04"
            },
            {
                "id": 63,
                "login_id": "fff",
                "name": "fff",
                "phone": "01011112222",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 03:48:57"
            },
            {
                "id": 62,
                "login_id": "test44",
                "name": "test44",
                "phone": "01023231212",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 03:48:55"
            },
            {
                "id": 61,
                "login_id": "cha06",
                "name": "cha",
                "phone": "01012123434",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 03:48:43"
            },
            {
                "id": 60,
                "login_id": "eee",
                "name": "eee",
                "phone": "01011112222",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 03:48:18"
            },
            {
                "id": 59,
                "login_id": "gyu5",
                "name": "ghlee",
                "phone": "01055556666",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 03:48:18"
            },
            {
                "id": 58,
                "login_id": "cha04",
                "name": "cha",
                "phone": "01012123434",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 03:48:17"
            },
            {
                "id": 57,
                "login_id": "qwerqwer",
                "name": "qwe",
                "phone": "111122223333",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 03:48:17"
            },
            {
                "id": 56,
                "login_id": "test033",
                "name": "test03",
                "phone": "030303030303",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 03:45:28"
            },
            {
                "id": 55,
                "login_id": "cha03",
                "name": "cha",
                "phone": "01012123434",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 03:44:16"
            },
            {
                "id": 54,
                "login_id": "gyu4",
                "name": "ghlee",
                "phone": "01044445555",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 03:44:12"
            },
            {
                "id": 53,
                "login_id": "test0004",
                "name": "Tester004",
                "phone": "01052146213",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 03:44:10"
            },
            {
                "id": 52,
                "login_id": "yhkim03",
                "name": "chris",
                "phone": "01011112222",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 03:44:00"
            },
            {
                "id": 51,
                "login_id": "ddd",
                "name": "ddd",
                "phone": "01011112222",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 03:44:00"
            },
            {
                "id": 50,
                "login_id": "gyu3",
                "name": "ghlee",
                "phone": "01033334444",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 03:39:55"
            },
            {
                "id": 49,
                "login_id": "test03",
                "name": "test03",
                "phone": "03003030303",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 03:39:34"
            },
            {
                "id": 48,
                "login_id": "yhkim02",
                "name": "chris",
                "phone": "01011112222",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 03:39:32"
            },
            {
                "id": 47,
                "login_id": "ccc",
                "name": "ccc",
                "phone": "01011112222",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 03:39:31"
            },
            {
                "id": 46,
                "login_id": "test0003",
                "name": "Test003",
                "phone": "01045783314",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 03:39:27"
            },
            {
                "id": 45,
                "login_id": "test2020",
                "name": "Test002",
                "phone": "01078945612",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 03:37:10"
            },
            {
                "id": 44,
                "login_id": "bbb",
                "name": "bbb",
                "phone": "01011112222",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 03:37:08"
            },
            {
                "id": 43,
                "login_id": "test02",
                "name": "test02",
                "phone": "02002020202",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 03:37:08"
            },
            {
                "id": 42,
                "login_id": "yhkim01",
                "name": "chris",
                "phone": "01012345678",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 03:37:03"
            },
            {
                "id": 41,
                "login_id": "gyu2",
                "name": "ghlee",
                "phone": "01022223333",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 03:37:03"
            },
            {
                "id": 40,
                "login_id": "cha01",
                "name": "asfd",
                "phone": "27332733",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 03:36:58"
            },
            {
                "id": 39,
                "login_id": "aaa",
                "name": "aaa",
                "phone": "01011112222",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 03:30:48"
            },
            {
                "id": 38,
                "login_id": "cha102",
                "name": "차순혁",
                "phone": "01025456585",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 03:22:46"
            },
            {
                "id": 37,
                "login_id": "cha056",
                "name": "chacha",
                "phone": "01012123434",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 03:21:47"
            },
            {
                "id": 36,
                "login_id": "cha02",
                "name": "chacha",
                "phone": "01012123434",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 03:21:23"
            },
            {
                "id": 35,
                "login_id": "test1010",
                "name": "조경진테스트",
                "phone": "01051123237",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 03:18:49"
            },
            {
                "id": 34,
                "login_id": "gyu",
                "name": "ghlee",
                "phone": "01012345678",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 03:18:48"
            },
            {
                "id": 33,
                "login_id": "test01",
                "name": "test",
                "phone": "01001010101",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 03:18:48"
            },
            {
                "id": 32,
                "login_id": "yhkim",
                "name": "chris",
                "phone": "01012345678",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 03:18:43"
            },
            {
                "id": 31,
                "login_id": "1",
                "name": "1",
                "phone": "1",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-12-22 01:18:27"
            },
            {
                "id": 30,
                "login_id": "choco",
                "name": "chocojoha",
                "phone": "010-1234-5890",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-11-21 12:54:04"
            },
            {
                "id": 29,
                "login_id": "ManduJoha",
                "name": "mandu mandu",
                "phone": "010-1234-5689",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-11-21 12:53:18"
            },
            {
                "id": 28,
                "login_id": "mydoory",
                "name": "mydoory",
                "phone": "010-1111-2222",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-11-18 12:50:48"
            },
            {
                "id": 27,
                "login_id": "ducho",
                "name": "ducho",
                "phone": "010-0000-0000",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-11-18 10:07:26"
            },
            {
                "id": 26,
                "login_id": "qqqw",
                "name": "dhdh",
                "phone": "dhdh",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-11-14 13:24:40"
            },
            {
                "id": 25,
                "login_id": "wmJuice",
                "name": "subakjuice",
                "phone": "010-1234-1234",
                "memo": "새 회원",
                "category": {
                    "id": 7,
                    "title": "피씨방",
                    "color": "#FF5900FF"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-11-14 13:19:07"
            },
            {
                "id": 24,
                "login_id": "cho881020123",
                "name": "TEst123",
                "phone": "01051123237",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-11-14 13:18:20"
            },
            {
                "id": 23,
                "login_id": "iddd",
                "name": "hi",
                "phone": "123456789",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-11-14 13:18:18"
            },
            {
                "id": 22,
                "login_id": "1111111",
                "name": "1111111",
                "phone": "010-000-1234",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-11-14 13:17:52"
            },
            {
                "id": 21,
                "login_id": "mango",
                "name": "mangoJuice",
                "phone": "010-1234-1234",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-11-14 13:17:46"
            },
            {
                "id": 20,
                "login_id": "id",
                "name": "id",
                "phone": "123456789",
                "memo": "새 회원",
                "category": {
                    "id": 4,
                    "title": "당구장",
                    "color": "#FFFF8612"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-11-14 13:16:13"
            },
            {
                "id": 19,
                "login_id": "qwer1234",
                "name": "Test123",
                "phone": "010-5112-3237",
                "memo": "새 회원",
                "category": {
                    "id": 2,
                    "title": "테스팅매장",
                    "color": "#00FF00"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-11-14 13:15:46"
            },
            {
                "id": 18,
                "login_id": "mskim",
                "name": "mskim",
                "phone": "010-3124-7792",
                "memo": "새 회원",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-11-14 13:15:24"
            },
            {
                "id": 17,
                "login_id": "jessica",
                "name": "Jessica Millano",
                "phone": "010-1234-1234",
                "memo": "새 회원",
                "category": {
                    "id": 3,
                    "title": "식당",
                    "color": "#0000FF"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-11-14 13:14:12"
            },
            {
                "id": 16,
                "login_id": "minsun1010",
                "name": "ddoni",
                "phone": "01012341234",
                "memo": "새 회원",
                "category": {
                    "id": 3,
                    "title": "식당",
                    "color": "#0000FF"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-11-14 13:13:28"
            },
            {
                "id": 15,
                "login_id": "aa1234",
                "name": "ddoni",
                "phone": "01012341234",
                "memo": "새 회원",
                "category": {
                    "id": 7,
                    "title": "피씨방",
                    "color": "#FF5900FF"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-11-14 13:12:50"
            },
            {
                "id": 14,
                "login_id": "aa",
                "name": "ddoni",
                "phone": "01012341234",
                "memo": "새 회원",
                "category": {
                    "id": 2,
                    "title": "테스팅매장",
                    "color": "#00FF00"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-11-14 13:12:28"
            },
            {
                "id": 13,
                "login_id": "aaaa",
                "name": "aa",
                "phone": "01012341234",
                "memo": "새 회원",
                "category": {
                    "id": 2,
                    "title": "테스팅매장",
                    "color": "#00FF00"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-11-14 13:11:52"
            },
            {
                "id": 12,
                "login_id": "abc",
                "name": "1234",
                "phone": "010-1234-1231",
                "memo": "새 회원",
                "category": {
                    "id": 7,
                    "title": "피씨방",
                    "color": "#FF5900FF"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-11-14 13:11:13"
            },
            {
                "id": 11,
                "login_id": "kiback2da",
                "name": "김기백",
                "phone": "010-9368-6483",
                "memo": "새 회원",
                "category": {
                    "id": 7,
                    "title": "피씨방",
                    "color": "#FF5900FF"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-11-14 13:08:42"
            },
            {
                "id": 10,
                "login_id": "asdf",
                "name": "가입테스트",
                "phone": "010-5112-3237",
                "memo": "새 회원",
                "category": {
                    "id": 4,
                    "title": "당구장",
                    "color": "#FFFF8612"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": null,
                "created_at": "2019-11-14 12:48:16"
            },
            {
                "id": 4,
                "login_id": "test_user2",
                "name": "시험대상",
                "phone": "01092924848",
                "memo": "수정되나",
                "category": {
                    "id": 2,
                    "title": "테스팅매장",
                    "color": "#00FF00"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": "2019-10-03",
                "created_at": "2019-09-07 07:33:22"
            },
            {
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
                "is_admin": false,
                "start_date": null,
                "expire_date": "2019-09-30",
                "created_at": "2019-09-07 07:32:52"
            },
            {
                "id": 2,
                "login_id": "test123",
                "name": "테스트",
                "phone": "01012345678",
                "memo": "",
                "category": {
                    "id": 1,
                    "title": "일반매장",
                    "color": "#FF0000"
                },
                "is_admin": false,
                "start_date": null,
                "expire_date": "2019-09-30",
                "created_at": "2019-08-23 17:32:12"
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
            urlBuilder.addEncodedQueryParameter("acitive", "ALL")//get 방식 파리미터 있을경우
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