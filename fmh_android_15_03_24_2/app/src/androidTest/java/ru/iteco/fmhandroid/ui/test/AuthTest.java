package ru.iteco.fmhandroid.ui.test;

import static ru.iteco.fmhandroid.ui.constants.IdConstants.LOGIN_TEXT_INPUT_LAYOUT_ID;
import static ru.iteco.fmhandroid.ui.constants.StringConstants.LOGIN_AND_PASSWORD_CANNOT_BE_EMPTY_DESCRIPTION;
import static ru.iteco.fmhandroid.ui.constants.StringConstants.SOMETHING_WENT_WRONG_DESCRIPTION;
import static ru.iteco.fmhandroid.ui.constants.IdConstants.CONTAINER_LIST_NEWS_FRAGMENT_ID;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Epic;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.steps.AuthorizationPageSteps;
import ru.iteco.fmhandroid.ui.steps.Base;
import ru.iteco.fmhandroid.ui.steps.WaitPageSteps;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
@Epic("Тестирование страницы авторизации")
public class AuthTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private AuthorizationPageSteps authorizationPageSteps;

    @Before
    public void authorizationPageLoaded() {
        authorizationPageSteps = new AuthorizationPageSteps();
        try {
            WaitPageSteps.waitPageLoad(LOGIN_TEXT_INPUT_LAYOUT_ID);
            authorizationPageSteps.checkViewIsDisplayed(LOGIN_TEXT_INPUT_LAYOUT_ID);
        } catch (Exception e) {
            authorizationPageSteps.logOut();
        }
    }

    @Test
    @Description("3 - Авторизация в приложении по валидным данным")
    public void authPositiveTest() {
        authorizationPageSteps.autorizationValid();
        WaitPageSteps.waitPageLoad(CONTAINER_LIST_NEWS_FRAGMENT_ID);
        authorizationPageSteps.checkViewIsDisplayed(CONTAINER_LIST_NEWS_FRAGMENT_ID);
        authorizationPageSteps.logOut();
    }

    @Test
    @Description("4 - Авторизация в приложении с валидным логином и пустым полем Пароль")
    public void authPasswordEmptyTest() {
        authorizationPageSteps.loginTextInupt(Base.loginValid);
        authorizationPageSteps.enterButtonLogIn();
        authorizationPageSteps.checkViewWithContentDescriptionIsDisplayed(LOGIN_AND_PASSWORD_CANNOT_BE_EMPTY_DESCRIPTION);
    }

    @Test
    @Description("5 - Авторизация в приложении с пустым полем Логином и валидным паролем")
    public void authLoginEmptyTest() {
        authorizationPageSteps.passwordTextInput(Base.passwordValid);
        authorizationPageSteps.enterButtonLogIn();
        authorizationPageSteps.checkViewWithContentDescriptionIsDisplayed(LOGIN_AND_PASSWORD_CANNOT_BE_EMPTY_DESCRIPTION);
    }

    @Test
    @Description("Авторизация в приложении с невалидным логином и валидным паролем")
    public void authPasswordNotValidTest() {
        authorizationPageSteps.loginTextInupt(Base.loginNotValid);
        authorizationPageSteps.passwordTextInput(Base.passwordValid);
        authorizationPageSteps.enterButtonLogIn();
        authorizationPageSteps.checkViewWithContentDescriptionIsDisplayed(SOMETHING_WENT_WRONG_DESCRIPTION);
    }

    @Test
    @Description("Авторизация в приложении с валидным логином и невалидным паролем")
    public void authLoginNotValidTest() {
        authorizationPageSteps.loginTextInupt(Base.loginValid);
        authorizationPageSteps.passwordTextInput(Base.passwordNotValid);
        authorizationPageSteps.enterButtonLogIn();
        authorizationPageSteps.checkViewWithContentDescriptionIsDisplayed(SOMETHING_WENT_WRONG_DESCRIPTION);
    }
}