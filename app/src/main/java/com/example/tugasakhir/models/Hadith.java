package com.example.tugasakhir.models;

public class Hadith {
    private int id;
    private String hadithNumber;
    private String englishNarrator;
    private String hadithEnglish;
    private String hadithUrdu;
    private String hadithArabic;
    private String urduNarrator;
    private String headingArabic;
    private String headingUrdu;
    private String headingEnglish;
    private String chapterId;
    private String bookSlug;
    private String volume;
    private String status;
    private Book book;
    private Chapter chapter;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHadithNumber() {
        return hadithNumber;
    }

    public void setHadithNumber(String hadithNumber) {
        this.hadithNumber = hadithNumber;
    }

    public String getEnglishNarrator() {
        return englishNarrator;
    }

    public void setEnglishNarrator(String englishNarrator) {
        this.englishNarrator = englishNarrator;
    }

    public String getHadithEnglish() {
        return hadithEnglish;
    }

    public void setHadithEnglish(String hadithEnglish) {
        this.hadithEnglish = hadithEnglish;
    }

    public String getHadithUrdu() {
        return hadithUrdu;
    }

    public void setHadithUrdu(String hadithUrdu) {
        this.hadithUrdu = hadithUrdu;
    }

    public String getHadithArabic() {
        return hadithArabic;
    }

    public void setHadithArabic(String hadithArabic) {
        this.hadithArabic = hadithArabic;
    }

    public String getUrduNarrator() {
        return urduNarrator;
    }

    public void setUrduNarrator(String urduNarrator) {
        this.urduNarrator = urduNarrator;
    }

    public String getHeadingArabic() {
        return headingArabic;
    }

    public void setHeadingArabic(String headingArabic) {
        this.headingArabic = headingArabic;
    }

    public String getHeadingUrdu() {
        return headingUrdu;
    }

    public void setHeadingUrdu(String headingUrdu) {
        this.headingUrdu = headingUrdu;
    }

    public String getHeadingEnglish() {
        return headingEnglish;
    }

    public void setHeadingEnglish(String headingEnglish) {
        this.headingEnglish = headingEnglish;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getBookSlug() {
        return bookSlug;
    }

    public void setBookSlug(String bookSlug) {
        this.bookSlug = bookSlug;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }
}
