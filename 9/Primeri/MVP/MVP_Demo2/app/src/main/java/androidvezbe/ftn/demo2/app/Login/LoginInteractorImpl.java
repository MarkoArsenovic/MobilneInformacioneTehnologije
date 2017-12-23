package androidvezbe.ftn.demo2.app.Login;

import android.os.Handler;
import android.text.TextUtils;

public class LoginInteractorImpl implements LoginInteractor {

    @Override
    public void login(final String username, final String password, final OnLoginFinishedListener listener) {
        // Mock login. Delay in 2s
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                if (TextUtils.isEmpty(username) || !username.equals("vezbe")) {
                    listener.onUsernameError();
                    return;
                }
                if (TextUtils.isEmpty(password) || !password.equals("ftn") ) {
                    listener.onPasswordError();
                    return;
                }
                listener.onSuccess();
            }
        }, 2000);
    }
}
