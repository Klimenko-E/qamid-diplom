package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class ControlPanelPageSteps {

    public static final int CUSTOM_APP_BAR_TITLE_TEXT_VIEW_ID = R.id.custom_app_bar_title_text_view;
    public static final int ADD_NEWS_IMAGE_VIEW_ID = R.id.add_news_image_view;

    public void clickButtonAddNews() {       //Открытие страницы добавления новости
        ViewInteraction addNews = onView(withId(ADD_NEWS_IMAGE_VIEW_ID));
        addNews.perform(click());
        WaitPageSteps.waitPageLoad(CUSTOM_APP_BAR_TITLE_TEXT_VIEW_ID);
    }
}
