package ru.iteco.fmhandroid.ui.test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;

import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import org.hamcrest.Matchers;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.util.UUID;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Epic;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.steps.authorizationPageSteps;
import ru.iteco.fmhandroid.ui.steps.controlPanelPageSteps;
import ru.iteco.fmhandroid.ui.steps.creatingNewsPageSteps;
import ru.iteco.fmhandroid.ui.steps.mainPageSteps;
import ru.iteco.fmhandroid.ui.steps.newsPageSteps;
import ru.iteco.fmhandroid.ui.steps.Base;

import static ru.iteco.fmhandroid.ui.steps.Base.withIndex;

@LargeTest
//@RunWith(AndroidJUnit4.class)
@RunWith(AllureAndroidJUnit4.class)

@Epic("Тестирование страницы новостей")

public class NewsTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void authorizationPageLoaded() {
        try {
            authorizationPageSteps.waitPageLoad(R.id.login_text_input_layout);
            ViewInteraction textView = onView(withId(R.id.login_text_input_layout));
            textView.check(matches(isDisplayed()));
        } catch (Exception e) {
            authorizationPageSteps.logOut();
        }
        authorizationPageSteps.autorizationValid();
        authorizationPageSteps.waitPageLoad(R.id.container_list_news_include_on_fragment_main);

    }

    @After
    public void logOut() {
        authorizationPageSteps.logOut();
    }

    @Test
    @Description("28 - Создание новости")
    public void creatingNewsTest() {

        String uniqueID = UUID.randomUUID().toString();
        String newsTitle = "Заголовок тестовой новости " + uniqueID;
        String newsDescription = "Описание тестовой новости " + uniqueID;

        mainPageSteps.clickButtonMainMenu();                               //Нажатие на кнопку главного меню
        mainPageSteps.clickButtonNewsMenu();                                //Нажатие кнопки "Новости" в главном меню
        newsPageSteps.clickButtonEditNews();                                //Открытие Control panel

        controlPanelPageSteps.clickButtonAddNews();                         //Открытие страницы добавления новости

        creatingNewsPageSteps.titleSelect("Объявление");                    //Выбор категории новости
        creatingNewsPageSteps.titleTextInput(newsTitle);                    //Добавление текста категории новости
        creatingNewsPageSteps.newsPublishDateInput();                       //Добавление даты публикации новости
        creatingNewsPageSteps.newsPublishTimeInput();                       //Добавление времени публикации новости
        creatingNewsPageSteps.descriptionTextInput(newsDescription);        //Добавление текста новости
        creatingNewsPageSteps.clickSaveButton();                            //Сохранение добавленной новости

        onView(withText(newsTitle)).perform(scrollTo()).check(matches(isDisplayed()));
        //onView(withText(newsTitle)).check(matches(isDisplayed()));          //Проверка
        // authorizationPageSteps.logOut();                                    //Выход из аккаунта
    }

    @Test
    @Description("18 - Разворачивание новости на странице News")
    public void unfoldingNewsPageNews() {

        String uniqueID = UUID.randomUUID().toString();
        String newsTitle = "Заголовок тестовой новости " + uniqueID;
        String newsDescription = "Описание тестовой новости " + uniqueID;

        mainPageSteps.clickButtonMainMenu();                               //Нажатие на кнопку главного меню
        mainPageSteps.clickButtonNewsMenu();                                //Нажатие кнопки "Новости" в главном меню
        newsPageSteps.clickButtonEditNews();                                //Открытие Control panel

        controlPanelPageSteps.clickButtonAddNews();                         //Открытие страницы добавления новости

        creatingNewsPageSteps.titleSelect("Объявление");                    //Выбор категории новости
        creatingNewsPageSteps.titleTextInput(newsTitle);                    //Добавление текста категории новости
        creatingNewsPageSteps.newsPublishDateInput();                       //Добавление даты публикации новости
        creatingNewsPageSteps.newsPublishTimeInput();                       //Добавление времени публикации новости
        creatingNewsPageSteps.descriptionTextInput(newsDescription);        //Добавление текста новости
        creatingNewsPageSteps.clickSaveButton();                            //Сохранение добавленной новости
        ViewInteraction newNews = onView(withText(newsTitle));              // Поиск категории новой новости
        newNews.perform(click());                                           // Разворачивание новости

        onView(withText(newsDescription)).perform(scrollTo()).check(matches(isDisplayed()));
        //onView(withText(newsDescription)).check(matches(isDisplayed()));    //Проверка

        //   authorizationPageSteps.logOut();                                    //Выход из аккаунта
    }

    @Test
    @Description("23 - Удаление новостей")
    public void deleteNews() {

        mainPageSteps.clickButtonMainMenu();                               //Нажатие на кнопку главного меню
        mainPageSteps.clickButtonNewsMenu();                                //Нажатие кнопки "Новости" в главном меню
        newsPageSteps.clickButtonEditNews();                                //Открытие Control panel

        onView(withIndex(withId(R.id.delete_news_item_image_view), 0)).perform(click());
        onView(withText("OK")).perform(click());
        onView(withId(R.id.news_list_recycler_view)).check(matches(isDisplayed()));

    }

    @Test
    @Description("23 - Переход со страницы Main на страницу News через ссылку All News")
    public void goToAllNews() {

        onView(withId(R.id.all_news_text_view)).perform(click());
        authorizationPageSteps.waitPageLoad(R.id.edit_news_material_button);
        onView(withId(R.id.edit_news_material_button)).check(matches(isDisplayed()));

    }

    @Test
    @Description("31 - Создание новости с незаданной категорией")
    public void creatingNewsWithEmptyCategoryTest() {

        String uniqueID = UUID.randomUUID().toString();
        String newsTitle = "Заголовок тестовой новости " + uniqueID;
        String newsDescription = "Описание тестовой новости " + uniqueID;

        mainPageSteps.clickButtonMainMenu();                               //Нажатие на кнопку главного меню
        mainPageSteps.clickButtonNewsMenu();                                //Нажатие кнопки "Новости" в главном меню
        newsPageSteps.clickButtonEditNews();                                //Открытие Control panel

        controlPanelPageSteps.clickButtonAddNews();                         //Открытие страницы добавления новости

        creatingNewsPageSteps.titleTextInput(newsTitle);                    //Добавление текста категории новости
        creatingNewsPageSteps.newsPublishDateInput();                       //Добавление даты публикации новости
        creatingNewsPageSteps.newsPublishTimeInput();                       //Добавление времени публикации новости
        creatingNewsPageSteps.descriptionTextInput(newsDescription);        //Добавление текста новости
        ViewInteraction saveButton = onView(withId(R.id.save_button));
        saveButton.perform(click());

        onView(Matchers.allOf(withContentDescription("Fill empty fields"), isDisplayed()));
        onView(withId(R.id.cancel_button)).perform(click());
        onView(withText("OK")).perform(click());

    }

    @Test
    @Description("26 - Сортировка новостей на странице Панель управления")
    public void sortNews() {

        mainPageSteps.clickButtonMainMenu();                               //Нажатие на кнопку главного меню
        mainPageSteps.clickButtonNewsMenu();                                //Нажатие кнопки "Новости" в главном меню
        newsPageSteps.clickFilterNewsButton();


        onView(withId(R.id.news_item_category_text_auto_complete_text_view)).perform(click());
        onView(withId(R.id.news_item_category_text_auto_complete_text_view)).perform(replaceText("Зарплата"));

        onView(withId(R.id.filter_button)).perform(click());
        onView(withId(R.id.all_news_cards_block_constraint_layout)).check(matches(isDisplayed()));
    }

}
