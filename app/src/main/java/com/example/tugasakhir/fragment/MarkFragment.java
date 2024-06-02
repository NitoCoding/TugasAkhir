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
import com.example.tugasakhir.adapter.BookmarkAdapter;
import com.example.tugasakhir.adapter.ChapterAdapter;
import com.example.tugasakhir.api.HadithApiClient;
import com.example.tugasakhir.api.HadithApiService;
import com.example.tugasakhir.database.DbHelper;
import com.example.tugasakhir.models.Book;
import com.example.tugasakhir.models.Bookmark;
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


public class MarkFragment extends Fragment {



    RecyclerView rv;
    BookmarkAdapter adapter;
    List<Bookmark> bookmarks = new ArrayList<>();
    DbHelper db;

    ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chapter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        AppCompatActivity activity = (AppCompatActivity) getActivity();
//        Toolbar toolbar = view.findViewById(R.id.toolbar);
//        toolbar.setTitle("");


        rv = view.findViewById(R.id.rv_chapter);
        db = new DbHelper(getContext());

        adapter = new BookmarkAdapter(getContext(), bookmarks);

        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(adapter);




        loadData();
    }

    private void loadData() {

        executorService.execute(() -> {
            bookmarks.clear();
            bookmarks.addAll(db.getAllBookmarks());

            // Memperbarui adapter di thread UI
            getActivity().runOnUiThread(() -> adapter.notifyDataSetChanged());
        });
    }
}