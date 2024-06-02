package com.example.tugasakhir.models;

public class Book {
    private int id;
    private String bookName;
    private String writerName;
    private String aboutWriter;
    private String writerDeath;
    private String bookSlug;
    private String hadithsCount;
    private String chaptersCount;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public String getAboutWriter() {
        return aboutWriter;
    }

    public void setAboutWriter(String aboutWriter) {
        this.aboutWriter = aboutWriter;
    }

    public String getWriterDeath() {
        return writerDeath;
    }

    public void setWriterDeath(String writerDeath) {
        this.writerDeath = writerDeath;
    }

    public String getBookSlug() {
        return bookSlug;
    }

    public void setBookSlug(String bookSlug) {
        this.bookSlug = bookSlug;
    }

    public String getHadithsCount() {
        return hadithsCount;
    }

    public void setHadithsCount(String hadithsCount) {
        this.hadithsCount = hadithsCount;
    }

    public String getChaptersCount() {
        return chaptersCount;
    }

    public void setChaptersCount(String chaptersCount) {
        this.chaptersCount = chaptersCount;
    }
}
