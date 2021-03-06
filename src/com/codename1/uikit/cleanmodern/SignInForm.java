/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.components.FloatingHint;
import com.codename1.components.ToastBar;
import com.codename1.io.AccessToken;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.io.Preferences;
import com.codename1.social.FacebookConnect;
import com.codename1.social.Login;
import com.codename1.social.LoginCallback;
import com.codename1.tunisiamall.service.UserService;
import com.codename1.uikit.entities.User;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.mindrot.BCrypt;

/**
 * Sign in UI
 *
 * @author Shai Almog
 */
public class SignInForm extends BaseForm {

    private ConnectionRequest connectionRequest;
    String sexe;
    List<User> u = new ArrayList<>();
    boolean test = false;
    Container ctn;
    public static User staticUser;

    private String fullName;
    private String uniqueId;
    private String imageURL;
    private String mail;
    Resources theme;

    public SignInForm(Resources res) {
        super(new BorderLayout());

        if (!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout) getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        setUIID("SignIn");

        add(BorderLayout.NORTH, new Label(res.getImage("Logo.png"), "LogoLabel"));

        TextField username = new TextField("", "Username", 20, TextField.ANY);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);

        Button signIn = new Button("Sign In");
        Button signUp = new Button("sign up");

//        started here facebook api
        Button loginWithFacebook = new Button("Signin with Facebook");
        loginWithFacebook.addActionListener((e) -> {
            String tokenPrefix = "facebook";
            Login fb = FacebookConnect.getInstance();
            fb.setClientId("139609243415619");
            fb.setRedirectURI("http://127.0.0.1/TestUser/web/images/amine/goodbadugly.jpg");
            fb.setClientSecret("003b5c9a295dd9192c8b3df7af15a1bd");
            theme = res;
            doLogin(fb, new FacebookData(), false);

        });

//        ended here
        signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println("testing signup");
                SignUpForm signUpForm = new SignUpForm(res);
                signUpForm.show();
            }
        });
        signUp.setUIID("Link");
        Label doneHaveAnAccount = new Label("Don't have an account?");

        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {


                UserService service = new UserService();
                if (service.verification(username.getText(), password.getText())) {
                    NewsfeedForm w = new NewsfeedForm(res);
                    w.show();
                }

            }
        });

        Container content = BoxLayout.encloseY(
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                signIn,
                FlowLayout.encloseCenter(doneHaveAnAccount, signUp),
                loginWithFacebook
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
//        signIn.requestFocus();
//        signIn.addActionListener(e -> new NewsfeedForm(res).show());

    }

    public void doLogin(Login lg, UserData data, boolean forceLogin) {
        String tokenPrefix = null;
        if (!forceLogin) {
            if (lg.isUserLoggedIn()) {
                // showContactsForm(data);
                System.out.println("done1");
                return;
            }

            // if the user already logged in previously and we have a token
            String t = Preferences.get(tokenPrefix + "token", (String) null);
            if (t != null) {
                // we check the expiration of the token which we previously stored as System time
                long tokenExpires = Preferences.get(tokenPrefix + "tokenExpires", (long) -1);
//            if(tokenExpires < 0 || tokenExpires > System.currentTimeMillis()) {
//                // we are still logged in
//                // showContactsForm(data);
//                System.out.println("done2");
//                return;
//            }
            }

        }

        lg.setCallback(new LoginCallback() {
            @Override
            public void loginFailed(String errorMessage) {
                Dialog.show("Error Logging In", "There was an error logging in: " + errorMessage, "OK", null);
            }

            @Override
            public void loginSuccessful() {
                // when login is successful we fetch the full data
                data.fetchData(lg.getAccessToken().getToken(), () -> {
                    // we store the values of result into local variables
                    uniqueId = data.getId();
                    fullName = data.getName();
                    imageURL = data.getImage();

                    // we then store the data into local cached storage so they will be around when we run the app next time
                    Preferences.set("fullName", fullName);
                    Preferences.set("uniqueId", uniqueId);
                    Preferences.set("imageURL", imageURL);

                    Preferences.set(tokenPrefix + "token", lg.getAccessToken().getToken());

                    // token expiration is in seconds from the current time, we convert it to a System.currentTimeMillis value so we can
                    // reference it in the future to check expiration
                 /////   Preferences.set(tokenPrefix + "tokenExpires", tokenExpirationInMillis(lg.getAccessToken()));
                    // showContactsForm(data);
                    System.out.println("done3");
                    System.out.println(fullName);
                    System.out.println(imageURL);

                    staticUser = new User();
                    staticUser.setNom(fullName);
                    staticUser.setPath(imageURL);

                    System.out.println("facebook id:  " + uniqueId);

                    UserService userService = new UserService();

                    User returnedUser = userService.getUserViaFacebookid(uniqueId);

                    if (returnedUser != null) {
                        staticUser = new User();
                        staticUser.setIdUser(returnedUser.getIdUser());
                        staticUser.setUsername(returnedUser.getUsername());
                        staticUser.setNom(returnedUser.getNom());
                        staticUser.setPrenom(returnedUser.getPrenom());
                        staticUser.setEmail(returnedUser.getEmail());
                        staticUser.setPassword(returnedUser.getPassword());
                        staticUser.setPath(returnedUser.getPath());
                    } else {
                        userService.addUserViaFacebook(
                                "",
                                fullName,
                                "",
                                "",
                                "",
                                "",
                                "",
                                uniqueId
                        );

                        User returnedUser1 = userService.getUserViaFacebookid(uniqueId);
                        staticUser = new User();
                        staticUser.setIdUser(returnedUser1.getIdUser());
                        staticUser.setUsername(returnedUser1.getUsername());
                        staticUser.setNom(returnedUser1.getNom());
                        staticUser.setPrenom(returnedUser1.getPrenom());
                        staticUser.setEmail(returnedUser1.getEmail());
                        staticUser.setPassword(returnedUser1.getPassword());
                        staticUser.setPath(returnedUser1.getPath());
                    }

                    NewsfeedForm newsfeedForm = new NewsfeedForm(theme);
                    newsfeedForm.show();
                });
            }
        });
        lg.doLogin();

    }

    long tokenExpirationInMillis(AccessToken token) {
        String expires = token.getExpires();
        if (expires != null && expires.length() > 0) {
            try {
                // when it will expire in seconds
                long l = (long) (Float.parseFloat(expires) * 1000);
                return System.currentTimeMillis() + l;
            } catch (NumberFormatException err) {
                // ignore invalid input
            }
        }
        return -1;
    }

}
