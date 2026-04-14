package com.sit305.myapplication.ui.sports;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.sit305.myapplication.R;
import com.sit305.myapplication.data.BookmarkStorage;
import com.sit305.myapplication.data.SportsItem;
import com.sit305.myapplication.data.SportsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SportsHomeFragment extends Fragment implements FeaturedAdapter.Listener, StoryAdapter.Listener {
    private FeaturedAdapter featuredAdapter;
    private StoryAdapter storyAdapter;
    private EditText searchInput;
    private Spinner categorySpinner;

    public SportsHomeFragment() {
        super(R.layout.fragment_sports_home);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sports_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView featuredRecycler = view.findViewById(R.id.featuredRecyclerView);
        RecyclerView latestRecycler = view.findViewById(R.id.latestRecyclerView);
        MaterialButton bookmarksButton = view.findViewById(R.id.openBookmarksButton);
        searchInput = view.findViewById(R.id.searchEditText);
        categorySpinner = view.findViewById(R.id.categorySpinner);

        featuredAdapter = new FeaturedAdapter(this);
        storyAdapter = new StoryAdapter(this);

        featuredRecycler.setLayoutManager(
                new LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false));
        featuredRecycler.setAdapter(featuredAdapter);

        latestRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        latestRecycler.setNestedScrollingEnabled(false);
        latestRecycler.setAdapter(storyAdapter);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                SportsRepository.getCategories()
        );
        categorySpinner.setAdapter(spinnerAdapter);

        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                refreshLists();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                refreshLists();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        bookmarksButton.setOnClickListener(v ->
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_sportsHomeFragment_to_bookmarksFragment));

        refreshLists();
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshLists();
    }

    private void refreshLists() {
        String query = searchInput == null ? "" : searchInput.getText().toString().trim().toLowerCase(Locale.ROOT);
        String selectedCategory = categorySpinner == null || categorySpinner.getSelectedItem() == null
                ? "All"
                : categorySpinner.getSelectedItem().toString();

        featuredAdapter.submitList(filterItems(SportsRepository.getFeaturedMatches(), query, selectedCategory));
        storyAdapter.submitList(filterItems(SportsRepository.getLatestNews(), query, selectedCategory));
    }

    private List<SportsItem> filterItems(List<SportsItem> source, String query, String selectedCategory) {
        List<SportsItem> filtered = new ArrayList<>();
        for (SportsItem item : source) {
            boolean matchesCategory = "All".equalsIgnoreCase(selectedCategory)
                    || item.getCategory().equalsIgnoreCase(selectedCategory);
            boolean matchesQuery = query.isEmpty()
                    || item.getTitle().toLowerCase(Locale.ROOT).contains(query)
                    || item.getSummary().toLowerCase(Locale.ROOT).contains(query)
                    || item.getCategory().toLowerCase(Locale.ROOT).contains(query);
            if (matchesCategory && matchesQuery) {
                filtered.add(item);
            }
        }
        return filtered;
    }

    @Override
    public void onItemClicked(SportsItem item) {
        Bundle bundle = new Bundle();
        bundle.putString("item_id", item.getId());
        NavHostFragment.findNavController(this)
                .navigate(R.id.action_sportsHomeFragment_to_sportsDetailFragment, bundle);
    }

    @Override
    public void onBookmarkToggled(SportsItem item) {
        BookmarkStorage.toggleBookmark(requireContext(), item.getId());
        refreshLists();
    }
}
