package com.sit305.myapplication.ui.sports;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.List;

public class BookmarksFragment extends Fragment implements StoryAdapter.Listener {
    private StoryAdapter adapter;
    private TextView emptyText;

    public BookmarksFragment() {
        super(R.layout.fragment_bookmarks);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bookmarks, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.bookmarksRecyclerView);
        emptyText = view.findViewById(R.id.bookmarksEmptyText);

        adapter = new StoryAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);

        view.findViewById(R.id.bookmarksBackButton)
                .setOnClickListener(v -> NavHostFragment.findNavController(this).popBackStack());

        refreshBookmarks();
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshBookmarks();
    }

    private void refreshBookmarks() {
        List<SportsItem> items = BookmarkStorage.getBookmarkedItems(requireContext());
        adapter.submitList(items);
        emptyText.setVisibility(items.isEmpty() ? View.VISIBLE : View.GONE);
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
        refreshBookmarks();
    }
}
