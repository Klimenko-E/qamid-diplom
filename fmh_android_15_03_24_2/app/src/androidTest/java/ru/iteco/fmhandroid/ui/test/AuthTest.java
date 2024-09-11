package ru.iteco.fmhandroid.ui.test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import androidx.test.ext.junit.runners.AndroidJUnit4;
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
import ru.iteco.fmhandroid.ui.steps.Base;
import ru.iteco.fmhandroid.ui.steps.authorizationPageSteps;

@LargeTest
//@RunWith(AndroidJUnit4.class)
@RunWith(AllureAndroidJUnit4.class)

@Epic("Тестирование страницы авторизации")
public class AuthTest {

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
    }

    @Test
    @Description("3 - Авторизация в приложении по валидным данным")
    public void authPositiveTest() {
        authorizationPageSteps.autorizationValid();
        authorizationPageSteps.waitPageLoad(R.id.container_list_news_include_on_fragment_main);
        ViewInteraction textView = onView(withId(R.id.container_list_news_include_on_fragment_main));
        textView.check(matches(isDisplayed()));
        authorizationPageSteps.logOut();
    }

    @Test
    @Description("4 - Авторизация в приложении с валидным логином и пустым полем Пароль")
    public void authPasswordEmptyTest() {
        authorizationPageSteps.loginTextInupt(Base.loginValid);
        authorizationPageSteps.enterButtonLogIn();
        onView(allOf(withContentDescription("Login and password cannot be empty"), isDisplayed()));
    }

    @Test
    @Description("5 - Авторизация в приложении с пыстым полем Логином и валидным паролем")
    public void authLoginEmptyTest() {
        authorizationPageSteps.passwordTextInput(Base.passwordValid);
        authorizationPageSteps.enterButtonLogIn();
        onView(allOf(withContentDescription("Login and password cannot be empty"), isDisplayed()));

    }

    @Test
    @Description("Авторизация в приложении с невалидным логином и валидным паролем")
    public void authPasswordNotValidTest() {
        authorizationPageSteps.loginTextInupt(Base.loginNotValid);
        authorizationPageSteps.passwordTextInput(Base.passwordValid);
        authorizationPageSteps.enterButtonLogIn();
        onView(allOf(withContentDescription("Something went wrong. Try again later"), isDisplayed()));
    }

    @Test
    @Description("Авторизация в приложении с валидным логином и валидным паролем")
    public void authLoginNotValidTest() {
        authorizationPageSteps.loginTextInupt(Base.loginValid);
        authorizationPageSteps.passwordTextInput(Base.passwordNotValid);
        authorizationPageSteps.enterButtonLogIn();
        onView(allOf(withContentDescription("Something went wrong. Try again later"), isDisplayed()));
    }
}
