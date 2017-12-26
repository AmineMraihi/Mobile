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
import com.codename1.io.AccessToken;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.io.Preferences;
import com.codename1.social.FacebookConnect;
import com.codename1.social.Login;
import com.codename1.social.LoginCallback;
import com.codename1.uikit.entities.User;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
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

//            String clientId = "139609243415619";
//            String redirectURI = "http://127.0.0.1/TestUser/web/images/amine/goodbadugly.jpg";
//            String clientSecret = "003b5c9a295dd9192c8b3df7af15a1bd";
//            Login fb = FacebookConnect.getInstance();
//            fb.setClientId(clientId);
//            fb.setRedirectURI(redirectURI);
//            fb.setClientSecret(clientSecret);
//            //Sets a LoginCallback listener
////            fb.setCallback();
//                //trigger the login if not already logged in
//                if (!fb.isUserLoggedIn()) {
//                fb.doLogin();
//            } else {
//                //get the token and now you can query the facebook API
//                String token = fb.getAccessToken().getToken();
//                
//            }
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
                connectionRequest = new ConnectionRequest() {

                    @Override
                    protected void readResponse(InputStream input) throws IOException {

                   //    MD5 md5 = new MD5(password.getText());
                 //     String md5Password = md5.asHex();

                        JSONParser json = new JSONParser();
                        Reader reader = new InputStreamReader(input, "UTF-8");
                        Map<String, Object> data = json.parseJSON(reader);
                        List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("root");

                        for (Map<String, Object> obj : content) {
                            u.add(new User(
                                    Integer.parseInt(obj.get("id_user").toString()),
                                    (String) obj.get("username"),
                                    (String) obj.get("password"),
                                    (String) obj.get("email"),
                                    (String) obj.get("nom"),
                                    (String) obj.get("prenom"),
                                    (String) obj.get("path")
                            ));
                        }

                        for (User user : u) {
                            if ( username.getText().equals(user.getUsername())
                                    && BCrypt.checkpw(password.getText(), user.getPassword()) )
                                    /*md5Password.equals(user.getPassword())*/ 
                            {
                                System.out.println("it works!");
                                staticUser = new User();
                                staticUser.setIdUser(user.getIdUser());
                                staticUser.setUsername(user.getUsername());
                                staticUser.setNom(user.getNom());
                                staticUser.setPrenom(user.getPrenom());
                                staticUser.setEmail(user.getEmail());
                                staticUser.setPassword(user.getPassword());
                                staticUser.setPath(user.getPath());

                                test = true;
                                NewsfeedForm w = new NewsfeedForm(res);
                                w.show();
                            }
                        }
                        if (!test) {
                            System.out.println("wrong credentials ");
                        }
                    }

                };
                connectionRequest.setUrl("http://localhost:8082/crud/login.php");
                NetworkManager.getInstance().addToQueue(connectionRequest);
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
                    Preferences.set(tokenPrefix + "tokenExpires", tokenExpirationInMillis(lg.getAccessToken()));
                    // showContactsForm(data);
                    System.out.println("done3");
                    System.out.println(fullName);
                    System.out.println(imageURL);

                    User m = new User(fullName, uniqueId,imageURL);
                    staticUser = new User();
                                staticUser.setNom(fullName);
                                staticUser.setPath(imageURL);
                    
                    NewsfeedForm newsfeedForm=new NewsfeedForm(theme);
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
