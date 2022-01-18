//package com.example.myapplication;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.Response;
//import com.android.volley.toolbox.StringRequest;
//
//import java.lang.reflect.Method;
//import java.util.HashMap;
//import java.util.Map;
//
//public class RegisterRequest extends StringRequest {
//    //서버 URL 설정(php 파일 연동)
//    final static private String URL = "cookforpet.db"; //php 서버의 주소가 들어가야 함
//    private Map<String, String> map;
//    //private Map<String, String>parameters;
//
//    public RegisterRequest(String user_id, String user_pwd, String user_name,Response.Listener<String> listener) {
//        super(Method.POST, URL, listener, null);
//
//        map = new HashMap<>();
//        map.put("user_id", user_id);
//        map.put("user_pwd", user_pwd);
//        map.put("user_name", user_name);
//    }
//
//    @Override
//    protected Map<String, String>getParams() throws AuthFailureError {
//        return map;
//    }
//}
