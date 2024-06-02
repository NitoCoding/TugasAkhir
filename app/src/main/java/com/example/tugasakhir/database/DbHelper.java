package com.example.tugasakhir.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tugasakhir.models.Bookmark;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "hadith_bookmark.db";
    private static final int DATABASE_VERSION = 1;

    // Nama tabel dan kolom
    public static final String TABLE_BOOKMARK = "bookmark";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_HADITH_ID = "hadith_id";
    public static final String COLUMN_HADITH_NUMBER = "hadith_number";
    public static final String COLUMN_BOOK_NAME = "book_name";
    public static final String COLUMN_CHAPTER_ENGLISH = "chapter_number";
    public static final String COLUMN_HADITH_ARABIC = "hadith_arabic";
    public static final String COLUMN_HADITH_ENGLISH = "hadith_english";

    // SQL statement untuk membuat tabel bookmark
    private static final String CREATE_TABLE_BOOKMARK = "CREATE TABLE " +
            TABLE_BOOKMARK + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_HADITH_ID + " INTEGER," +
            COLUMN_HADITH_NUMBER + " INTEGER," +
            COLUMN_BOOK_NAME + " TEXT," +
            COLUMN_CHAPTER_ENGLISH + " TEXT," +
            COLUMN_HADITH_ENGLISH + " TEXT," +
            COLUMN_HADITH_ARABIC+ " TEXT" +
            ")";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Membuat tabel bookmark
        db.execSQL(CREATE_TABLE_BOOKMARK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Menghapus tabel lama jika ada, dan membuat tabel baru
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKMARK);
        onCreate(db);
    }

    // Metode untuk menambahkan data bookmark baru
    public long addBookmark(int hadithId,int hadithNumber, String bookName, String hadithArabic, String chapterEnglish, String hadithEnglish) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_HADITH_ID, hadithId);
        values.put(COLUMN_HADITH_NUMBER, hadithNumber);
        values.put(COLUMN_BOOK_NAME, bookName);
        values.put(COLUMN_HADITH_ARABIC, hadithArabic);
        values.put(COLUMN_CHAPTER_ENGLISH, chapterEnglish);
        values.put(COLUMN_HADITH_ENGLISH, hadithEnglish);
        long id = db.insert(TABLE_BOOKMARK, null, values);
        db.close();
        return id;
    }

    // Metode untuk menghapus data bookmark berdasarkan ID hadith
    public void deleteBookmark(int hadithId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BOOKMARK, COLUMN_HADITH_ID + " = ?", new String[]{String.valueOf(hadithId)});
        db.close();
    }
    public List<Bookmark> getAllBookmarks() {
        List<Bookmark> bookmarks = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_BOOKMARK;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                int hadithId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_HADITH_ID));
                int hadithNumber = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_HADITH_NUMBER));
                String bookName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BOOK_NAME));
                String bookSlug = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_HADITH_ARABIC));
                String chapterNumber = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CHAPTER_ENGLISH));
                String chapterName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_HADITH_ENGLISH));
                Bookmark bookmark = new Bookmark(id,hadithNumber, hadithId, bookName, bookSlug, chapterNumber, chapterName);
                bookmarks.add(bookmark);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return bookmarks;
    }

    public Bookmark getBookmarkById(int hadithId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_HADITH_ID,COLUMN_HADITH_NUMBER, COLUMN_BOOK_NAME, COLUMN_HADITH_ARABIC, COLUMN_CHAPTER_ENGLISH, COLUMN_HADITH_ENGLISH};
        String selection = COLUMN_HADITH_ID + " = ?";
        String[] selectionArgs = {String.valueOf(hadithId)};
        Cursor cursor = db.query(TABLE_BOOKMARK, columns, selection, selectionArgs, null, null, null);
        Bookmark bookmark = null;
        if (cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
            int bookmarkHadithId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_HADITH_ID));
            int bookmarkHadithNumber = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_HADITH_NUMBER));
            String bookName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BOOK_NAME));
            String bookSlug = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_HADITH_ARABIC));
            String chapterNumber = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CHAPTER_ENGLISH));
            String chapterName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_HADITH_ENGLISH));
            bookmark = new Bookmark(id, bookmarkHadithId,bookmarkHadithNumber, bookName, bookSlug, chapterNumber, chapterName);
        }
        cursor.close();
        db.close();
        return bookmark;
    }

    public boolean checkIfBookmark(int hadithId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_HADITH_ID};
        String selection = COLUMN_HADITH_ID + " = ?";
        String[] selectionArgs = {String.valueOf(hadithId)};
        Cursor cursor = db.query(TABLE_BOOKMARK, columns, selection, selectionArgs, null, null, null);
        boolean isBookmarked = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return isBookmarked;
    }




}