/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.io.ConnectionRequest;
import com.codename1.ui.Container;
import com.codename1.ui.TextField;
import com.codename1.components.FloatingHint;
import com.codename1.components.OnOffSwitch;
import com.codename1.io.ConnectionRequest;
import com.codename1.messaging.Message;
import com.codename1.tunisiamall.service.BoutiqueService;
//import com.codename1.tunisiamall.service.MailService;
import com.codename1.tunisiamall.service.ReclamationService;
import com.codename1.tunisiamall.service.UserService;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.entities.User;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import util.Statics;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.mail.MessagingException;

/**
 *
 * @author ASUS
 */
public class ReclamatioForm extends BaseForm{
    private ConnectionRequest connectionRequest;
    private String sexe;
    private final Container content;
    private final TextField type, description, boutique;
   
   
 

    public ReclamatioForm(Resources res) throws IOException {
        super(new BorderLayout());
        if (!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout) getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
                ReclamationService reclamationservice=new ReclamationService();
                BoutiqueService boutiqueService=new BoutiqueService();
                UserService userService=new UserService();
       /* getTitleArea().setUIID("jassem");*/
        setUIID("jassem");
         type = new TextField("", "type", 20, TextField.ANY);


        description = new TextField("", "description", 20, TextField.ANY);


        boutique = new TextField("", "boutique", 20, TextField.ANY);
        ComboBox combo=  new ComboBox();
        for (String s:boutiqueService.findallboutique())
        {
            combo.addItem(s);
        }
        Button send = new Button("Send");
        Button historique = new Button("Historique");
 
        content = BoxLayout.encloseY(
                new Label("Reclamation", "LogoLabel"),
                new FloatingHint(type),
                createLineSeparator(),
                new FloatingHint(description),
                createLineSeparator(),
                /*new FloatingHint(boutique),
                createLineSeparator(),*/
                combo,
                send,
                 createLineSeparator(),
                 historique,
                 createLineSeparator() ,
                 Statics.createBackBtn()
               
        );
        
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        send.requestFocus();
        System.out.println(boutiqueService.findallboutique());
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                connectionRequest = new ConnectionRequest() {
                    @Override
                    protected void postResponse() {
                        Dialog.show("Succes", "envoye avec succes", "ok", null);

                    }
                };
               
                
                System.out.println(boutiqueService.getBoutiqueBynom(boutique.getText())) ;
                /*try {
                    MailService.Send("tunisiamalla", "pidev2018", SignInForm.staticUser.getEmail(), "Reclamation Bien réçu", "Après étude, il semble en effet que certains manquements ont eu lieu dans "
                + "la gestion mise en place par notre service. Croyez bien que nous sommes désolés de cet état "
                + "de fait et que votre dossier fait dès à présent l’objet d’une étude particulière.\n"
                + "Aussi, vous serez contacté(e) très prochainement par un membre de notre équipe / Monsieur Ahmed Merzoug.\n"
                + "Espérant que tout rentrera dans l’ordre rapidement.\n"
                + "Cordialement");
                } catch (MessagingException ex) {
                    Logger.getLogger(ReclamatioForm.class.getName()).log(Level.SEVERE, null, ex);
                }*/
                if( (type.getText()!="") && ( description.getText()!="")){
                    
                
                reclamationservice.addReclamation(type.getText(), description.getText(),SignInForm.staticUser.getIdUser(),
                        userService.getUserByBoutique(boutiqueService.getBoutiqueBynom((String)combo.getSelectedItem()).getIdBoutique()).getIdUser());
                Message m = new Message(description.getText());
        m.getAttachments().put("text", "text/plain");
        Display.getInstance().sendMessage(new String[]{"tunisiamalla@gmail.com"}, "Reclamation ", m);
                }else{
                    Dialog.show("Erreur", "Champs Obligatoires", "ok", null);
                }
                
                
            }
        });
         historique.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {            
               
                
               AfficherRecForm ar=new AfficherRecForm(res);
                
                
            }
        });
        

    

    }
   
}
