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
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.uikit.entities.User;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
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

                        MD5 md5 = new MD5(password.getText());
                        String md5Password = md5.asHex();

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
                                    (String) obj.get("prenom")
                            ));
                        }

                        for (User user : u) {
                            if (username.getText().equals(user.getUsername())
                                    && md5Password.equals(user.getPassword())) {
                                System.out.println("it works!");
                                staticUser = new User();
                                staticUser.setIdUser(user.getIdUser());
                                staticUser.setUsername(user.getUsername());
                                staticUser.setNom(user.getNom());
                                staticUser.setPrenom(user.getPrenom());
                                staticUser.setEmail(user.getEmail());
                                staticUser.setPassword(user.getPassword());

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
                connectionRequest.setUrl("http://localhost/crud/login.php");
                NetworkManager.getInstance().addToQueue(connectionRequest);
            }
        });

        Container content = BoxLayout.encloseY(
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                signIn,
                FlowLayout.encloseCenter(doneHaveAnAccount, signUp)
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
//        signIn.requestFocus();
//        signIn.addActionListener(e -> new NewsfeedForm(res).show());

    }

}
