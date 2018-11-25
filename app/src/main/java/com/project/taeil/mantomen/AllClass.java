package com.project.taeil.mantomen;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AllClass { //클래스관련 전역변수모음 특정 클래스 하나를 위한거

    String ClassPicture; // 강의이미지
    String ClassName;  // 강의제목
    String ClassTutorID; // 튜터아이디
    String ClassTuteeID; // 튜티아이디
    String ClassCategory; //카테고리
    String ClassTotalPeople;  // 모집인원
    String ClassCurrentPeople; // 현재인원
    String ClassTutorIntro; // 튜터소개
    String ClassIntro; // 강의소개
    String ClassContents; // 수업내용(커리큘럼)
    String ClassWhom; // 수업대상
    String ClassPrice; // 수강료
    String ClassHour; // 1회에 몇시간씩
    String ClassNumberOfTime; // 몇 회
    String ClassPlace; // 수업 장소
    String ClassPlaceDetail; //상세위치
    String ClassWeek; // 수업 요일
    String ClassTime; // 수업 시간
    String ClassFirstTime; // 첫 수업일
    String ClassIdentity; // 승인된클래스인지 아닌지
    String ClassScore; // 클래스 점수


    public AllClass(String classPicture, String className, String classTutorID, String classTuteeID, String classCategory, String classTotalPeople,
                    String classCurrentPeople, String classTutorIntro,  String classIntro, String classContents, String classWhom, String classPrice,
                    String classHour, String classNumberOfTime, String classPlace, String classPlaceDetail, String classWeek, String classTime,
                    String classFirstTime, String classIdentity, String classScore) {
        ClassPicture = classPicture;
        ClassName = className;
        ClassTutorID = classTutorID;
        ClassTuteeID  = classTuteeID;
        ClassCategory = classCategory;
        ClassTotalPeople = classTotalPeople;
        ClassCurrentPeople = classCurrentPeople;
        ClassTutorIntro = classTutorIntro;
        ClassIntro = classIntro;
        ClassContents = classContents;
        ClassWhom = classWhom;
        ClassPrice = classPrice;
        ClassHour = classHour;
        ClassNumberOfTime = classNumberOfTime;
        ClassPlace = classPlace;
        ClassPlaceDetail = classPlaceDetail;
        ClassWeek = classWeek;
        ClassTime = classTime;
        ClassFirstTime = classFirstTime;
        ClassIdentity = classIdentity;
        ClassScore = classScore;


    }

    public String getClassScore() {
        return ClassScore;
    }

    public void setClassScore(String classScore) {
        ClassScore = classScore;
    }

    public String getClassTuteeID() {
        return ClassTuteeID;
    }

    public void setClassTuteeID(String classTuteeID) {
        ClassTuteeID = classTuteeID;
    }

    public String getClassIdentity() {
        return ClassIdentity;
    }

    public void setClassIdentity(String classIdentity) {
        ClassIdentity = classIdentity;
    }

    public String getClassPicture() {
        return ClassPicture;
    }

    public void setClassPicture(String classPicture) {
        ClassPicture = classPicture;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public String getClassTutorID() {
        return ClassTutorID;
    }

    public void setClassTutorID(String classTutorID) {
        ClassTutorID = classTutorID;
    }

    public String getClassCategory() {
        return ClassCategory;
    }

    public void setClassCategory(String classCategory) {
        ClassCategory = classCategory;
    }

    public String getClassTotalPeople() {
        return ClassTotalPeople;
    }

    public void setClassTotalPeople(String classTotalPeople) {
        ClassTotalPeople = classTotalPeople;
    }

    public String getClassCurrentPeople() {
        return ClassCurrentPeople;
    }

    public void setClassCurrentPeople(String classCurrentPeople) {
        ClassCurrentPeople = classCurrentPeople;
    }

    public String getClassTutorIntro() {
        return ClassTutorIntro;
    }

    public void setClassTutorIntro(String classTutorIntro) {
        ClassTutorIntro = classTutorIntro;
    }

    public String getClassIntro() {
        return ClassIntro;
    }

    public void setClassIntro(String classIntro) {
        ClassIntro = classIntro;
    }

    public String getClassContents() {
        return ClassContents;
    }

    public void setClassContents(String classContents) {
        ClassContents = classContents;
    }

    public String getClassWhom() {
        return ClassWhom;
    }

    public void setClassWhom(String classWhom) {
        ClassWhom = classWhom;
    }

    public String getClassPrice() {
        return ClassPrice;
    }

    public void setClassPrice(String classPrice) {
        ClassPrice = classPrice;
    }

    public String getClassHour() {
        return ClassHour;
    }

    public void setClassHour(String classHour) {
        ClassHour = classHour;
    }

    public String getClassNumberOfTime() {
        return ClassNumberOfTime;
    }

    public void setClassNumberOfTime(String classNumberOfTime) {
        ClassNumberOfTime = classNumberOfTime;
    }

    public String getClassPlace() {
        return ClassPlace;
    }

    public void setClassPlace(String classPlace) {
        ClassPlace = classPlace;
    }

    public String getClassPlaceDetail() {
        return ClassPlaceDetail;
    }

    public void setClassPlaceDetail(String classPlaceDetail) {
        ClassPlaceDetail = classPlaceDetail;
    }

    public String getClassWeek() {
        return ClassWeek;
    }

    public void setClassWeek(String classWeek) {
        ClassWeek = classWeek;
    }

    public String getClassTime() {
        return ClassTime;
    }

    public void setClassTime(String classtime) {
        ClassTime = classtime;
    }

    public String getClassFirstTime() {
        return ClassFirstTime;
    }

    public void setClassFirstTime(String classFirstTime) {
        ClassFirstTime = classFirstTime;
    }
}
