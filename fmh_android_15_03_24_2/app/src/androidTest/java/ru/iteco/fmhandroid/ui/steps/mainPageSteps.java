package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;

import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class mainPageSteps {

    public static void clickButtonMainMenu() {
        Allure.step("Нажатие на кнопку главного меню" + R.id.main_menu_image_button);
        ViewInteraction mainMenuButton = onView(withId(R.id.main_menu_image_button));
        mainMenuButton.perform(click());
    }

    public static void clickButtonNewsMenu() {
        Allure.step("Нажатие News в главном меню");
        ViewInteraction news = onView(withText("News"));
        news.perform(click());
        authorizationPageSteps.waitPageLoad(R.id.edit_news_material_button);
    }

    public static void clickButtonAboutMenu() {
        Allure.step("Нажатие About в главном меню");
        ViewInteraction about = onView(withText("About"));
        about.perform(click());
        authorizationPageSteps.waitPageLoad(R.id.about_version_title_text_view);
    }

}
