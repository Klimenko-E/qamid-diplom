package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;
import android.widget.EditText;

import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.espresso.util.TreeIterables;

import org.hamcrest.Matcher;

import java.util.concurrent.TimeoutException;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;


public class authorizationPageSteps {

    public static void loginTextInupt(String login) {
        Allure.step("Ввод логина");
        ViewInteraction loginInput = onView(
                allOf(isDescendantOfA(withId(R.id.login_text_input_layout)),
                        isAssignableFrom(EditText.class)));
        loginInput.perform(replaceText(login), closeSoftKeyboard());
    }

    public static void passwordTextInput(String password) {
        Allure.step("Ввод пароля");
        ViewInteraction passwordInput = onView(
                allOf(isDescendantOfA(withId(R.id.password_text_input_layout)),
                        isAssignableFrom(EditText.class)));
        passwordInput.perform(replaceText(password), closeSoftKeyboard());
    }

    public static void enterButtonLogIn() {
        Allure.step("Нажатие кнопки атвторизации в программе");
        ViewInteraction enterButton = onView(withId(R.id.enter_button));
        enterButton.perform(click());
    }

    public static void logOut() {
        Allure.step("Выход из аккаунта");
        ViewInteraction userButton = onView(withId(R.id.authorization_image_button));
        userButton.check(matches(isDisplayed()));
        userButton.perform(click());

        ViewInteraction logOutButton = onView(withText("Log out"));
        logOutButton.check(matches(isDisplayed()));
        logOutButton.perform(click());
    }

    public static void autorizationValid() {
        Allure.step("Авторизация по валидным данным");
        waitPageLoad(R.id.login_text_input_layout);
        loginTextInupt(Base.loginValid);
        passwordTextInput(Base.passwordValid);
        enterButtonLogIn();

    }

    public static void waitPageLoad(final int viewId) {
        onView(isRoot()).perform(Base.waitDisplayed(viewId, 5_000));
    }

}
