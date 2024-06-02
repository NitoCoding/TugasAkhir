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
import com.example.tugasakhir.models.Book;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private final List<Book> bookList;
    private final Context context;

    public BookAdapter(Context context, List<Book> bookList) {
        this.bookList = bookList;
        this.context = context;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.book_item_nomor.setText(String.valueOf(position + 1));
        holder.book_item_nama.setText(book.getBookName());
        holder.book_item_penulis.setText(book.getWriterName());

        holder.itemView.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("book-slug",book.getBookSlug());
            bundle.putString("book-name",book.getBookName());

            // Mengatur bundle ke HomeFragment
            ChapterFragment chapterFragment = new ChapterFragment();
            chapterFragment.setArguments(bundle);

            // Mengganti fragmen dan menambahkan ke back stack
            FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, chapterFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    static class BookViewHolder extends RecyclerView.ViewHolder{
//        LinearLayout book_item;
        TextView book_item_nama,book_item_penulis,book_item_nomor;
        public BookViewHolder(@NonNull View itemView){
            super(itemView);
//            book_item = itemView.findViewById(R.id.book);
            book_item_nomor = itemView.findViewById(R.id.bookNumber);
            book_item_nama = itemView.findViewById(R.id.bookNama);
            book_item_penulis = itemView.findViewById(R.id.bookPenulis);
        }
    }
}
