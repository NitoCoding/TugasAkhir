package com.example.tugasakhir.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tugasakhir.R;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugasakhir.fragment.BookmarkHadithFragment;
import com.example.tugasakhir.fragment.ChapterFragment;
import com.example.tugasakhir.models.Bookmark;

import java.util.List;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder> {
    private List<Bookmark> bookmarkList;
    private Context context;

    public BookmarkAdapter(Context context, List<Bookmark> bookmarkList) {
        this.context = context;
        this.bookmarkList = bookmarkList;
    }

    @NonNull
    @Override
    public BookmarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookmark_item, parent, false);
        return new BookmarkViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkViewHolder holder, int position) {
        Bookmark bookmark = bookmarkList.get(position);
        holder.bookmarkNumber.setText(String.valueOf(position + 1));
        String bookmarkText = bookmark.getBookName() + " | " + bookmark.getChapterName() + " : " + bookmark.getHadithId();
        holder.bookmarkText.setText(bookmarkText);

        holder.itemView.setOnClickListener(v->{
            Bundle bundle = new Bundle();
            bundle.putString("id", String.valueOf(bookmark.getId()));


            // Mengatur bundle ke HomeFragment
            BookmarkHadithFragment bookmarkHadithFragment = new BookmarkHadithFragment();
            bookmarkHadithFragment.setArguments(bundle);

            // Mengganti fragmen dan menambahkan ke back stack
            FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, bookmarkHadithFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }

    @Override
    public int getItemCount() {
        return bookmarkList.size();
    }

    public static class BookmarkViewHolder extends RecyclerView.ViewHolder {
        public TextView bookmarkNumber, bookmarkText;

        public BookmarkViewHolder(View view) {
            super(view);
            bookmarkNumber = view.findViewById(R.id.bookmark_number);
            bookmarkText = view.findViewById(R.id.bookmark);
        }
    }
}

