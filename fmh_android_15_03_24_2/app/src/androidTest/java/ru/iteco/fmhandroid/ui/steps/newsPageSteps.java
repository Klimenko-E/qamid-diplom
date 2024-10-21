package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


import static org.hamcrest.core.AllOf.allOf;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class NewsPageSteps {
    public static final int ADD_NEWS_IMAGE_ID = R.id.add_news_image_view;
    private static final int NEWS_LIST_RECYCLER_VIEW_ID = R.id.news_list_recycler_view;
    private static final int WAITING_LOAD_TIME = 1000;
    private static final String CONFIRM_DELETE_TEXT = "OK";
    private static final int NEWS_ITEM_CATEGORY_VIEW_ID =
            R.id.news_item_category_text_auto_complete_text_view;
    private static final int FILTER_BUTTON_ID =
            R.id.filter_button;
    private static final int ALL_NEWS_CARDS_BLOCK_LAYOUT_ID =
            R.id.all_news_cards_block_constraint_layout;
    public static final int EDIT_NEWS_BUTTON_ID = R.id.edit_news_material_button;
    public static final int FILTER_NEWS_BUTTON_ID = R.id.filter_news_material_button;

    public void clickButtonEditNews() {
        Allure.step("Открытие Control panel");
        ViewInteraction editNewsButton = onView(withId(EDIT_NEWS_BUTTON_ID));
        editNewsButton.perform(click());
        WaitPageSteps.waitPageLoad(ADD_NEWS_IMAGE_ID);
    }

    public void clickFilterNewsButton() {
        Allure.step("Нажатие кнопки фильтра новостей");
        ViewInteraction filterNewsButton = onView(withId(FILTER_NEWS_BUTTON_ID));
        filterNewsButton.perform(click());
    }

    public void checkExistRecyclerViewItem(String description) {
        ViewInteraction newNews = scrollToElement(description);
        newNews.check(matches(isDisplayed()));
    }

    public ViewInteraction scrollToElement(String description) {
        return WaitPageSteps.waitView(withId(NEWS_LIST_RECYCLER_VIEW_ID))
                .perform(RecyclerViewActions.scrollTo(
                        allOf(hasDescendant(withText(description)))));
    }


    public void clickToRecyclerViewItem(String newsDescription) {
        scrollToElement(newsDescription)
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void confirmDeleteNews() {
        WaitPageSteps.waitLoad(WAITING_LOAD_TIME);

        ViewInteraction okButton = WaitPageSteps.waitView(withText(CONFIRM_DELETE_TEXT));

        okButton.check(matches(isDisplayed())).perform(click());

        WaitPageSteps.waitView(withId(NEWS_LIST_RECYCLER_VIEW_ID)).check(matches(isDisplayed()));
    }
    public void checkDisplayedView(int id) {
        onView(withId(id)).check(matches(isDisplayed()));
    }

    public void filterNewsByCategory(String category){

        onView(withId(NEWS_ITEM_CATEGORY_VIEW_ID)).perform(click());
        onView(withId(NEWS_ITEM_CATEGORY_VIEW_ID)).perform(replaceText(category));

        onView(withId(FILTER_BUTTON_ID)).perform(click());
        checkDisplayedView(ALL_NEWS_CARDS_BLOCK_LAYOUT_ID);

    }
}
