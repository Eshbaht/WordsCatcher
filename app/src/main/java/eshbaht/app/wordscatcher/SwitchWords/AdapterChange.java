package eshbaht.app.wordscatcher.SwitchWords;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eshbaht.app.wordscatcher.DataBase.MaxLenghtWord.WordMax;
import eshbaht.app.wordscatcher.MyCollection.Adapter;
import eshbaht.app.wordscatcher.databinding.ItemchangeBinding;

public class AdapterChange extends RecyclerView.Adapter<AdapterChange.WordMaxVH> {

    int selectedPosition=-1;
    private final LayoutInflater inflater;
    private final OnItemClickListener onLeftClickListener;

    private final List<WordMax> wordmax = new ArrayList<>();

    public AdapterChange(Context context, OnItemClickListener onLeftClickListener) {
        this.onLeftClickListener = onLeftClickListener;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public AdapterChange.WordMaxVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemchangeBinding binding = ItemchangeBinding.inflate(inflater, parent, false);

        return new AdapterChange.WordMaxVH(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull AdapterChange.WordMaxVH holder, int position) {
        holder.onBind(wordmax.get(position));
        if(selectedPosition==position)
            holder.itemView.setBackgroundColor(Color.parseColor("#000000"));
        else
            holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
    }



    @Override
    public int getItemCount() {
        return wordmax.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setMaxWords(List<WordMax> wordmax) {
        this.wordmax.clear();
        this.wordmax.addAll(wordmax);
        notifyDataSetChanged();
    }

    class WordMaxVH extends RecyclerView.ViewHolder{

        private WordMax wordmaxw;
        private ItemchangeBinding views;


        public WordMaxVH(ItemchangeBinding views) {
            super(views.getRoot());
            this.views = views;

            views.changesWord.setOnClickListener(view->{
                if(wordmaxw!=null) {
                    onLeftClickListener.onClickItem(getAdapterPosition(), wordmaxw);
                    selectedPosition=getAdapterPosition();
                    notifyDataSetChanged();
                }
            });

        }


        public void onBind(WordMax wordmax) {
            this.wordmaxw = wordmax;
            views.changesWord.setText(wordmax.MAXWORD);
        }
    }
    interface OnItemClickListener{

        void onClickItem(int position, WordMax wordmax);

    }

}