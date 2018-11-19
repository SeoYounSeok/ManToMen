package com.example.taeil.mantomen;

import org.json.JSONObject;

import java.util.ArrayList;

public class VariableOfClass { //클래스관련 전역변수모음 특정 클래스 하나를 위한거

    private static VariableOfClass variableofclass;

    private static String ClassPicture = null; // 강의이미지
    private static String ClassName = null;  // 강의제목
    private static String ClassTutorID = null; // 튜터아이디
    private static String ClassTuteeID = null; // 튜티아이디들
    private static String ClassCategory = null; //카테고리
    private static String ClassTotalPeople = null;  // 모집인원
    private static String ClassCurrentPeople = null; // 현재인원
    private static String ClassTutorIntro = null; // 튜터소개
    private static String ClassIntro = null; // 강의소개
    private static String ClassContents = null; // 수업내용(커리큘럼)
    private static String ClassWhom = null; // 수업대상
    private static String ClassPrice = null; // 수강료
    private static String ClassHour = null; // 1회에 몇시간씩
    private static String ClassNumberOfTime = null; // 몇 회
    private static String ClassPlace = null; // 수업 장소
    private static String ClassPlaceDetail = null; //상세위치
    private static String ClassWeek = null; // 수업 요일
    private static String ClassTime = null; // 수업 시간
    private static String ClassFirstTime = null; // 첫 수업일
    private static String ClassIdentity = null;
    private static String ClassScore = null;  // 강의 평균평점

    private static String ReviewClassName = null;
    private static String ReviewuserID = null;
    private static String ReviewContents = null;
    private static String ReviewDate = null;
    private static String ReviewScore = null;

    private static ArrayList<AllClass> allClass = null;  // 클래스관련 컬렉션
    private static ArrayList<AllReview> allReview = null;  // 리뷰관련 컬렉션

    private static String ChatObjectID = null;
    private static String ChatObjectContents = null;
    private static String ChatMyID = null;
    private static String ChatMyContents = null;

    private static ArrayList<ChatData> chatData = null; // 채팅데이터를 담을 어레이리스트

    public static ArrayList<AllReview> getAllReview() {
        return allReview;
    }

    public static void setAllReview(ArrayList<AllReview> allReview) {
        VariableOfClass.allReview = allReview;
    }

    public static ArrayList<AllClass> getAllClass() {
        return allClass;
    }

    public static void setAllClass(ArrayList<AllClass> allClasse) {
        VariableOfClass.allClass = allClasse;
    }
//리뷰스키마찾는거


    public static ArrayList<ChatData> getChatData() {
        return chatData;
    }

    public static void setChatData(ArrayList<ChatData> chatData) {
        VariableOfClass.chatData = chatData;
    }

    public static String getChatObjectID() {
        return ChatObjectID;
    }

    public static void setChatObjectID(String chatObjectID) {
        ChatObjectID = chatObjectID;
    }

    public static String getChatObjectContents() {
        return ChatObjectContents;
    }

    public static void setChatObjectContents(String chatObjectContents) {
        ChatObjectContents = chatObjectContents;
    }

    public static String getChatMyID() {
        return ChatMyID;
    }

    public static void setChatMyID(String chatMyID) {
        ChatMyID = chatMyID;
    }

    public static String getChatMyContents() {
        return ChatMyContents;
    }

    public static void setChatMyContents(String chatMyContents) {
        ChatMyContents = chatMyContents;
    }

    public static String getReviewClassName() {
        return ReviewClassName;
    }

    public static void setReviewClassName(String reviewClassName) {
        ReviewClassName = reviewClassName;
    }

    public static String getReviewuserID() {
        return ReviewuserID;
    }

    public static void setReviewuserID(String reviewUserID) {
        ReviewuserID = reviewUserID;
    }

    public static String getReviewContents() {
        return ReviewContents;
    }

    public static void setReviewContents(String reviewContents) {
        ReviewContents = reviewContents;
    }

    public static String getReviewDate() {
        return ReviewDate;
    }

    public static void setReviewDate(String reviewDate) {
        ReviewDate = reviewDate;
    }

    public static String getReviewScore() {
        return ReviewScore;
    }

    public static void setReviewScore(String reviewScore) {
        ReviewScore = reviewScore;
    }

    private VariableOfClass() {
        variableofclass = new VariableOfClass();
    }


    public static VariableOfClass getVariableofclass() {
        return variableofclass;
    }


    public static VariableOfClass getInstance() {
        return variableofclass;
    }

    public static String getClassIdentity() {
        return ClassIdentity;
    }

    public static void setClassIdentity(String classIdentity) {
        ClassIdentity = classIdentity;
    }

    public static String getClassScore() {
        return ClassScore;
    }

    public static void setClassScore(String classScore) {
        ClassScore = classScore;
    }

    public static String getClassTuteeID() {
        return ClassTuteeID;
    }

    public static void setClassTuteeID(String classTuteeID) {
        ClassTuteeID = classTuteeID;
    }

    public static String getClassPicture() {
        return ClassPicture;
    }

    public static void setClassPicture(String classPicture) {
        ClassPicture = classPicture;
    }

    public static String getClassName() {
        return ClassName;
    }

    public static void setClassName(String className) {
        ClassName = className;
    }

    public static String getClassTutorID() {
        return ClassTutorID;
    }

    public static void setClassTutorID(String classTutorID) {
        ClassTutorID = classTutorID;
    }

    public static String getClassCategory() {
        return ClassCategory;
    }

    public static void setClassCategory(String classCategory) {
        ClassCategory = classCategory;
    }

    public static String getClassTotalPeople() {
        return ClassTotalPeople;
    }

    public static void setClassTotalPeople(String classTotalPeople) {
        ClassTotalPeople = classTotalPeople;
    }

    public static String getClassCurrentPeople() {
        return ClassCurrentPeople;
    }

    public static void setClassCurrentPeople(String classCurrentPeople) {
        ClassCurrentPeople = classCurrentPeople;
    }

    public static String getClassTutorIntro() {
        return ClassTutorIntro;
    }

    public static void setClassTutorIntro(String classTutorIntro) {
        ClassTutorIntro = classTutorIntro;
    }

    public static String getClassIntro() {
        return ClassIntro;
    }

    public static void setClassIntro(String classIntro) {
        ClassIntro = classIntro;
    }

    public static String getClassContents() {
        return ClassContents;
    }

    public static void setClassContents(String classContents) {
        ClassContents = classContents;
    }

    public static String getClassWhom() {
        return ClassWhom;
    }

    public static void setClassWhom(String classWhom) {
        ClassWhom = classWhom;
    }

    public static String getClassPrice() {
        return ClassPrice;
    }

    public static void setClassPrice(String classPrice) {
        ClassPrice = classPrice;
    }

    public static String getClassHour() {
        return ClassHour;
    }

    public static void setClassHour(String classHour) {
        ClassHour = classHour;
    }

    public static String getClassNumberOfTime() {
        return ClassNumberOfTime;
    }

    public static void setClassNumberOfTime(String classNumberOfTime) {
        ClassNumberOfTime = classNumberOfTime;
    }

    public static String getClassPlace() {
        return ClassPlace;
    }

    public static void setClassPlace(String classPlace) {
        ClassPlace = classPlace;
    }

    public static String getClassPlaceDetail() {
        return ClassPlaceDetail;
    }

    public static void setClassPlaceDetail(String classPlaceDetail) {
        ClassPlaceDetail = classPlaceDetail;
    }

    public static String getClassWeek() {
        return ClassWeek;
    }

    public static void setClassWeek(String classWeek) {
        ClassWeek = classWeek;
    }

    public static String getClassTime() {
        return ClassTime;
    }

    public static void setClassTime(String classtime) {
        ClassTime = classtime;
    }

    public static String getClassFirstTime() {
        return ClassFirstTime;
    }

    public static void setClassFirstTime(String classFirstTime) {
        ClassFirstTime = classFirstTime;
    }
}
