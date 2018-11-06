package com.example.taeil.mantomen;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {


    //final static private String URL = "http://192.168.219.103/Login.php";
    final static private String URL = "http://ec2-54-180-79-6.ap-northeast-2.compute.amazonaws.com";

    private Map<String, String> parameters; //네임 밸류형식의 키값을 이기 때문에

    public RegisterRequest(String userID, String userPassword, int userStudentID, String userName, int userAge, String userGender, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null); //포스트방식
        parameters = new HashMap<>(); //초기화
        parameters.put("userID",userID); //put 웹으로 정보를 보낼거임 포스트방식으로 userID키값으로 userID값을
        parameters.put("userPassword",userPassword);
        parameters.put("userSID",userStudentID+""); //int형은 ""을추가해줌으로써 문자열로 변환가능
        parameters.put("userName",userName);
        parameters.put("userAge",userAge+"");
        parameters.put("userGender",userGender);
    }

    @Override
    public Map<String, String> getParams(){
        return parameters;   //현재 가지고있는 파라미터를 반환
    }

}
