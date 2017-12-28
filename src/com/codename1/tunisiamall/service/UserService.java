
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
import com.codename1.uikit.cleanmodern.MD5;
import static com.codename1.uikit.cleanmodern.SignInForm.staticUser;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.mindrot.BCrypt;

/**
 *
 * @author Amine
 */
public class UserService {

    private ConnectionRequest connectionRequest;
    boolean test = false;

    public void adduser(String username, String nom, String prenom, String password,
            String mail, String imagename, String imagepath) {
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
                + "&email=" + mail
                + "&imagename=" + imagename
                + "&imagepath=" + imagepath
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

    public boolean verifyUsername(String username) {
        List<Map<String, Object>> all = new ArrayList<>();
        ConnectionRequest request = new ConnectionRequest("http://localhost/crud/seletUsername.php?"
                + "username=" + username);
        NetworkManager.getInstance().addToQueueAndWait(request);
        Map<String, Object> result = null;
        boolean exist = false;

        try {
            result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
            List<Map<String, Object>> response = (List<Map<String, Object>>) result.get("root");

            for (Map<String, Object> obj : response) {
                exist = true;
            }

        } catch (IOException ex) {
            System.out.println("EXCEPTION : " + ex);

        }

        return exist;
    }

    public void deleteAccount(int id) {
        List<Map<String, Object>> all = new ArrayList<>();
        ConnectionRequest request = new ConnectionRequest("http://localhost/crud/deleteAcccount.php?"
                + "id=" + id);
        NetworkManager.getInstance().addToQueueAndWait(request);
        Map<String, Object> result = null;
        NetworkManager.getInstance().addToQueue(request);

    }

    public boolean verifyUsernameforupdate(String username, int id) {
        List<Map<String, Object>> all = new ArrayList<>();
        ConnectionRequest request = new ConnectionRequest("http://localhost/crud/seletUsernameforUpdate.php?"
                + "username=" + username
                + "&id=" + id
        );
        NetworkManager.getInstance().addToQueueAndWait(request);
        Map<String, Object> result = null;
        boolean exist = false;

        try {
            result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
            List<Map<String, Object>> response = (List<Map<String, Object>>) result.get("root");

            for (Map<String, Object> obj : response) {
                exist = true;
            }

        } catch (IOException ex) {
            System.out.println("EXCEPTION : " + ex);

        }

        return exist;
    }

    public boolean verification(String username, String password) {
        List<User> u = new ArrayList<>();

        connectionRequest = new ConnectionRequest("http://localhost/crud/login.php");
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
        Map<String, Object> result = null;

        MD5 md5 = new MD5(password);
        String md5Password = md5.asHex();

        try {
            result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(connectionRequest.getResponseData()), "UTF-8"));
            List<Map<String, Object>> response = (List<Map<String, Object>>) result.get("root");

            for (Map<String, Object> obj : response) {
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
                if (username.equals(user.getUsername())
                        && BCrypt.checkpw(password, user.getPassword())) {

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
                    return true;
                }
            }
        } catch (IOException ex) {
            System.out.println("EXCEPTION : " + ex);

        }
        System.out.println("wrong ");
        return false;

    }

    public boolean verifymail(String mail) {
        List<Map<String, Object>> all = new ArrayList<>();
        ConnectionRequest request = new ConnectionRequest("http://localhost/crud/selectMail.php?"
                + "mail=" + mail);
        NetworkManager.getInstance().addToQueueAndWait(request);
        Map<String, Object> result = null;
        boolean exist = false;

        try {
            result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
            List<Map<String, Object>> response = (List<Map<String, Object>>) result.get("root");

            for (Map<String, Object> obj : response) {
                exist = true;
            }

        } catch (IOException ex) {
            System.out.println("EXCEPTION : " + ex);

        }

        return exist;
    }

    public boolean verifyMailforupdate(String mail, int id) {
        List<Map<String, Object>> all = new ArrayList<>();
        ConnectionRequest request = new ConnectionRequest("http://localhost/crud/seletMailforUpdate.php?"
                + "mail=" + mail
                + "&id=" + id
        );
        NetworkManager.getInstance().addToQueueAndWait(request);
        Map<String, Object> result = null;
        boolean exist = false;

        try {
            result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
            List<Map<String, Object>> response = (List<Map<String, Object>>) result.get("root");

            for (Map<String, Object> obj : response) {
                exist = true;
            }

        } catch (IOException ex) {
            System.out.println("EXCEPTION : " + ex);

        }

        return exist;
    }

    public void addUserViaFacebook(String username, String nom, String prenom, String password,
            String mail, String imagename, String imagepath, String facebook_id) {

        connectionRequest = new ConnectionRequest() {
            @Override
            protected void postResponse() {

            }
        };

        connectionRequest.setUrl("http://localhost/crud/insertviafb.php?"
                + "username=" + username
                + "&name=" + nom
                + "&prenom=" + prenom
                + "&password=" + password
                + "&mail=" + mail
                + "&imagename=" + imagename
                + "&imagepath=" + imagepath
                + "&facebook_id=" + facebook_id
        );
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }

    public User getUserViaFacebookid(String facebook_id) {

        List<Map<String, Object>> all = new ArrayList<>();
        ConnectionRequest request = new ConnectionRequest("http://localhost/crud/selectUserViaFacebookid.php?"
                + "facebook_id=" + facebook_id);
        NetworkManager.getInstance().addToQueueAndWait(request);
        Map<String, Object> result = null;
        boolean exist = false;

        try {
            result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
            List<Map<String, Object>> response = (List<Map<String, Object>>) result.get("root");

            for (Map<String, Object> obj : response) {
                User u = new User(
                        Integer.parseInt(obj.get("id_user").toString()),
                        (String) obj.get("username"),
                        (String) obj.get("password"),
                        (String) obj.get("email"),
                        (String) obj.get("nom"),
                        (String) obj.get("prenom"),
                        (String) obj.get("path"),
                        (String) obj.get("facebook_id"));
                return u;

            }

        } catch (IOException ex) {
            System.out.println("EXCEPTION : " + ex);

        }
        return null;
    }

}
