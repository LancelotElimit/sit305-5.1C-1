# Sports News Feed App

This project is an Android application built for `SIT305 Task 5.1C` Subtask 1.  
It demonstrates a `Single Activity Architecture` using `Fragments`, `RecyclerView`, and local bookmark storage.

## Overview

The app is a simple sports content feed with:

- A horizontal `Featured Matches` section
- A vertical `Latest Sports News` section
- A detail screen for each story or match
- A `Related Stories` list inside the detail screen
- Search and category-based filtering
- Local bookmark support
- A dedicated bookmarks screen

All content is implemented using hardcoded dummy data, as allowed in the task requirements.

## Features

- Single `MainActivity` with Fragment-based navigation
- `SportsHomeFragment` as the home page
- Horizontal `RecyclerView` for featured matches
- Vertical `RecyclerView` for latest sports news
- `SportsDetailFragment` with:
  - large image
  - title
  - description
  - related stories list
- Search bar for keyword filtering
- Sport category filtering using a spinner
- Bookmark button for saving favourite items locally
- `BookmarksFragment` for viewing saved items later

## Tech Stack

- Java
- Android Fragments
- RecyclerView
- Navigation Component
- SharedPreferences
- Material Components

## Project Structure

```text
app/src/main/java/com/sit305/myapplication/
|-- MainActivity.java
|-- data/
|   |-- BookmarkStorage.java
|   |-- SportsItem.java
|   `-- SportsRepository.java
`-- ui/sports/
    |-- BookmarksFragment.java
    |-- FeaturedAdapter.java
    |-- SportsDetailFragment.java
    |-- SportsHomeFragment.java
    `-- StoryAdapter.java
```

## File Responsibilities

- `MainActivity.java`
  Hosts the `NavHostFragment` and provides the single-activity app container.

- `SportsItem.java`
  Defines the data model used for both featured matches and sports stories.

- `SportsRepository.java`
  Stores hardcoded dummy data and provides helper methods for categories, details, and related stories.

- `BookmarkStorage.java`
  Saves and retrieves bookmarked items using `SharedPreferences`.

- `FeaturedAdapter.java`
  Displays the horizontal featured matches list.

- `StoryAdapter.java`
  Displays vertical news-style lists, including latest news, related stories, and bookmarks.

- `SportsHomeFragment.java`
  Controls the home screen, search/filter logic, and navigation to detail/bookmarks screens.

- `SportsDetailFragment.java`
  Displays the selected item's full content and related stories.

- `BookmarksFragment.java`
  Displays all locally saved bookmarked stories and matches.

## How to Run

1. Open the project in Android Studio.
2. Sync Gradle dependencies.
3. Run the app on an emulator or Android device.

To build from the command line:

```powershell
.\gradlew.bat assembleDebug
```

## Build Status

The project builds successfully with:

```powershell
.\gradlew.bat assembleDebug
```

## Assignment Notes

This repository currently implements `Subtask 1: Sports News Feed App`.

Implemented requirements:

- Single Activity Architecture
- Fragment-based navigation
- Horizontal and vertical RecyclerViews
- Detail Fragment with related stories
- Search/filter by sport category
- Bookmark feature
- Dedicated bookmarks fragment
- Hardcoded dummy data

## Author

- Student project for `SIT305`
