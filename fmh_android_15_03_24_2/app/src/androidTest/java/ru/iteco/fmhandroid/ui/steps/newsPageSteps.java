package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class newsPageSteps {

    public static void clickButtonEditNews() {
        Allure.step("Открытие Control panel");
        ViewInteraction editNewsButton = onView(withId(R.id.edit_news_material_button));
        editNewsButton.perform(click());
        authorizationPageSteps.waitPageLoad(R.id.add_news_image_view);
    }

    public static void clickFilterNewsButton() {
        Allure.step("Нажатие кнопки фильтра новостей");
        ViewInteraction filterNewsButton = onView(withId(R.id.filter_news_material_button));
        filterNewsButton.perform(click());
    }

}
