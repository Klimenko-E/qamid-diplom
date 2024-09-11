package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;

import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class aboutPageSteps {
    public static void clickButtonAboutBack() {
        Allure.step("Нажатие назад на странице About");
        ViewInteraction buttonAboutBack = onView(withId(R.id.about_back_image_button));
        buttonAboutBack.perform(click());
        authorizationPageSteps.waitPageLoad(R.id.container_list_news_include_on_fragment_main);
    }

}
