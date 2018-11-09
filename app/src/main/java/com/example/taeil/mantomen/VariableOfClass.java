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
    private static String ClassScore = null;  // 강의 평점

    private static ArrayList<AllClass> allClass = null;

    public static ArrayList<AllClass> getAllClass() {
        return allClass;
    }

    public static void setAllClass(ArrayList<AllClass> allClasse) {
        VariableOfClass.allClass = allClasse;
    }
//리뷰스키마찾는거

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
