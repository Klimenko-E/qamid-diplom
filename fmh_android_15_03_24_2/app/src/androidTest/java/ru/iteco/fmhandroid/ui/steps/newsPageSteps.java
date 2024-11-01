package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static ru.iteco.fmhandroid.ui.constants.IdConstants.ADD_NEWS_IMAGE_VIEW_ID;
import static ru.iteco.fmhandroid.ui.constants.IdConstants.ALL_NEWS_CARDS_BLOCK_LAYOUT_ID;
import static ru.iteco.fmhandroid.ui.constants.IdConstants.EDIT_NEWS_MATERIAL_BUTTON_ID;
import static ru.iteco.fmhandroid.ui.constants.IdConstants.FILTER_BUTTON_ID;
import static ru.iteco.fmhandroid.ui.constants.IdConstants.FILTER_NEWS_BUTTON_ID;
import static ru.iteco.fmhandroid.ui.constants.IdConstants.NEWS_ITEM_CATEGORY_TEXT_ID;
import static ru.iteco.fmhandroid.ui.constants.IdConstants.NEWS_LIST_RECYCLER_VIEW_ID;
import static ru.iteco.fmhandroid.ui.constants.IdConstants.WAITING_LOAD_TIME;
import static ru.iteco.fmhandroid.ui.constants.StringConstants.CONFIRM_TEXT;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;

import io.qameta.allure.kotlin.Allure;

public class NewsPageSteps {


    public void clickButtonEditNews() {
        Allure.step("Открытие Control panel");
        ViewInteraction editNewsButton = onView(withId(EDIT_NEWS_MATERIAL_BUTTON_ID));
        editNewsButton.perform(click());
        WaitPageSteps.waitPageLoad(ADD_NEWS_IMAGE_VIEW_ID);
    }

    public void clickFilterNewsButton() {
        Allure.step("Нажатие кнопки фильтра новостей");
        ViewInteraction filterNewsButton = onView(withId(FILTER_NEWS_BUTTON_ID));
        filterNewsButton.perform(click());
    }

    public void checkExistRecyclerViewItem(String description) {
        Allure.step("Проверка наличия элемента в RecyclerView с описанием: " + description);
        ViewInteraction newNews = scrollToElement(description);
        newNews.check(matches(isDisplayed()));
    }

    public ViewInteraction scrollToElement(String description) {
        Allure.step("Скролл до элемента с описанием: " + description);
        return WaitPageSteps.waitView(withId(NEWS_LIST_RECYCLER_VIEW_ID))
                .perform(RecyclerViewActions.scrollTo(
                        allOf(hasDescendant(withText(description)))));
    }



    public void waitPageLoading(){
        Allure.step("Проверка загрузки страницы");
        WaitPageSteps.waitPageLoad(EDIT_NEWS_MATERIAL_BUTTON_ID);
        checkDisplayedView(EDIT_NEWS_MATERIAL_BUTTON_ID);
    }

    public void clickToRecyclerViewItem(String newsDescription) {
        Allure.step("Нажатие на элемент RecyclerView с описанием: " + newsDescription);
        scrollToElement(newsDescription)
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void confirmDeleteNews() {
        Allure.step("Подтверждение удаления новости");
        WaitPageSteps.waitLoad(WAITING_LOAD_TIME);

        ViewInteraction okButton = WaitPageSteps.waitView(withText(CONFIRM_TEXT));

        okButton.check(matches(isDisplayed())).perform(click());

        WaitPageSteps.waitView(withId(NEWS_LIST_RECYCLER_VIEW_ID)).check(matches(isDisplayed()));
    }
    public void checkDisplayedView(int id) {
        Allure.step("Проверка отображения элемента с ID: " + id);
        onView(withId(id)).check(matches(isDisplayed()));
    }

    public void filterNewsByCategory(String category){
        Allure.step("Фильтрация новостей по категории: " + category);
        onView(withId(NEWS_ITEM_CATEGORY_TEXT_ID)).perform(click());
        onView(withId(NEWS_ITEM_CATEGORY_TEXT_ID)).perform(replaceText(category));

        onView(withId(FILTER_BUTTON_ID)).perform(click());
        checkDisplayedView(ALL_NEWS_CARDS_BLOCK_LAYOUT_ID);

    }
}
