package com.example.taeil.mantomen;

public class AllReview {  // 리뷰관련 전역변수 모음

    String ReviewClassName;
    String ReviewUserID;
    String ReviewContents;
    String ReviewDate;
    String ReviewScore;

    public AllReview(String reviewClassName, String reviewUserID, String reviewContents, String reviewDate, String reviewScore) {
        ReviewClassName = reviewClassName;
        ReviewUserID = reviewUserID;
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
        return ReviewUserID;
    }

    public void setReviewUserID(String reviewUserID) {
        ReviewUserID = reviewUserID;
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
