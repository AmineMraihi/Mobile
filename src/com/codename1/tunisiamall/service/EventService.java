/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.tunisiamall.service;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.uikit.entities.Evenement;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Amine
 */
public class EventService {

    private ConnectionRequest connectionRequest;

    public void showEvents() {

    }

    public void addReview(int id_user, int id_event, String reviewValue) {
        connectionRequest = new ConnectionRequest() {
            @Override
            protected void postResponse() {
                Dialog.show("Succes", "ajoute avec succesyour review has been submitted", "ok", null);

            }
        };

        connectionRequest.setUrl("http://localhost/crud/addreview.php?"
                + "id_user=" + id_user
                + "&id_event=" + id_event
                + "&reviewvalue=" + reviewValue
        );
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }

    public int returnRate(int id_user, int id_event) {
        ConnectionRequest request = new ConnectionRequest("http://localhost/crud/returnRate.php?"
                + "id_user=" + id_user
                + "&id_event=" + id_event
        );
        int x = 0;
        NetworkManager.getInstance().addToQueueAndWait(request);
        Map<String, Object> result = null;
        try {
            result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
            List<Map<String, Object>> response = (List<Map<String, Object>>) result.get("root");

            for (Map<String, Object> obj : response) {
                x = Integer.parseInt((String) obj.get("rate"));
                return x;
            }

        } catch (IOException ex) {
            System.out.println("EXCEPTION : " + ex);

        }
        return x;
    }

    public List<Evenement> getAllEvents() {
        List<Evenement> listevenement = new ArrayList<>();
        List<Map<String, Object>> all = new ArrayList<>();
        ConnectionRequest request = new ConnectionRequest("http://localhost/crud/select_events.php");
        NetworkManager.getInstance().addToQueueAndWait(request);
        Map<String, Object> result = null;

        try {
            result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
            List<Map<String, Object>> response = (List<Map<String, Object>>) result.get("root");

            for (Map<String, Object> obj : response) {
                listevenement.add(
                        new Evenement(
                                Integer.valueOf((String) obj.get("id_evenement")),
                                (String) obj.get("nom"),
                                (String) obj.get("description"),
                                (String) obj.get("path")
                        ));
            }

        } catch (IOException ex) {
            System.out.println("EXCEPTION : " + ex);

        }
        return listevenement;

    }

}
