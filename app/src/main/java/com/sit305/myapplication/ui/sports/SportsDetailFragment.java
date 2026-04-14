package com.sit305.myapplication.ui.sports;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sit305.myapplication.R;
import com.sit305.myapplication.data.BookmarkStorage;
import com.sit305.myapplication.data.SportsItem;
import com.sit305.myapplication.data.SportsRepository;

public class SportsDetailFragment extends Fragment implements StoryAdapter.Listener {
    private SportsItem currentItem;
    private ImageButton bookmarkButton;
    private StoryAdapter relatedAdapter;

    public SportsDetailFragment() {
        super(R.layout.fragment_sports_detail);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sports_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ImageView heroImage = view.findViewById(R.id.detailHeroImage);
        TextView categoryText = view.findViewById(R.id.detailCategoryText);
        TextView titleText = view.findViewById(R.id.detailTitleText);
        TextView metaText = view.findViewById(R.id.detailMetaText);
        TextView descriptionText = view.findViewById(R.id.detailDescriptionText);
        ImageButton backButton = view.findViewById(R.id.detailBackButton);
        bookmarkButton = view.findViewById(R.id.detailBookmarkButton);
        RecyclerView relatedRecycler = view.findViewById(R.id.relatedRecyclerView);

        String itemId = getArguments() != null ? getArguments().getString("item_id") : null;
        currentItem = SportsRepository.getItemById(itemId);
        if (currentItem == null) {
            NavHostFragment.findNavController(this).popBackStack();
            return;
        }

        heroImage.setImageResource(currentItem.getImageResId());
        categoryText.setText(currentItem.getCategory());
        titleText.setText(currentItem.getTitle());
        metaText.setText(currentItem.getMeta());
        descriptionText.setText(currentItem.getDescription());

        relatedAdapter = new StoryAdapter(this);
        relatedRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        relatedRecycler.setNestedScrollingEnabled(false);
        relatedRecycler.setAdapter(relatedAdapter);
        relatedAdapter.submitList(SportsRepository.getRelatedStories(currentItem));

        refreshBookmarkIcon();

        backButton.setOnClickListener(v -> NavHostFragment.findNavController(this).popBackStack());
        bookmarkButton.setOnClickListener(v -> {
            BookmarkStorage.toggleBookmark(requireContext(), currentItem.getId());
            refreshBookmarkIcon();
            relatedAdapter.notifyDataSetChanged();
        });
    }

    private void refreshBookmarkIcon() {
        bookmarkButton.setImageResource(BookmarkStorage.isBookmarked(requireContext(), currentItem.getId())
                ? android.R.drawable.btn_star_big_on
                : android.R.drawable.btn_star_big_off);
    }

    @Override
    public void onItemClicked(SportsItem item) {
        Bundle bundle = new Bundle();
        bundle.putString("item_id", item.getId());
        NavHostFragment.findNavController(this).navigate(R.id.sportsDetailFragment, bundle);
    }

    @Override
    public void onBookmarkToggled(SportsItem item) {
        BookmarkStorage.toggleBookmark(requireContext(), item.getId());
        relatedAdapter.notifyDataSetChanged();
        refreshBookmarkIcon();
    }
}
