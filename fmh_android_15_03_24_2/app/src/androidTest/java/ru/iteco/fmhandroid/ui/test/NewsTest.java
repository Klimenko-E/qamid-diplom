package ru.iteco.fmhandroid.ui.test;

import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;

import android.view.View;
import android.view.ViewGroup;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.hamcrest.Matcher;
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
import ru.iteco.fmhandroid.ui.steps.AuthorizationPageSteps;
import ru.iteco.fmhandroid.ui.steps.ControlPanelPageSteps;
import ru.iteco.fmhandroid.ui.steps.CreatingNewsPageSteps;
import ru.iteco.fmhandroid.ui.steps.MainPageSteps;
import ru.iteco.fmhandroid.ui.steps.NewsPageSteps;
import ru.iteco.fmhandroid.ui.steps.WaitPageSteps;

@LargeTest
//@RunWith(AndroidJUnit4.class)
@RunWith(AllureAndroidJUnit4.class)

@Epic("Тестирование страницы новостей")

public class NewsTest {

    private static final int LOGIN_TEXT_INPUT_LAYOUT_ID =
            R.id.login_text_input_layout;
    private static final int CONTAINER_LIST_NEWS_ID =
            R.id.container_list_news_include_on_fragment_main;
    private static final int DELETE_NEWS_ITEM_VIEW_ID =
            R.id.delete_news_item_image_view;
    private static final int EDIT_NEWS_MATERIAL_BUTTON_ID =
            R.id.edit_news_material_button;

    private static final String TITLE_NEWS_ID = "Заголовок тестовой новости ";
    private static final String DESCRIPTION_NEWS_ID = "Описание тестовой новости ";
    private static final String SALARY = "Зарплата";
    private static final String EXAMPLE_CATEGORY = "Объявление";


    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private MainPageSteps mainPageSteps;
    private NewsPageSteps newsPageSteps;
    private ControlPanelPageSteps controlPanelPageSteps;
    private CreatingNewsPageSteps creatingNewsPageSteps;

    @Before
    public void authorizationPageLoaded() {
        AuthorizationPageSteps authorizationPageSteps = new AuthorizationPageSteps();
        mainPageSteps = new MainPageSteps();
        newsPageSteps = new NewsPageSteps();
        controlPanelPageSteps = new ControlPanelPageSteps();
        creatingNewsPageSteps = new CreatingNewsPageSteps();
        try {
            WaitPageSteps.waitPageLoad(LOGIN_TEXT_INPUT_LAYOUT_ID);
            authorizationPageSteps.checkViewIsDisplayed(LOGIN_TEXT_INPUT_LAYOUT_ID);
        } catch (Exception e) {
            authorizationPageSteps.logOut();
        }
        authorizationPageSteps.autorizationValid();
        WaitPageSteps.waitPageLoad(CONTAINER_LIST_NEWS_ID);

    }

    @Test
    @Description("28 - Создание новости")
    public void creatingNewsTest() {

        String uniqueID = UUID.randomUUID().toString();
        String newsTitle = TITLE_NEWS_ID + uniqueID;
        String newsDescription = DESCRIPTION_NEWS_ID + uniqueID;
        mainPageSteps.clickButtonMainMenu();                               //Нажатие на кнопку главного меню
        mainPageSteps.clickButtonNewsMenu();                                //Нажатие кнопки "Новости" в главном меню
        newsPageSteps.clickButtonEditNews();                                //Открытие Control panel
        controlPanelPageSteps.clickButtonAddNews();                         //Открытие страницы добавления новости
        creatingNewsPageSteps.titleSelect(EXAMPLE_CATEGORY);                    //Выбор категории новости
        creatingNewsPageSteps.titleTextInput(newsTitle);                    //Добавление текста категории новости
        creatingNewsPageSteps.newsPublishDateInput();                       //Добавление даты публикации новости
        creatingNewsPageSteps.newsPublishTimeInput();                       //Добавление времени публикации новости
        creatingNewsPageSteps.descriptionTextInput(newsDescription);        //Добавление текста новости
        creatingNewsPageSteps.clickSaveButton();                            //Сохранение добавленной новости
        newsPageSteps.checkExistRecyclerViewItem(newsDescription);
    }

    @Test
    @Description("18 - Разворачивание новости на странице News")
    public void unfoldingNewsPageNews() {

        String uniqueID = UUID.randomUUID().toString();
        String newsTitle = TITLE_NEWS_ID + uniqueID;
        String newsDescription = DESCRIPTION_NEWS_ID + uniqueID;

        mainPageSteps.clickButtonMainMenu();                               //Нажатие на кнопку главного меню
        mainPageSteps.clickButtonNewsMenu();                                //Нажатие кнопки "Новости" в главном меню
        newsPageSteps.clickButtonEditNews();                                //Открытие Control panel
        controlPanelPageSteps.clickButtonAddNews();                         //Открытие страницы добавления новости
        creatingNewsPageSteps.titleSelect(EXAMPLE_CATEGORY);                    //Выбор категории новости
        creatingNewsPageSteps.titleTextInput(newsTitle);                    //Добавление текста категории новости
        creatingNewsPageSteps.newsPublishDateInput();                       //Добавление даты публикации новости
        creatingNewsPageSteps.newsPublishTimeInput();                       //Добавление времени публикации новости
        creatingNewsPageSteps.descriptionTextInput(newsDescription);        //Добавление текста новости
        creatingNewsPageSteps.clickSaveButton();                            //Сохранение добавленной новости
        newsPageSteps.clickToRecyclerViewItem(newsDescription);

    }


    @Test
    @Description("23 - Удаление новостей")
    public void deleteNews() {

        String uniqueID = UUID.randomUUID().toString();
        String newsTitle = TITLE_NEWS_ID + uniqueID;
        String newsDescription = DESCRIPTION_NEWS_ID + uniqueID;

        mainPageSteps.clickButtonMainMenu();                               //Нажатие на кнопку главного меню
        mainPageSteps.clickButtonNewsMenu();                                //Нажатие кнопки "Новости" в главном меню
        newsPageSteps.clickButtonEditNews();                                //Открытие Control panel
        controlPanelPageSteps.clickButtonAddNews();                         //Открытие страницы добавления новости

        creatingNewsPageSteps.titleSelect(EXAMPLE_CATEGORY);                    //Выбор категории новости
        creatingNewsPageSteps.titleTextInput(newsTitle);                    //Добавление текста категории новости
        creatingNewsPageSteps.newsPublishDateInput();                       //Добавление даты публикации новости
        creatingNewsPageSteps.newsPublishTimeInput();                       //Добавление времени публикации новости
        creatingNewsPageSteps.descriptionTextInput(newsDescription);        //Добавление текста новости
        creatingNewsPageSteps.clickSaveButton();

        newsPageSteps.scrollToElement(newsDescription).perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(ViewGroup.class);
            }

            @Override
            public String getDescription() {
                return "perform action on child view with id " + DELETE_NEWS_ITEM_VIEW_ID;
            }

            @Override
            public void perform(final UiController uiController, final View view) {
                View childView = view.findViewById(DELETE_NEWS_ITEM_VIEW_ID);
                if (childView != null) {
                    childView.performClick();
                }
            }
        });
        newsPageSteps.confirmDeleteNews();
    }


    @Test
    @Description("23 - Переход со страницы Main на страницу News через ссылку All News")
    public void goToAllNews() {
        mainPageSteps.clickAllNews();
        WaitPageSteps.waitPageLoad(EDIT_NEWS_MATERIAL_BUTTON_ID);
        newsPageSteps.checkDisplayedView(EDIT_NEWS_MATERIAL_BUTTON_ID);
    }


    @Test
    @Description("31 - Создание новости с незаданной категорией")
    public void creatingNewsWithEmptyCategoryTest() {

        String uniqueID = UUID.randomUUID().toString();
        String newsTitle = TITLE_NEWS_ID + uniqueID;
        String newsDescription = DESCRIPTION_NEWS_ID + uniqueID;

        mainPageSteps.clickButtonMainMenu();                               //Нажатие на кнопку главного меню
        mainPageSteps.clickButtonNewsMenu();                                //Нажатие кнопки "Новости" в главном меню
        newsPageSteps.clickButtonEditNews();                                //Открытие Control panel
        controlPanelPageSteps.clickButtonAddNews();                         //Открытие страницы добавления новости
        creatingNewsPageSteps.titleTextInput(newsTitle);                    //Добавление текста категории новости
        creatingNewsPageSteps.newsPublishDateInput();                       //Добавление даты публикации новости
        creatingNewsPageSteps.newsPublishTimeInput();                       //Добавление времени публикации новости
        creatingNewsPageSteps.descriptionTextInput(newsDescription);        //Добавление текста новости
        creatingNewsPageSteps.clickSaveButton();
        creatingNewsPageSteps.clickCancelButton();
    }

    @Test
    @Description("26 - Сортировка новостей на странице Панель управления")
    public void sortNews() {
        mainPageSteps.clickButtonMainMenu();                               //Нажатие на кнопку главного меню
        mainPageSteps.clickButtonNewsMenu();                                //Нажатие кнопки "Новости" в главном меню
        newsPageSteps.clickFilterNewsButton();
        newsPageSteps.filterNewsByCategory(SALARY);
    }

}
