package com.example.tugasakhir.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tugasakhir.R;
import com.example.tugasakhir.adapter.BookAdapter;
import com.example.tugasakhir.adapter.ChapterAdapter;
import com.example.tugasakhir.api.HadithApiClient;
import com.example.tugasakhir.api.HadithApiService;
import com.example.tugasakhir.models.Book;
import com.example.tugasakhir.models.Chapter;
import com.example.tugasakhir.response.BookResponse;
import com.example.tugasakhir.response.ChapterResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChapterFragment extends Fragment {

    String API_KEY = "$2y$10$xl4v0F7dFrd1uMKeEXtrsrrO19SpO1va4F8WB2tDJ5HBtBTZrHu";
    String BOOK_SLUG = "bookSlug";
    String BOOK_NAME = "bookName";

    HadithApiService api;
    RecyclerView rv;
    ChapterAdapter adapter;
    List<Chapter> chapters = new ArrayList<>();

    TextView book_name;
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Bundle bundle = getArguments();

        if (bundle != null) {
            BOOK_SLUG = bundle.getString("book-slug");
            BOOK_NAME = bundle.getString("book-name");
            // Do something with the bookSlug
//            Log.d("ChapterFragment", "Received bookSlug: " + bookSlug);
        }
        return inflater.inflate(R.layout.fragment_chapter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        AppCompatActivity activity = (AppCompatActivity) getActivity();
//        Toolbar toolbar = view.findViewById(R.id.toolbar);
//        toolbar.setTitle("");

        book_name = view.findViewById(R.id.bookNama);
        book_name.setText(BOOK_NAME);
        rv = view.findViewById(R.id.rv_chapter);
        api = HadithApiClient.getClient().create(HadithApiService.class);

        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ChapterAdapter(getContext(), chapters, BOOK_NAME, BOOK_SLUG);
        rv.setAdapter(adapter);




        loadData();
    }

    private void loadData() {
        executorService.execute(() -> {
            Call<ChapterResponse> call = api.getChapters(BOOK_SLUG,API_KEY);
            call.enqueue(new Callback<ChapterResponse>() {
                @Override
                public void onResponse(Call<ChapterResponse> call, Response<ChapterResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        chapters.clear();
                        chapters.addAll(response.body().getChapters());
                        getActivity().runOnUiThread(() -> adapter.notifyDataSetChanged());
                    }
                }

                @Override
                public void onFailure(Call<ChapterResponse> call, Throwable t) {
                    // Handle API call failure
                }
            });
        });
    }
}