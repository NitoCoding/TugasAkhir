package com.example.tugasakhir.models;

public class Bookmark {
    private int id;
    private int hadithId;



    private int hadithNumber;
    private String bookName;



    private String hadithArabic;
    private String hadithEnglish;
    private String chapterName;

    public Bookmark(int id, int hadithId, int hadithNumber,String bookName, String hadithArabic, String chapterName, String hadithEnglish) {
        this.id = id;
        this.hadithId = hadithId;
        this.bookName = bookName;
        this.hadithArabic = hadithArabic;
        this.hadithEnglish = hadithEnglish;
        this.chapterName = chapterName;
        this.hadithNumber = hadithNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHadithId() {
        return hadithId;
    }

    public void setHadithId(int hadithId) {
        this.hadithId = hadithId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }



    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public int getHadithNumber() {
        return hadithNumber;
    }

    public void setHadithNumber(int hadithNumber) {
        this.hadithNumber = hadithNumber;
    }

    public String getHadithArabic() {
        return hadithArabic;
    }

    public void setHadithArabic(String hadithArabic) {
        this.hadithArabic = hadithArabic;
    }

    public String getHadithEnglish() {
        return hadithEnglish;
    }

    public void setHadithEnglish(String hadithEnglish) {
        this.hadithEnglish = hadithEnglish;
    }
}
