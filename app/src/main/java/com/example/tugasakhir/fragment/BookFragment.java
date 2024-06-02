package com.example.tugasakhir.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.tugasakhir.R;
import com.example.tugasakhir.adapter.BookAdapter;
import com.example.tugasakhir.api.HadithApiClient;
import com.example.tugasakhir.api.HadithApiService;
import com.example.tugasakhir.models.Book;
import com.example.tugasakhir.response.BookResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookFragment extends Fragment {
    String API_KEY = "$2y$10$xl4v0F7dFrd1uMKeEXtrsrrO19SpO1va4F8WB2tDJ5HBtBTZrHu";

    HadithApiService api;
    RecyclerView rv;
    BookAdapter adapter;
    List<Book> books = new ArrayList<>();
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        rv = view.findViewById(R.id.rv_book);
        api = HadithApiClient.getClient().create(HadithApiService.class);

        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new BookAdapter(getContext(), books);
        rv.setAdapter(adapter);
//
//        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottom_nav);
//        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
//            Fragment selectedFragment = null;
//            int id = item.getItemId();
//            if (id == R.id.nav_home) {
//                selectedFragment = new BookFragment();
//            } else if (id == R.id.nav_mark) {
//                selectedFragment = new MarkFragment();
//            }
//
//            if (selectedFragment != null) {
//                FragmentManager fragmentManager = getParentFragmentManager();
//                fragmentManager.beginTransaction()
//                        .replace(R.id.frame_container, selectedFragment)
//                        .addToBackStack(null)
//                        .commit();
//            }
//            return true;
//        });



        loadData();
    }

    private void loadData() {
        executorService.execute(() -> {
            Call<BookResponse> call = api.getBooks(API_KEY);
            call.enqueue(new Callback<BookResponse>() {
                @Override
                public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        books.clear();
                        books.addAll(response.body().getBooks());
//                         getActivity().runOnUiThread(() -> adapter.notifyDataSetChanged());
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<BookResponse> call, Throwable t) {
                    // Handle API call failure
                }
            });
        });
    }
}
