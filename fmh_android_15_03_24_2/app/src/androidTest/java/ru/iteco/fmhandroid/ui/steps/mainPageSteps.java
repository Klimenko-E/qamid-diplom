package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;

import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


import static ru.iteco.fmhandroid.ui.constants.IdConstants.ABOUT_VERSION_TITLE_TEXT_VIEW_ID;
import static ru.iteco.fmhandroid.ui.constants.IdConstants.ALL_NEWS_TEXT_VIEW_ID;
import static ru.iteco.fmhandroid.ui.constants.IdConstants.CONTAINER_LIST_NEWS_FRAGMENT_ID;
import static ru.iteco.fmhandroid.ui.constants.IdConstants.EDIT_NEWS_MATERIAL_BUTTON_ID;
import static ru.iteco.fmhandroid.ui.constants.IdConstants.MAIN_MENU_IMAGE_BUTTON_ID;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class MainPageSteps {

    public void clickButtonMainMenu() {
        Allure.step("Нажатие на кнопку главного меню" + MAIN_MENU_IMAGE_BUTTON_ID);
        ViewInteraction mainMenuButton = onView(withId(MAIN_MENU_IMAGE_BUTTON_ID));
        mainMenuButton.perform(click());
    }

    public void waitMainPageLoading(){
        Allure.step("Проверка загрузки страницы");
        WaitPageSteps.waitPageLoad(CONTAINER_LIST_NEWS_FRAGMENT_ID);
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
        WaitPageSteps.waitPageLoad(ABOUT_VERSION_TITLE_TEXT_VIEW_ID);
    }
    public void clickAllNews() {
        Allure.step("Нажатие AllNews в главном меню");
        onView(withId(ALL_NEWS_TEXT_VIEW_ID)).perform(click()
        );
    }
}
