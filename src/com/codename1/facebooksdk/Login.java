/*
 * Copyright (c) 2012, Codename One and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Codename One designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *  
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 * 
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * Please contact Codename One through http://www.codenameone.com/ if you 
 * need additional information or have any questions.
 */
package com.codename1.facebooksdk;

import com.codename1.facebook.FaceBookAccess;
import com.codename1.io.AccessToken;
import com.codename1.io.NetworkEvent;
import com.codename1.io.Oauth2;
import com.codename1.io.Storage;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.Resources;
import com.codename1.facebooksdk.FBConnect;
import java.io.IOException;
import java.util.Observer;

/**
 *
 * @author Chen
 */
public class Login extends Form {

    private Form main;
    public static String TOKEN;

    public Login(Form f) {
        super("Facebook Login");
        this.main = f;
        setLayout(new LayeredLayout());
        try {
            FBConnect.setTheme(Resources.openLayered("/themeFB"));
        } catch (IOException ex) {
            System.out.println(ex);
        }
        Button login = new Button(FBConnect
                .getTheme()
                .getImage("SignInFacebook.png_veryHigh.png"));
        login.addActionListener((ActionListener) (ActionEvent evt) -> {
            signIn(main);
        });
        login.setUIID("CenterLabel");
        Form last = Display.getInstance().getCurrent();
        getToolbar().setBackCommand("", e -> last.show());
        addComponent(login);
    }

    private static void signIn(final Form main) {
        FaceBookAccess.setClientId("139609243415619");
        FaceBookAccess.setClientSecret("003b5c9a295dd9192c8b3df7af15a1bd");
        FaceBookAccess.setRedirectURI("http://127.0.0.1/TestUser/web/images/amine/goodbadugly.jpg");
        FaceBookAccess.setPermissions(new String[]{"publish_actions"});

        FaceBookAccess.getInstance().showAuthentication((ActionListener) (ActionEvent evt) -> {
            if (evt.getSource() instanceof AccessToken) {
                AccessToken token = (AccessToken) evt.getSource();
                String expires = Oauth2.getExpires();
                TOKEN = token.getToken();
                System.out.println("token from login: "+TOKEN);
                System.out.println("received a token " + token + " which expires on " + expires);
                //store token for future queries.
                Storage.getInstance().writeObject("token", token);
                if (main != null) {
                    main.showBack();
                    Observer o = (Observer) main;
                    o.update(null, null);
                }
            } else {
//                Exception err = (Exception) evt.getSource();
//                err.printStackTrace();

                Dialog.show("Error", "An error occurred while logging in: " + evt.getSource(), "OK", null);
            }
        });
    }

    public static boolean firstLogin() {
        //return Storage.getInstance().readObject("token") == null;
        return true;
    }

    public static void login(final Form form) {
        if (firstLogin()) {
            Login logForm = new Login(form);
            logForm.show();
        } else {
            //token exists no need to authenticate
            TOKEN = (String) Storage.getInstance().readObject("token");
            FaceBookAccess.setToken(TOKEN);
            //in case token has expired re-authenticate
            FaceBookAccess.getInstance().addResponseCodeListener((ActionListener) (ActionEvent evt) -> {
                NetworkEvent ne = (NetworkEvent) evt;
                int code = ne.getResponseCode();
                //token has expired
                if (code == 400) {
                    signIn(form);
                }
            });
        }
    }
}
