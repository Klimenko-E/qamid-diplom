package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static ru.iteco.fmhandroid.ui.constants.IdConstants.ADD_NEWS_IMAGE_VIEW_ID;
import static ru.iteco.fmhandroid.ui.constants.IdConstants.CUSTOM_APP_BAR_TITLE_TEXT_VIEW_ID;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class ControlPanelPageSteps {


    public void clickButtonAddNews() {
        Allure.step("Открытие страницы добавления новости");
        ViewInteraction addNews = onView(withId(ADD_NEWS_IMAGE_VIEW_ID));
        addNews.perform(click());
        WaitPageSteps.waitPageLoad(CUSTOM_APP_BAR_TITLE_TEXT_VIEW_ID);
    }
}
