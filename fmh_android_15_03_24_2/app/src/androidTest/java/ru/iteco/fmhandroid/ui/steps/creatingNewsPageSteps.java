package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matchers;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class CreatingNewsPageSteps {
    private static final int CANCEL_BUTTON_ID = R.id.cancel_button;
    private static final String EXAMPLE_CONTENT_DESCRIPTION = "Fill empty fields";
    private static final String CONFIRM_TEXT = "OK";
    public static final int NEWS_ITEM_CATEGORY_TEXT_ID = R.id.news_item_category_text_auto_complete_text_view;
    public static final int NEWS_ITEM_TITLE_TEXT_ID = R.id.news_item_title_text_input_edit_text;
    public static final int NEWS_ITEM_PUBLISH_DATE_ID = R.id.news_item_publish_date_text_input_edit_text;
    public static final int BUTTON_1_ID = android.R.id.button1;
    public static final int NEWS_ITEM_PUBLISH_TIME_ID = R.id.news_item_publish_time_text_input_edit_text;
    public static final int NEWS_ITEM_DESCRIPTION_ID = R.id.news_item_description_text_input_edit_text;
    public static final int SAVE_BUTTON_ID = R.id.save_button;

    public void titleSelect(String title) {
        Allure.step("Выбор категории новости");
        ViewInteraction newsCategoryText = onView(withId(NEWS_ITEM_CATEGORY_TEXT_ID));
        newsCategoryText.perform(replaceText(title), closeSoftKeyboard());
    }

    public void titleTextInput(String titleText) {
        Allure.step("Добавление текста категории новости");
        ViewInteraction newsTitleText = onView(withId(NEWS_ITEM_TITLE_TEXT_ID));
        newsTitleText.perform(replaceText(titleText), closeSoftKeyboard());
    }

    public void newsPublishDateInput() {
        Allure.step("Добавление даты публикации новости");
        ViewInteraction newsDateText = onView(withId(NEWS_ITEM_PUBLISH_DATE_ID));
        newsDateText.perform(click());
        ViewInteraction buttonOkDate = onView(withId(BUTTON_1_ID));
        WaitPageSteps.waitPageLoad(BUTTON_1_ID);
        buttonOkDate.perform(click());
    }

    public void newsPublishTimeInput() {
        Allure.step("Добавление времени публикации новости");
        ViewInteraction newsTimeText = onView(withId(NEWS_ITEM_PUBLISH_TIME_ID));
        newsTimeText.perform(click());
        ViewInteraction buttonOkTime = onView(withId(BUTTON_1_ID));
        WaitPageSteps.waitPageLoad(BUTTON_1_ID);
        buttonOkTime.perform(click());
    }

    public void descriptionTextInput(String textDescription) {
        Allure.step("Добавление текста новости");
        ViewInteraction newsDescriptionText = onView(withId(NEWS_ITEM_DESCRIPTION_ID));
        newsDescriptionText.perform(replaceText(textDescription), closeSoftKeyboard());
    }

    public void clickSaveButton() {
        Allure.step("Сохранение добавленной новости");
        ViewInteraction saveButton = onView(withId(SAVE_BUTTON_ID));
        saveButton.perform(click());
    }

    public void clickCancelButton() {
        onView(Matchers.allOf(withContentDescription(EXAMPLE_CONTENT_DESCRIPTION), isDisplayed()));
        onView(withId(CANCEL_BUTTON_ID)).perform(click());
        onView(withText(CONFIRM_TEXT)).perform(click());
    }
}
