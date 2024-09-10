package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class controlPanelPageSteps {

    public static void clickButtonAddNews() {       //Открытие страницы добавления новости
        ViewInteraction addNews = onView(withId(R.id.add_news_image_view));
        addNews.perform(click());
        authorizationPageSteps.waitPageLoad(R.id.custom_app_bar_title_text_view);
    }


}
