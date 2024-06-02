package com.example.tugasakhir.models;

public class Chapter {
    private int id;
    private String chapterNumber;
    private String chapterEnglish;
    private String chapterUrdu;
    private String chapterArabic;
    private String bookSlug;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(String chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public String getChapterEnglish() {
        return chapterEnglish;
    }

    public void setChapterEnglish(String chapterEnglish) {
        this.chapterEnglish = chapterEnglish;
    }

    public String getChapterUrdu() {
        return chapterUrdu;
    }

    public void setChapterUrdu(String chapterUrdu) {
        this.chapterUrdu = chapterUrdu;
    }

    public String getChapterArabic() {
        return chapterArabic;
    }

    public void setChapterArabic(String chapterArabic) {
        this.chapterArabic = chapterArabic;
    }

    public String getBookSlug() {
        return bookSlug;
    }

    public void setBookSlug(String bookSlug) {
        this.bookSlug = bookSlug;
    }
}
