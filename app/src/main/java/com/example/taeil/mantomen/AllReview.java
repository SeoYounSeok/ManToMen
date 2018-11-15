package com.example.taeil.mantomen;

public class AllReview {  // 리뷰관련 전역변수 모음

    String ReviewClassName;
    String ReviewuserID;
    String ReviewContents;
    String ReviewDate;
    String ReviewScore;

    public AllReview(String reviewClassName, String reviewuserID, String reviewContents, String reviewDate, String reviewScore) {
        ReviewClassName = reviewClassName;
        ReviewuserID = reviewuserID;
        ReviewContents = reviewContents;
        ReviewDate = reviewDate;
        ReviewScore = reviewScore;
    }

    public String getReviewClassName() {
        return ReviewClassName;
    }

    public void setReviewClassName(String reviewClassName) {
        ReviewClassName = reviewClassName;
    }

    public String getReviewUserID() {
        return ReviewuserID;
    }

    public void setReviewUserID(String reviewUserID) {
        ReviewuserID = reviewUserID;
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

    public String getReviewScore() {
        return ReviewScore;
    }

    public void setReviewScore(String reviewScore) {
        ReviewScore = reviewScore;
    }
}
