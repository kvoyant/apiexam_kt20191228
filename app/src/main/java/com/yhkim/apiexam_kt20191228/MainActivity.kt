package com.yhkim.apiexam_kt20191228

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.yhkim.apiexam_kt20191228.adapters.UserAdapter
import com.yhkim.apiexam_kt20191228.datas.User
import com.yhkim.apiexam_kt20191228.utils.ConnectServer
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : BaseActivity() {

    val userList = ArrayList<User>()
    var mUserAdapter:UserAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        userListView.setOnItemClickListener { parent, view, position, id ->
            val userData = userList.get(position)
            val intent = Intent(mContext, UserInfoActivity::class.java)
            intent.putExtra("userInfo", userData)
            startActivity(intent)
        }

    }

    override fun setValues() {
        mUserAdapter = UserAdapter(mContext, R.layout.user_list_item, userList)
        userListView.adapter = mUserAdapter
    }

    override fun onResume() {
        super.onResume()

        ConnectServer.getRequestUserList(mContext, object : ConnectServer.JsonResponseHandler {
            override fun onResponse(json: JSONObject) {
                Log.d("응답", json.toString())

                val code = json.getInt("code")

                runOnUiThread {
                    if(code == 200 ) {
                        val data = json.getJSONObject("data")
                        val users = data.getJSONArray("users")

                        for(i in 0..users.length() - 1 ) {
                            val userJson = users.getJSONObject(i)

                            val userDataObject = User.getUserFromJson(userJson)
                            userList.add(userDataObject)
                        }

                        mUserAdapter?.notifyDataSetChanged()

                    }
                    else {
                        Toast.makeText(mContext,"서버에 문제가 있습니다.",Toast.LENGTH_SHORT).show()

                    }

                }
            }
        })
    }

}
