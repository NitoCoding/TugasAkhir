package com.example.tugasakhir.api;

import com.example.tugasakhir.response.BookResponse;
import com.example.tugasakhir.response.ChapterResponse;
import com.example.tugasakhir.response.HadithPageResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
public interface HadithApiService {

    @GET("api/books")
    Call<BookResponse> getBooks(@Query("apiKey") String apiKey);

    @GET("api/{book}/chapters")
    Call<ChapterResponse> getChapters(
            @Path("book") String book,
//            @Query("page") int page,
            @Query("apiKey") String apiKey);

    @GET("api/hadiths")
    Call<HadithPageResponse> getHadiths(
            @Query("apiKey") String apiKey,
            @Query("book") String book,
            @Query("chapter") String chapter,
            @Query("paginate") int paginate,
            @Query("page") int page);
}
