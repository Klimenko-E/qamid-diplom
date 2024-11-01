package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.constants.IdConstants.BUTTON_1_ID;
import static ru.iteco.fmhandroid.ui.constants.IdConstants.CANCEL_BUTTON_ID;
import static ru.iteco.fmhandroid.ui.constants.IdConstants.NEWS_ITEM_CATEGORY_TEXT_ID;
import static ru.iteco.fmhandroid.ui.constants.IdConstants.NEWS_ITEM_DESCRIPTION_ID;
import static ru.iteco.fmhandroid.ui.constants.IdConstants.NEWS_ITEM_PUBLISH_DATE_ID;
import static ru.iteco.fmhandroid.ui.constants.IdConstants.NEWS_ITEM_PUBLISH_TIME_ID;
import static ru.iteco.fmhandroid.ui.constants.IdConstants.NEWS_ITEM_TITLE_TEXT_ID;
import static ru.iteco.fmhandroid.ui.constants.IdConstants.SAVE_BUTTON_ID;
import static ru.iteco.fmhandroid.ui.constants.StringConstants.CONFIRM_TEXT;
import static ru.iteco.fmhandroid.ui.constants.StringConstants.EXAMPLE_CONTENT_DESCRIPTION;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.hamcrest.Matchers;
import org.junit.Rule;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

public class CreatingNewsPageSteps {

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

    public void checkErrorWindow() {
        Allure.step("Проверка отображения модального окна");
        onView(withText(R.string.empty_fields)).inRoot(new ToastMatcher());
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
