package com.example.tugasakhir.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugasakhir.R;
import com.example.tugasakhir.fragment.ChapterFragment;
import com.example.tugasakhir.fragment.HadithFragment;
import com.example.tugasakhir.models.Chapter;

import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder> {

    private final List<Chapter> ChapterList;
    private final Context context;
    private String bookName, bookSlug;

    public ChapterAdapter(Context context, List<Chapter> ChapterList, String bookName, String bookSlug) {
        this.ChapterList = ChapterList;
        this.context = context;
        this.bookName = bookName;
        this.bookSlug = bookSlug;
    }

    @NonNull
    @Override
    public ChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chapter_item, parent, false);
        return new ChapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterViewHolder holder, int position) {
        Chapter chapter = ChapterList.get(position);

        holder.chapter_item_number.setText(chapter.getChapterNumber());
        holder.chapter_item_english.setText(chapter.getChapterEnglish());


        holder.itemView.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("chapter-number",chapter.getChapterNumber());
            bundle.putString("chapter-english",chapter.getChapterEnglish());
            bundle.putString("book-name",bookName);
            bundle.putString("book-slug",bookSlug);


            // Mengatur bundle ke HomeFragment
            HadithFragment hadithFragment = new HadithFragment();
            hadithFragment.setArguments(bundle);

            // Mengganti fragmen dan menambahkan ke back stack
            FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, hadithFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }

    @Override
    public int getItemCount() {
        return ChapterList.size();
    }

    static class ChapterViewHolder extends RecyclerView.ViewHolder{
//        LinearLayout Chapter_item;
        TextView chapter_item_number,chapter_item_english,chapter_item_arabic;
        public ChapterViewHolder(@NonNull View itemView){
            super(itemView);
//            Chapter_item = itemView.findViewById(R.id.Chapter);
            chapter_item_number = itemView.findViewById(R.id.chapterNumber);
            chapter_item_english = itemView.findViewById(R.id.chapterEnglish);

        }
    }
}
