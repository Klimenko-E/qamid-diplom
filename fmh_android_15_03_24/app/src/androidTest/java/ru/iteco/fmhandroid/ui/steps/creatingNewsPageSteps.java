package ru.iteco.fmhandroid.ui.steps;

import io.qameta.allure.kotlin.Allure;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class creatingNewsPageSteps {

    public static void titleSelect(String title) {
        Allure.step("Выбор категории новости");
        ViewInteraction newsCategoryText = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
        newsCategoryText.perform(replaceText(title), closeSoftKeyboard());
    }

    public static void titleTextInput(String titleText) {
        Allure.step("Добавление текста категории новости");
        ViewInteraction newsTitleText = onView(withId(R.id.news_item_title_text_input_edit_text));
        newsTitleText.perform(replaceText(titleText), closeSoftKeyboard());
    }

    public static void newsPublishDateInput() {
        Allure.step("Добавление даты публикации новости");
        ViewInteraction newsDateText = onView(withId(R.id.news_item_publish_date_text_input_edit_text));
        newsDateText.perform(click());
        ViewInteraction buttonOkDate = onView(withId(android.R.id.button1));
        authorizationPageSteps.waitPageLoad(android.R.id.button1);
        buttonOkDate.perform(click());
    }

    public static void newsPublishTimeInput() {
        Allure.step("Добавление времени публикации новости");
        ViewInteraction newsTimeText = onView(withId(R.id.news_item_publish_time_text_input_edit_text));
        newsTimeText.perform(click());
        ViewInteraction buttonOkTime = onView(withId(android.R.id.button1));
        authorizationPageSteps.waitPageLoad(android.R.id.button1);
        buttonOkTime.perform(click());
    }

    public static void descriptionTextInput(String textDescription) {
        Allure.step("Добавление текста новости");
        ViewInteraction newsDescriptionText = onView(withId(R.id.news_item_description_text_input_edit_text));
        newsDescriptionText.perform(replaceText(textDescription), closeSoftKeyboard());
    }

    public static void clickSaveButton() {
        Allure.step("Сохранение добавленной новости");
        ViewInteraction saveButton = onView(withId(R.id.save_button));
        saveButton.perform(click());
        authorizationPageSteps.waitPageLoad(R.id.add_news_image_view);
    }


}
