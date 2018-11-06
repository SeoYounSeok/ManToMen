package com.example.taeil.mantomen;

public class ClassInfo {
    String ClassName;
    String MentorName;
    String Mentor_word;  //멘토의 한마디
    String StudentNum;
    String ClassTerm;
    int ClassScore;
    int ClassPhoto; //사진은 인트형
    int MentorPhoto; //사진은 인트형

    public ClassInfo(String className, String mentorName, String mentor_word, String studentNum, String classTerm, int classScore, int classPhoto, int mentorPhoto) {
        ClassName = className;
        MentorName = mentorName;
        Mentor_word = mentor_word;
        StudentNum = studentNum;
        ClassTerm = classTerm;
        ClassScore = classScore;
        ClassPhoto = classPhoto;
        MentorPhoto = mentorPhoto;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public String getMentorName() {
        return MentorName;
    }

    public void setMentorName(String mentorName) {
        MentorName = mentorName;
    }

    public String getMentor_word() {
        return Mentor_word;
    }

    public void setMentor_word(String mentor_word) {
        Mentor_word = mentor_word;
    }

    public String getStudentNum() {
        return StudentNum;
    }

    public void setStudentNum(String studentNum) {
        StudentNum = studentNum;
    }

    public String getClassTerm() {
        return ClassTerm;
    }

    public void setClassTerm(String classTerm) {
        ClassTerm = classTerm;
    }

    public int getClassScore() {
        return ClassScore;
    }

    public void setClassScore(int classScore) {
        ClassScore = classScore;
    }

    public int getClassPhoto() {
        return ClassPhoto;
    }

    public void setClassPhoto(int classPhoto) {
        ClassPhoto = classPhoto;
    }

    public int getMentorPhoto() {
        return MentorPhoto;
    }

    public void setMentorPhoto(int mentorPhoto) {
        MentorPhoto = mentorPhoto;
    }

    //강의 정보에는 정말 많은 정보가 들어가지만 추후 추가할 예정
}
