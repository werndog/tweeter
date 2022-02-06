package edu.byu.cs.tweeter.client.model.service.backgroundTask;

import android.os.Bundle;
import android.os.Handler;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.util.Pair;

/**
 * Background task that logs in a user (i.e., starts a session).
 */
public class LoginTask extends BackgroundTask {

    private static final String LOG_TAG = "LoginTask";
    public static final String USER_KEY = "user";
    public static final String AUTH_TOKEN_KEY = "auth-token";

    private User loggedInUser;
    private AuthToken authToken;

    /**
     * The user's username (or "alias" or "handle"). E.g., "@susan".
     */
    private String username;
    /**
     * The user's password.
     */
    private String password;

    public LoginTask(String username, String password, Handler messageHandler) {
        super(messageHandler);
        this.username = username;
        this.password = password;
    }

    @Override
    protected void runTask() {
        Pair<User, AuthToken> loginResult = doLogin();

        loggedInUser = loginResult.getFirst();
        authToken = loginResult.getSecond();
    }

    @Override
    protected void loadMessageBundle(Bundle msgBundle) {
        msgBundle.putSerializable(USER_KEY, loggedInUser);
        msgBundle.putSerializable(AUTH_TOKEN_KEY, authToken);
    }


    private Pair<User, AuthToken> doLogin() {
        User loggedInUser = getFakeData().getFirstUser();
        AuthToken authToken = getFakeData().getAuthToken();
        return new Pair<>(loggedInUser, authToken);
    }
}
