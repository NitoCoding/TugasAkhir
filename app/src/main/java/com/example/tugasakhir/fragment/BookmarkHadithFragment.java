package com.example.tugasakhir.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tugasakhir.R;
import com.example.tugasakhir.api.HadithApiClient;
import com.example.tugasakhir.api.HadithApiService;
import com.example.tugasakhir.database.DbHelper;
import com.example.tugasakhir.models.Bookmark;
import com.example.tugasakhir.response.HadithPageResponse;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BookmarkHadithFragment extends Fragment {


    String ID = "";



    public TextView hadithNumber, arabicHadith, englishHadith;
    public ImageView bookmarkBtn;

    DbHelper db;

    HadithApiService api;

    ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Bundle bundle = getArguments();

        if (bundle != null) {
            ID = bundle.getString("id");
            // Do something with the bookSlug
//            Log.d("ChapterFragment", "Received bookSlug: " + bookSlug);
        }
        return inflater.inflate(R.layout.fragment_bookmark_hadist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        AppCompatActivity activity = (AppCompatActivity) getActivity();
//        Toolbar toolbar = view.findViewById(R.id.toolbar);
//        toolbar.setTitle(BOOK_NAME + "|" + CHAPTER_ENGLISH);


        api = HadithApiClient.getClient().create(HadithApiService.class);

        hadithNumber = view.findViewById(R.id.hadithNumber);
        arabicHadith = view.findViewById(R.id.arabicHadith);
        englishHadith = view.findViewById(R.id.englishHadith);
        bookmarkBtn = view.findViewById(R.id.logoButton);

        db = new DbHelper(getContext());








        loadData();
    }

    private void loadData() {
        Bookmark bkm = db.getBookmarkById(Integer.parseInt(ID));

        hadithNumber.setText(bkm.getHadithNumber());
        arabicHadith.setText(bkm.getHadithArabic());
        englishHadith.setText(bkm.getHadithEnglish());
        bookmarkBtn.setImageResource(R.drawable.baseline_bookmark_24_active);

    }
}