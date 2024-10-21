package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;

import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class MainPageSteps {
    private static final int ALL_NEWS_TEXT_VIEW_ID = R.id.all_news_text_view;
    public static final int MAIN_MENU_IMAGE_BUTTON_ID = R.id.main_menu_image_button;
    public static final int EDIT_NEWS_MATERIAL_BUTTON_ID = R.id.edit_news_material_button;
    public static final int ABOUT_VERSION_TITLE_ID = R.id.about_version_title_text_view;

    public void clickButtonMainMenu() {
        Allure.step("Нажатие на кнопку главного меню" + MAIN_MENU_IMAGE_BUTTON_ID);
        ViewInteraction mainMenuButton = onView(withId(MAIN_MENU_IMAGE_BUTTON_ID));
        mainMenuButton.perform(click());
    }

    public void clickButtonNewsMenu() {
        Allure.step("Нажатие News в главном меню");
        ViewInteraction news = onView(withText(R.string.news));
        news.perform(click());
        WaitPageSteps.waitPageLoad(EDIT_NEWS_MATERIAL_BUTTON_ID);
    }

    public void clickButtonAboutMenu() {
        Allure.step("Нажатие About в главном меню");
        ViewInteraction about = onView(withText(R.string.about));
        about.perform(click());
        WaitPageSteps.waitPageLoad(ABOUT_VERSION_TITLE_ID);
    }
    public void clickAllNews() {
        onView(withId(ALL_NEWS_TEXT_VIEW_ID)).perform(click());
    }
}
