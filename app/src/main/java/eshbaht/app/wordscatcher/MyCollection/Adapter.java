package eshbaht.app.wordscatcher.MyCollection;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import eshbaht.app.wordscatcher.DataBase.WordCollect.CollectWords;
import eshbaht.app.wordscatcher.databinding.ItemBinding;

public class Adapter extends RecyclerView.Adapter<Adapter.CollectWordsVH> {

    private final LayoutInflater inflater;

    private final List<CollectWords> collectwords = new ArrayList<>();

    public Adapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CollectWordsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBinding binding = ItemBinding.inflate(inflater, parent, false);


        return new CollectWordsVH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectWordsVH holder, int position) {
        holder.onBind(collectwords.get(position));
    }



    @Override
    public int getItemCount() {
        return collectwords.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setCollectWords(List<CollectWords> collectwords) {
        this.collectwords.clear();
        this.collectwords.addAll(collectwords);
        notifyDataSetChanged();
    }

    class CollectWordsVH extends RecyclerView.ViewHolder{

        private CollectWords collectw;
        private ItemBinding views;


        public CollectWordsVH(ItemBinding views) {
            super(views.getRoot());
            this.views = views;
        }


        public void onBind(CollectWords collectwords) {
            this.collectw = collectwords;
            views.collectWord.setText(collectwords.WORDCOLLECT);
        }
    }

}