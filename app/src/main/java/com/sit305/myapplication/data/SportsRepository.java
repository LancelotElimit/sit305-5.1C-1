package com.sit305.myapplication.data;

import com.sit305.myapplication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class SportsRepository {
    private SportsRepository() {
    }

    public static List<String> getCategories() {
        return Arrays.asList("All", "Football", "Basketball", "Tennis", "Cricket");
    }

    public static List<SportsItem> getFeaturedMatches() {
        List<SportsItem> items = new ArrayList<>();
        items.add(new SportsItem("match_1", SportsItem.TYPE_MATCH, "Melbourne FC vs Sydney United",
                "A top-of-the-table clash headlines the weekend fixture list.",
                "Melbourne FC host Sydney United in a marquee football matchup with both teams pushing for first place. Expect a fast start, high pressing, and a tactical midfield battle that could decide the title race.",
                "Football", "Featured Match - Tonight 7:30 PM", R.drawable.bg_football_card));
        items.add(new SportsItem("match_2", SportsItem.TYPE_MATCH, "Boomers Select vs City Hoops",
                "Two elite backcourts go head-to-head in a high-scoring basketball showdown.",
                "Boomers Select bring the league's most efficient offense into a contest against City Hoops, whose transition game has dominated recent weeks. Three-point shooting and defensive rebounding will be the key swing factors.",
                "Basketball", "Featured Match - Wednesday 8:00 PM", R.drawable.bg_basketball_card));
        items.add(new SportsItem("match_3", SportsItem.TYPE_MATCH, "Aces vs Baseline Kings",
                "A semifinal preview with two of the strongest servers on tour.",
                "The Aces and Baseline Kings renew their rivalry in a tennis tie that could shape seeding for the next tournament. Analysts are watching for extended rallies, second-serve pressure, and net play adjustments.",
                "Tennis", "Featured Match - Friday 5:00 PM", R.drawable.bg_tennis_card));
        items.add(new SportsItem("match_4", SportsItem.TYPE_MATCH, "Harbour Strikers vs Northern XI",
                "A powerplay-heavy cricket fixture with playoff implications.",
                "Harbour Strikers meet Northern XI in a cricket contest built around aggressive batting and sharp death-over bowling. The early overs and spin matchups in the middle phase could decide the result.",
                "Cricket", "Featured Match - Saturday 6:45 PM", R.drawable.bg_cricket_card));
        return items;
    }

    public static List<SportsItem> getLatestNews() {
        List<SportsItem> items = new ArrayList<>();
        items.add(new SportsItem("news_1", SportsItem.TYPE_STORY,
                "Football leaders unveil new pressing system before derby week",
                "Coaches say the tactical tweak is designed to create more chances from turnovers.",
                "The league leaders have adjusted their shape to win possession higher up the field ahead of derby week. Training sessions this week emphasized compactness, quick combinations through the middle, and rapid support runs from wide areas.",
                "Football", "Latest News - 1 hour ago", R.drawable.bg_football_card));
        items.add(new SportsItem("news_2", SportsItem.TYPE_STORY,
                "Basketball captain returns after ankle injury",
                "The veteran guard is expected to lift the pace and defensive communication.",
                "After missing three games with an ankle injury, the team captain has been cleared to return. Coaches believe the comeback will stabilize late-game execution and restore their defensive rotations on the perimeter.",
                "Basketball", "Latest News - 2 hours ago", R.drawable.bg_basketball_card));
        items.add(new SportsItem("news_3", SportsItem.TYPE_STORY,
                "Tennis analysts praise improved serve placement on indoor courts",
                "Players are finding sharper angles and shorter service games this week.",
                "Tennis analysts have highlighted a noticeable improvement in serve placement across the draw. With indoor conditions rewarding precision, players are adapting with more body serves, quicker first-strike patterns, and smarter court positioning.",
                "Tennis", "Latest News - 3 hours ago", R.drawable.bg_tennis_card));
        items.add(new SportsItem("news_4", SportsItem.TYPE_STORY,
                "Cricket side reshuffles batting order for better powerplay starts",
                "Management wants more intent in the first six overs without sacrificing depth.",
                "The coaching group has reshuffled the batting order to create faster starts in the powerplay. The move aims to maximize aggressive stroke play while protecting the middle order from early pressure.",
                "Cricket", "Latest News - 4 hours ago", R.drawable.bg_cricket_card));
        items.add(new SportsItem("news_5", SportsItem.TYPE_STORY,
                "Football academy prospect earns senior squad call-up",
                "A breakout season in the youth team has been rewarded with promotion.",
                "A young football academy prospect has earned a senior call-up after a breakout month. Staff praised the player's work rate, tactical discipline, and confidence in one-versus-one situations.",
                "Football", "Latest News - 5 hours ago", R.drawable.bg_football_card));
        items.add(new SportsItem("news_6", SportsItem.TYPE_STORY,
                "Basketball coaches experiment with smaller closing lineup",
                "Spacing and switchability are central to the late-game plan.",
                "Basketball coaches are leaning toward a smaller closing lineup to improve switchability and perimeter spacing. The group has looked comfortable in recent scrimmages, especially in last-two-minute situations.",
                "Basketball", "Latest News - 6 hours ago", R.drawable.bg_basketball_card));
        return items;
    }

    public static List<SportsItem> getAllItems() {
        List<SportsItem> items = new ArrayList<>();
        items.addAll(getFeaturedMatches());
        items.addAll(getLatestNews());
        return items;
    }

    public static SportsItem getItemById(String id) {
        for (SportsItem item : getAllItems()) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    public static List<SportsItem> getRelatedStories(SportsItem currentItem) {
        List<SportsItem> related = new ArrayList<>();
        if (currentItem == null) {
            return related;
        }
        for (SportsItem item : getAllItems()) {
            if (!item.getId().equals(currentItem.getId())
                    && item.getCategory().equalsIgnoreCase(currentItem.getCategory())) {
                related.add(item);
            }
        }
        return related;
    }
}
