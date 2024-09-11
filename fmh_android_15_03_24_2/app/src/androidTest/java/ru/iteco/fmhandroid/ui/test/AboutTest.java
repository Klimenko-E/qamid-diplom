package ru.iteco.fmhandroid.ui.test;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import androidx.test.uiautomator.UiDevice;
import androidx.test.platform.app.InstrumentationRegistry;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.espresso.intent.Intents;

import android.content.Intent;

import static org.hamcrest.core.AllOf.allOf;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Epic;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.steps.authorizationPageSteps;
import ru.iteco.fmhandroid.ui.steps.mainPageSteps;
import ru.iteco.fmhandroid.ui.steps.aboutPageSteps;

@LargeTest
//@RunWith(AndroidJUnit4.class)
@RunWith(AllureAndroidJUnit4.class)

@Epic("Тестирование страницы About")

public class AboutTest {

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
    @Description("Переход на страницу About")
    public void goToAboutPage() {

        mainPageSteps.clickButtonMainMenu();
        mainPageSteps.clickButtonAboutMenu();
        onView(withId(R.id.about_version_title_text_view)).check(matches(isDisplayed()));
        //Espresso.pressBack();
        aboutPageSteps.clickButtonAboutBack();
        authorizationPageSteps.waitPageLoad(R.id.container_list_news_include_on_fragment_main);
    }

    @Test
    @Description("Открытие ссылки Privacy Politicy на странице About")
    public void goToPrivacyPolicy() {
        mainPageSteps.clickButtonMainMenu();
        mainPageSteps.clickButtonAboutMenu();



        ViewInteraction clickPrivacyPolicy = onView(
                allOf(withId(R.id.about_privacy_policy_value_text_view)));
        clickPrivacyPolicy.check(matches(isDisplayed()));

        Intents.init();
        clickPrivacyPolicy.perform(click());
        intended(hasData("https://vhospice.org/#/privacy-policy/"));
        intended(hasAction(Intent.ACTION_VIEW));
        Intents.release();


        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).pressBack();
        ViewInteraction clickBack = onView(allOf(withId(R.id.about_back_image_button)));
        clickBack.check(matches(isDisplayed()));
        clickBack.perform(click());

    }

    @Test
    @Description("Открытие ссылки Terms of use на странице About")
    public void goToTermsOfUse() {
        mainPageSteps.clickButtonMainMenu();
        mainPageSteps.clickButtonAboutMenu();



        ViewInteraction clickTermsOfUse = onView(
                allOf(withId(R.id.about_terms_of_use_value_text_view)));
        clickTermsOfUse.check(matches(isDisplayed()));

        Intents.init();
        clickTermsOfUse.perform(click());
        intended(hasData("https://vhospice.org/#/terms-of-use"));
        intended(hasAction(Intent.ACTION_VIEW));
        Intents.release();


        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).pressBack();
        ViewInteraction clickBack = onView(allOf(withId(R.id.about_back_image_button)));
        clickBack.check(matches(isDisplayed()));
        clickBack.perform(click());

    }

}
