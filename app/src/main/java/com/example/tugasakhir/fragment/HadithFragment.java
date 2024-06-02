package com.example.tugasakhir.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.tugasakhir.R;
import com.example.tugasakhir.adapter.BookAdapter;
import com.example.tugasakhir.adapter.ChapterAdapter;
import com.example.tugasakhir.adapter.HadithAdapter;
import com.example.tugasakhir.api.HadithApiClient;
import com.example.tugasakhir.api.HadithApiService;
import com.example.tugasakhir.database.DbHelper;
import com.example.tugasakhir.models.Book;
import com.example.tugasakhir.models.Chapter;
import com.example.tugasakhir.models.Hadith;
import com.example.tugasakhir.response.BookResponse;
import com.example.tugasakhir.response.HadithPageResponse;
import com.example.tugasakhir.response.HadithResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HadithFragment extends Fragment {

    String API_KEY = "$2y$10$xl4v0F7dFrd1uMKeEXtrsrrO19SpO1va4F8WB2tDJ5HBtBTZrHu";
    String BOOK_SLUG = "bookSlug";
    String BOOK_NAME = "bookName";
    String CHAPTER_ENGLISH = "chapterEnglish";
    String CHAPTER_NUMBER = "chapterArabic";
    int PAGINATE = 10;
    int PAGE = 1;
    boolean hasNext = false;

    HadithApiService api;
    RecyclerView rv;
    HadithAdapter adapter;
    List<Hadith> hadiths = new ArrayList<>();
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    DbHelper db ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Bundle bundle = getArguments();

        if (bundle != null) {
            BOOK_SLUG = bundle.getString("book-slug");
            BOOK_NAME = bundle.getString("book-name");
            CHAPTER_ENGLISH = bundle.getString("chapter-english");
            CHAPTER_NUMBER = bundle.getString("chapter-number");
            // Do something with the bookSlug
//            Log.d("ChapterFragment", "Received bookSlug: " + bookSlug);
        }
        return inflater.inflate(R.layout.fragment_hadith, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        AppCompatActivity activity = (AppCompatActivity) getActivity();
//        Toolbar toolbar = view.findViewById(R.id.toolbar);
//        toolbar.setTitle(BOOK_NAME + "|" + CHAPTER_ENGLISH);

        rv = view.findViewById(R.id.rv_hadith);
        api = HadithApiClient.getClient().create(HadithApiService.class);

        db = new DbHelper(getContext());

        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new HadithAdapter(getContext(), hadiths,db);
        rv.setAdapter(adapter);




        loadData();
    }

    private void loadData() {
        executorService.execute(() -> {
            Call<HadithPageResponse> call = api.getHadiths(API_KEY,BOOK_SLUG,CHAPTER_NUMBER,PAGINATE,PAGE);
            call.enqueue(new Callback<HadithPageResponse>() {
                @Override
                public void onResponse(Call<HadithPageResponse> call, Response<HadithPageResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        hadiths.clear();
                        hadiths.addAll(response.body().getHadiths().getData());
                        for (Hadith hadith : hadiths) {
                            Log.d("Hadith", "Id: " + hadith.getId());
                            Log.d("Hadith", "num: " + hadith.getHadithNumber());
                        }

                        getActivity().runOnUiThread(() -> adapter.notifyDataSetChanged());
                    }
                }

                @Override
                public void onFailure(Call<HadithPageResponse> call, Throwable t) {
                    // Handle API call failure
                }
            });
        });
    }
}