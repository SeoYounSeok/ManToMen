package com.example.taeil.mantomen;

import org.json.JSONObject;

import java.util.ArrayList;

public class VariableOfClass { //클래스관련 전역변수모음 특정 클래스 하나를 위한거

    private static VariableOfClass variableofclass;
    private static String ClassName = null;
    private static String ClassTutorID = null;
    private static String ClassTuteeID = null;
    private static String ClassCategory = null;
    private static String ClassTotalPeople = null;
    private static String ClassCurrentPeople = null;
    private static String ClassRPeriod = null;
    private static String ClassOPeriod = null;
    private static String ClassIntro = null;
    private static JSONObject ClassReview = null;
    private static ArrayList<AllClass> allClass;


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


    public static String getClassIntro() {
        return ClassIntro;
    }

    public static void setClassIntro(String classIntro) {
        ClassIntro = classIntro;
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

    public static String getClassTuteeID() {
        return ClassTuteeID;
    }

    public static void setClassTuteeID(String classTuteeID) {
        ClassTuteeID = classTuteeID;
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

    public static String getClassRPeriod() {
        return ClassRPeriod;
    }

    public static void setClassRPeriod(String classRPeriod) {
        ClassRPeriod = classRPeriod;
    }

    public static String getClassOPeriod() {
        return ClassOPeriod;
    }

    public static void setClassOPeriod(String classOPeriod) {
        ClassOPeriod = classOPeriod;
    }



    public static VariableOfClass getInstance(){
        return variableofclass;
    }



}
