package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import static ru.iteco.fmhandroid.ui.constants.IdConstants.AUTHORIZATION_IMAGE_BUTTON_ID;
import static ru.iteco.fmhandroid.ui.constants.IdConstants.ENTER_BUTTON_ID;
import static ru.iteco.fmhandroid.ui.constants.IdConstants.LOGIN_TEXT_INPUT_LAYOUT_ID;
import static ru.iteco.fmhandroid.ui.constants.IdConstants.PASSWORD_TEXT_INPUT_LAYOUT_ID;

import android.widget.EditText;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class AuthorizationPageSteps {


    public void loginTextInupt(String login) {
        Allure.step("Ввод логина");
        ViewInteraction loginInput = onView(
                allOf(isDescendantOfA(withId(LOGIN_TEXT_INPUT_LAYOUT_ID)),
                        isAssignableFrom(EditText.class)));
        loginInput.perform(replaceText(login), closeSoftKeyboard());
    }

    public void passwordTextInput(String password) {
        Allure.step("Ввод пароля");
        ViewInteraction passwordInput = onView(
                allOf(isDescendantOfA(withId(PASSWORD_TEXT_INPUT_LAYOUT_ID)),
                        isAssignableFrom(EditText.class)));
        passwordInput.perform(replaceText(password), closeSoftKeyboard());
    }

    public void enterButtonLogIn() {
        Allure.step("Нажатие кнопки авторизации в программе");
        ViewInteraction enterButton = onView(withId(ENTER_BUTTON_ID));
        enterButton.perform(click());
    }

    public void logOut() {
        Allure.step("Выход из аккаунта");
        ViewInteraction userButton = onView(withId(AUTHORIZATION_IMAGE_BUTTON_ID));
        userButton.check(matches(isDisplayed()));
        userButton.perform(click());

        ViewInteraction logOutButton = onView(withText(R.string.log_out));
        logOutButton.check(matches(isDisplayed()));
        logOutButton.perform(click());
    }

    public void autorizationValid() {
        Allure.step("Авторизация по валидным данным");
        WaitPageSteps.waitPageLoad(LOGIN_TEXT_INPUT_LAYOUT_ID);
        loginTextInupt(Base.loginValid);
        passwordTextInput(Base.passwordValid);
        enterButtonLogIn();
    }


    public void checkViewIsDisplayed(int viewId) {
        Allure.step("Проверка отображения элемента с ID: " + viewId);
        ViewInteraction view = onView(withId(viewId));
        view.check(matches(isDisplayed()));
    }

    public void checkViewWithContentDescriptionIsDisplayed(String contentDescription) {
        Allure.step("Проверка отображения элемента с описанием: " + contentDescription);
        onView(allOf(withContentDescription(contentDescription), isDisplayed()));    }
}