
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.tunisiamall.service;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.uikit.entities.User;
import com.codename1.ui.Dialog;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Amine
 */
public class UserService {

    private ConnectionRequest connectionRequest;

    public void adduser(String username, String nom, String prenom, String password,
            String mail) {
        connectionRequest = new ConnectionRequest() {
            @Override
            protected void postResponse() {
                Dialog.show("Succes", "ajoute avec succes", "ok", null);

            }
        };

        connectionRequest.setUrl("http://localhost/crud/insert.php?"
                + "username=" + username
                + "&name=" + nom
                + "&prenom=" + prenom
                + "&password=" + password
                + "&mail=" + mail
                
        );
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }

//    public void findUserbyid(int id) {
//        connectionRequest = new ConnectionRequest() {
//            List<User> users = new ArrayList<>();
//
//            @Override
//            protected void readResponse(InputStream input) throws IOException {
//                JSONParser json = new JSONParser();
//                Reader reader = new InputStreamReader(input, "UTF-8");
//                Map<String, Object> data = json.parseJSON(reader);
//                List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("root");
//
//        }
//    }
    public void updateuser(int id_user, String username, String nom, String prenom,
            String password, String mail, String imagename, String imagepath) {

        connectionRequest = new ConnectionRequest() {
            @Override
            protected void postResponse() {
                Dialog.show("Succes", "modification avec succes", "ok", null);

            }
        };

        connectionRequest.setUrl("http://localhost/crud/UpdateProfile.php?"
                + "username=" + username
                + "&name=" + nom
                + "&prenom=" + prenom
                + "&password=" + password
                + "&mail=" + mail
                + "&id_user=" + id_user
                + "&imagename=" + imagename
                + "&imagepath=" + imagepath
        );
        NetworkManager.getInstance().addToQueue(connectionRequest);

    }
}
