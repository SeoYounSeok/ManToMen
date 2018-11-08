package com.example.taeil.mantomen;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AllClass { //클래스관련 전역변수모음 특정 클래스 하나를 위한거

    String ClassPicture; // 강의이미지
    String ClassName;  // 강의제목
    String ClassTutorID; // 튜터아이디
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
    String Classtime; // 수업 시간
    String ClassFirstTime; // 첫 수업일


    public AllClass(String classPicture, String className, String classTutorID, String classCategory, String classTotalPeople,
                    String classCurrentPeople, String classTutorIntro, String classIntro, String classContents, String classWhom, String classPrice,
                    String classHour, String classNumberOfTime, String classPlace, String classPlaceDetail, String classWeek, String classtime,
                    String classFirstTime) {
        ClassPicture = classPicture;
        ClassName = className;
        ClassTutorID = classTutorID;
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
        Classtime = classtime;
        ClassFirstTime = classFirstTime;


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

    public String getClasstime() {
        return Classtime;
    }

    public void setClasstime(String classtime) {
        Classtime = classtime;
    }

    public String getClassFirstTime() {
        return ClassFirstTime;
    }

    public void setClassFirstTime(String classFirstTime) {
        ClassFirstTime = classFirstTime;
    }
}
