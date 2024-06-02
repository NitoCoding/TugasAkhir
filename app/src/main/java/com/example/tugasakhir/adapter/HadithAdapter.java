package com.example.tugasakhir.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tugasakhir.R;

import com.example.tugasakhir.database.DbHelper;
import com.example.tugasakhir.models.Hadith;

import java.util.List;

public class HadithAdapter extends RecyclerView.Adapter<HadithAdapter.HadithViewHolder> {
    private List<Hadith> hadithList;
    private Context context;
    private DbHelper db;


    public HadithAdapter(Context context, List<Hadith> hadithList, DbHelper db) {
        this.context = context;
        this.hadithList = hadithList;
        this.db = db;

    }

    @NonNull
    @Override
    public HadithViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.hadith_item, parent, false);
        return new HadithViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HadithViewHolder holder, int position) {
        Hadith hadith = hadithList.get(position);
        holder.hadithNumber.setText(hadith.getHadithNumber());
        holder.arabicHadith.setText(hadith.getHadithArabic());
        holder.englishHadith.setText(hadith.getHadithEnglish());

        boolean isBookmark = db.checkIfBookmark(hadith.getId());

        if(isBookmark){
            holder.bookmarkBtn.setImageResource(R.drawable.baseline_bookmark_24_active);
        }else {
            holder.bookmarkBtn.setImageResource(R.drawable.baseline_bookmark_border_24_inactive);
        }

        holder.bookmarkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBookmark) {

                    db.deleteBookmark(hadith.getId());
                    holder.bookmarkBtn.setImageResource(R.drawable.baseline_bookmark_border_24_inactive);
                } else {
                    Log.d("test", hadith.getHadithNumber());
                    Log.d("test", String.valueOf(hadith.getId()));
                    db.addBookmark(hadith.getId(),Integer.parseInt(hadith.getHadithNumber()), hadith.getBook().getBookName(), hadith.getHadithArabic(), hadith.getChapter().getChapterEnglish(), hadith.getHadithEnglish());
                    holder.bookmarkBtn.setImageResource(R.drawable.baseline_bookmark_24_active);
                }

                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return hadithList.size();
    }

    public static class HadithViewHolder extends RecyclerView.ViewHolder {
        public TextView hadithNumber, arabicHadith, englishHadith;
        public ImageView bookmarkBtn;

        public HadithViewHolder(View view) {
            super(view);
            hadithNumber = view.findViewById(R.id.hadithNumber);
            arabicHadith = view.findViewById(R.id.arabicHadith);
            englishHadith = view.findViewById(R.id.englishHadith);
            bookmarkBtn = view.findViewById(R.id.logoButton);
        }
    }
}
