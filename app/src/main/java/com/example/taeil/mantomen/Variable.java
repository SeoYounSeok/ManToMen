package com.example.taeil.mantomen;

import android.widget.EditText;

public class Variable { //전역변수 저장 모음 로그인할 때 한번에 받아와서 로그인한 user의 정보를 저장하는 변수 모음

    private static String userID = null;
    private static String userPassword = null;
    private static String userEmail = null;
    private static String userName = null;
    private static String userAge = null;
    private static String userGender = null;
    private static Variable variable;
    private static String userCategory = null; //사용자 관심분야 6칸으로 만들예정
    private static String userIdentity = null; //튜티인지 튜터인지
    private static int userPicture; //사용자 사진
    private static String userParticipateClass = null; //사용자가 참여중인 클래스
    private static String userOperateClass = null; //사용자가 운영중인 클래스
    public static String HttpAddres = "http://ec2-54-180-106-61.ap-northeast-2.compute.amazonaws.com";   //민영이 서버 입력하기
    private static String Authnumber = null;

    private static String userPhoneNumber = null;

    public static String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public static void setUserPhoneNumber(String userPhoneNumber) {
        Variable.userPhoneNumber = userPhoneNumber;
    }

    public static String getAuthnumber() {
        return Authnumber;
    }

    public static void setAuthnumber(String authnumber) {
        Authnumber = authnumber;
    }

    private Variable() {
        variable = new  Variable();
    }

    public static Variable getInstance(){
        return variable;
    }

    public static String getUserID() {
        return userID;
    }

    public static void setUserID(String userID) {
        Variable.userID = userID;
    }

    public static String getUserPassword() {
        return userPassword;
    }

    public static void setUserPassword(String userPassword) {
        Variable.userPassword = userPassword;
    }

    public static String getUserEmail() {
        return userEmail;
    }

    public static void setUserEmail(String userEmail) {
        Variable.userEmail = userEmail;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        Variable.userName = userName;
    }

    public static String getUserAge() {
        return userAge;
    }

    public static void setUserAge(String userAge) {
        Variable.userAge = userAge;
    }

    public static String getUserGender() {
        return userGender;
    }

    public static void setUserGender(String userGender) {
        Variable.userGender = userGender;
    }

    public static String getUserCategory() {
        return userCategory;
    }

    public static void setUserCategory(String userCategory) {
        Variable.userCategory = userCategory;
    }

    public static String getUserIdentity() {
        return userIdentity;
    }

    public static void setUserIdentity(String userIdentity) {
        Variable.userIdentity = userIdentity;
    }

    public static int getUserPicture() {
        return userPicture;
    }

    public static void setUserPicture(int userPicture) {
        Variable.userPicture = userPicture;
    }

    public static String getUserParticipateClass() {
        return userParticipateClass;
    }

    public static void setUserParticipateClass(String userParticipateClass) {
        Variable.userParticipateClass = userParticipateClass;
    }

    public static String getUserOperateClass() {
        return userOperateClass;
    }

    public static void setUserOperateClass(String userOperateClass) {
        Variable.userOperateClass = userOperateClass;
    }
}
