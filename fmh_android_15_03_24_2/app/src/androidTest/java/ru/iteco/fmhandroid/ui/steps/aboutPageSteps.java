package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;
import static ru.iteco.fmhandroid.ui.constants.IdConstants.ABOUT_BACK_IMAGE_BUTTON_ID;
import static ru.iteco.fmhandroid.ui.constants.IdConstants.ABOUT_PRIVACY_POLICY_VALUE_TEXT_VIEW_ID;
import static ru.iteco.fmhandroid.ui.constants.IdConstants.ABOUT_TERMS_OF_USE_VALUE_TEXT_VIEW_ID;
import static ru.iteco.fmhandroid.ui.constants.IdConstants.ABOUT_VERSION_TITLE_TEXT_VIEW_ID;
import static ru.iteco.fmhandroid.ui.constants.IdConstants.CONTAINER_LIST_NEWS_FRAGMENT_ID;
import static ru.iteco.fmhandroid.ui.constants.StringConstants.PRIVACY_POLICY_URL;
import static ru.iteco.fmhandroid.ui.constants.StringConstants.TERMS_OF_USE_URL;

import android.app.Instrumentation;
import android.content.Intent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.Intents;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;

import io.qameta.allure.kotlin.Allure;

public class AboutPageSteps {


    public void clickButtonAboutBack() {
        Allure.step("Нажатие кнопки Назад на странице About");
        ViewInteraction buttonAboutBack = onView(withId(ABOUT_BACK_IMAGE_BUTTON_ID));
        buttonAboutBack.perform(click());
        WaitPageSteps.waitPageLoad(CONTAINER_LIST_NEWS_FRAGMENT_ID);
    }

    public void checkViewAboutVersionTitle() {
        Allure.step("Проверка наличия заголовка версии на странице About");
        onView(withId(ABOUT_VERSION_TITLE_TEXT_VIEW_ID)).check(matches(isDisplayed()));
    }

    public void checkPrivacyPolicyView() {
        Allure.step("Проверка наличия и нажатия на ссылку Privacy Policy");

        ViewInteraction clickPrivacyPolicy = onView(
                allOf(withId(ABOUT_PRIVACY_POLICY_VALUE_TEXT_VIEW_ID)));
        clickPrivacyPolicy.check(matches(isDisplayed()));

        Intents.init();
        intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(0, null));
        clickPrivacyPolicy.perform(click());
        intended(hasData(PRIVACY_POLICY_URL));
        intended(hasAction(Intent.ACTION_VIEW));
        Intents.release();
    }


    public void checkTermsOfUseView() {

        Allure.step("Проверка наличия и нажатия на ссылку Terms Of Use");
        ViewInteraction clickPrivacyPolicy = onView(
                allOf(withId(ABOUT_TERMS_OF_USE_VALUE_TEXT_VIEW_ID)));
        clickPrivacyPolicy.check(matches(isDisplayed()));

        Intents.init();
        intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(0, null));
        clickPrivacyPolicy.perform(click());
        intended(hasData(TERMS_OF_USE_URL));
        intended(hasAction(Intent.ACTION_VIEW));
        Intents.release();
    }

    public void pressBackButton() {
        Allure.step("Нажатие аппаратной кнопки Назад");
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).pressBack();
    }
}
