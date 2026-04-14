package com.sit305.myapplication.ui.sports;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sit305.myapplication.R;
import com.sit305.myapplication.data.BookmarkStorage;
import com.sit305.myapplication.data.SportsItem;

import java.util.ArrayList;
import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder> {
    public interface Listener {
        void onItemClicked(SportsItem item);

        void onBookmarkToggled(SportsItem item);
    }

    private final List<SportsItem> items = new ArrayList<>();
    private final Listener listener;

    public StoryAdapter(Listener listener) {
        this.listener = listener;
    }

    public void submitList(List<SportsItem> newItems) {
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_story, parent, false);
        return new StoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryViewHolder holder, int position) {
        SportsItem item = items.get(position);
        Context context = holder.itemView.getContext();
        holder.titleText.setText(item.getTitle());
        holder.categoryText.setText(item.getCategory());
        holder.metaText.setText(item.getMeta());
        holder.summaryText.setText(item.getSummary());
        holder.imageView.setImageResource(item.getImageResId());
        holder.bookmarkButton.setImageResource(BookmarkStorage.isBookmarked(context, item.getId())
                ? android.R.drawable.btn_star_big_on
                : android.R.drawable.btn_star_big_off);
        holder.itemView.setOnClickListener(v -> listener.onItemClicked(item));
        holder.bookmarkButton.setOnClickListener(v -> listener.onBookmarkToggled(item));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class StoryViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView titleText;
        private final TextView categoryText;
        private final TextView metaText;
        private final TextView summaryText;
        private final ImageButton bookmarkButton;

        public StoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.storyImage);
            titleText = itemView.findViewById(R.id.storyTitle);
            categoryText = itemView.findViewById(R.id.storyCategory);
            metaText = itemView.findViewById(R.id.storyMeta);
            summaryText = itemView.findViewById(R.id.storySummary);
            bookmarkButton = itemView.findViewById(R.id.storyBookmarkButton);
        }
    }
}
