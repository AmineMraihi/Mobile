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
import com.codename1.components.OnOffSwitch;
import com.codename1.io.ConnectionRequest;
import com.codename1.tunisiamall.service.UserService;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

/**
 * Signup UI
 *
 * @author Shai Almog
 */
public class SignUpForm extends BaseForm {

    private ConnectionRequest connectionRequest;
    private String sexe;
    private final Container content;
    private final TextField username, nom, prenom, password, mail;
//    private final Button save;

    public SignUpForm(Resources res) {
        super(new BorderLayout());

        if (!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout) getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }

        getTitleArea().setUIID("Container");
        setUIID("SignIn");

        username = new TextField("", "username", 20, TextField.ANY);

        nom = new TextField("", "nom", 20, TextField.ANY);

        prenom = new TextField("", "prenom", 20, TextField.ANY);

        password = new TextField("", "password", 20, TextField.ANY);
        password.setConstraint(TextField.PASSWORD);

        mail = new TextField("", "e-mail", 20, TextField.ANY);

        Button next = new Button("Sign up");
        Button signIn = new Button("Sign In");
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");

        content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(nom),
                createLineSeparator(),
                new FloatingHint(prenom),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                new FloatingHint(mail),
                createLineSeparator(),
                next,
                createLineSeparator(),
                signIn,
                createLineSeparator()
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);

        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                SignInForm signInForm = new SignInForm(res);
                signInForm.show();
            }
        });
        
        next.requestFocus();
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                MD5 md5 = new MD5(password.getText());
                String md5Password = md5.asHex();

                connectionRequest = new ConnectionRequest() {
                    @Override
                    protected void postResponse() {
                        Dialog.show("Succes", "ajoute avec succes", "ok", null);

                    }
                };

                UserService userService = new UserService();
                userService.adduser(username.getText(), nom.getText(), prenom.getText(),
                        md5Password, mail.getText());

            }
        });
    }

}
