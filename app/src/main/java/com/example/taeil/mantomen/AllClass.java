package com.example.taeil.mantomen;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AllClass { //클래스관련 전역변수모음 특정 클래스 하나를 위한거

    String ClassName;
    String ClassTutorID;
    String ClassTuteeID;
    String ClassCategory;
    String ClassTotalPeople;
    String ClassCurrentPeople;
    String ClassRPeriod;
    String ClassOPeriod;
    String ClassIntro;

    //   String ClassReview;


    JSONObject ClassReview; //리뷰잠깐 빼 오브젝트니까
    String ReviewuserID;
    String ReviewContents;
    String ReviewDate;

    public String getReviewuserID() {
        return ReviewuserID;
    }

    public void setReviewuserID(String reviewuserID) {
        ReviewuserID = reviewuserID;
    }

    public String getReviewContents() {
        return ReviewContents;
    }

    public void setReviewContents(String reviewContents) {
        ReviewContents = reviewContents;
    }

    public String getReviewDate() {
        return ReviewDate;
    }

    public void setReviewDate(String reviewDate) {
        ReviewDate = reviewDate;
    }

    public void setClassReview(JSONObject classReview) {
        ClassReview = classReview;
    }

    public ArrayList<AllClass> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<AllClass> arrayList) {
        this.arrayList = arrayList;
    }

    ArrayList<AllClass> arrayList; //어레이리스트만듦


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

    public String getClassTuteeID() {
        return ClassTuteeID;
    }

    public void setClassTuteeID(String classTuteeID) {
        ClassTuteeID = classTuteeID;
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

    public String getClassRPeriod() {
        return ClassRPeriod;
    }

    public void setClassRPeriod(String classRPeriod) {
        ClassRPeriod = classRPeriod;
    }

    public String getClassOPeriod() {
        return ClassOPeriod;
    }

    public void setClassOPeriod(String classOPeriod) {
        ClassOPeriod = classOPeriod;
    }

    public String getClassIntro() {
        return ClassIntro;
    }

    public void setClassIntro(String classIntro) {
        ClassIntro = classIntro;
    }

    public JSONObject getClassReview() {
        return ClassReview;
    }

    public void JSONObject(JSONObject classReview) {
        ClassReview = classReview;
    }

    public AllClass(String className, String classTutorID, String classTuteeID, String classCategory,
                    String classTotalPeople, String classCurrentPeople, String classRPeriod, String classOPeriod, String classIntro, JSONObject classReview) {
        ClassName = className;
        ClassTutorID = classTutorID;
        ClassTuteeID = classTuteeID;
        ClassCategory = classCategory;
        ClassTotalPeople = classTotalPeople;
        ClassCurrentPeople = classCurrentPeople;
        ClassRPeriod = classRPeriod;
        ClassOPeriod = classOPeriod;
        ClassIntro = classIntro;
        ClassReview = classReview;
        try {
            ReviewuserID = ClassReview.getString("userID");
            ReviewContents = ClassReview.getString("Contents");
            ReviewDate = ClassReview.getString("Date");

        } catch (JSONException e) {
            e.printStackTrace();
        }

//        ClassReview = classReview;


    }


}
