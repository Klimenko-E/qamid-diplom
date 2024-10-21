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

import android.app.Instrumentation;
import android.content.Intent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.Intents;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class AboutPageSteps {

    private static final int ABOUT_VERSION_TITLE_TEXT_VIEW_ID = R.id.about_version_title_text_view;
    private static final int ABOUT_PRIVACY_POLICY_VALUE_TEXT_VIEW_ID = R.id.about_privacy_policy_value_text_view;
    private static final int ABOUT_TERMS_OF_USE_VALUE_TEXT_VIEW_ID = R.id.about_terms_of_use_value_text_view;
    private static final int ABOUT_BACK_IMAGE_BUTTON_ID = R.id.about_back_image_button;

    private static final String PRIVACY_POLICY_URL = "https://vhospice.org/#/privacy-policy/";
    private static final String TERMS_OF_USE_URL = "https://vhospice.org/#/terms-of-use";
    public static final int CONTAINER_LIST_NEWS_FRAGMENT_ID = R.id.container_list_news_include_on_fragment_main;

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
