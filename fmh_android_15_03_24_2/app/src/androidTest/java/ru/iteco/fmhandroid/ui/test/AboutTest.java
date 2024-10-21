package ru.iteco.fmhandroid.ui.test;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Epic;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.steps.AboutPageSteps;
import ru.iteco.fmhandroid.ui.steps.AuthorizationPageSteps;
import ru.iteco.fmhandroid.ui.steps.MainPageSteps;
import ru.iteco.fmhandroid.ui.steps.WaitPageSteps;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
@Epic("Тестирование страницы About")
public class AboutTest {

    private static final int LOGIN_TEXT_INPUT_LAYOUT_ID = R.id.login_text_input_layout;
    private static final int CONTAINER_LIST_NEWS_INCLUDE_ON_FRAGMENT_MAIN_ID = R.id.container_list_news_include_on_fragment_main;

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private MainPageSteps mainPageSteps;
    private AboutPageSteps aboutPageSteps;

    @Before
    public void authorizationPageLoaded() {
        AuthorizationPageSteps authorizationPageSteps = new AuthorizationPageSteps();
        mainPageSteps = new MainPageSteps();
        aboutPageSteps = new AboutPageSteps();
        try {
            WaitPageSteps.waitPageLoad(LOGIN_TEXT_INPUT_LAYOUT_ID);
            authorizationPageSteps.checkViewIsDisplayed(LOGIN_TEXT_INPUT_LAYOUT_ID);
        } catch (Exception e) {
            authorizationPageSteps.logOut();
        }
        authorizationPageSteps.autorizationValid();
        WaitPageSteps.waitPageLoad(CONTAINER_LIST_NEWS_INCLUDE_ON_FRAGMENT_MAIN_ID);
    }

    @Test
    @Description("Переход на страницу About")
    public void goToAboutPage() {
        mainPageSteps.clickButtonMainMenu();
        mainPageSteps.clickButtonAboutMenu();
        aboutPageSteps.checkViewAboutVersionTitle();
        aboutPageSteps.clickButtonAboutBack();
    }

    @Test
    @Description("Открытие ссылки Privacy Policy на странице About")
    public void goToPrivacyPolicy() {
        mainPageSteps.clickButtonMainMenu();
        mainPageSteps.clickButtonAboutMenu();
        aboutPageSteps.checkPrivacyPolicyView();
        aboutPageSteps.pressBackButton();
    }

    @Test
    @Description("Открытие ссылки Terms of Use на странице About")
    public void goToTermsOfUse() {
        mainPageSteps.clickButtonMainMenu();
        mainPageSteps.clickButtonAboutMenu();
        aboutPageSteps.checkTermsOfUseView();
        aboutPageSteps.pressBackButton();
    }
}
