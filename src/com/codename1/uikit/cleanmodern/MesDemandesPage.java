/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.components.WebBrowser;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.entities.DemandeEmploi;
import com.codename1.uikit.entities.OffreEmploi;
import com.codename1.uikit.entities.User;
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
public class MesDemandesPage extends BaseForm {
    //Resources theme ;
    //Form MesDemandesPage;

    List<DemandeEmploi> listDemandes = new ArrayList<>();
    //public static DemandeEmploi StaticOffre = null;
    User u = SignInForm.staticUser;
    OffreEmploi o = OffreEmploiPage.StaticOffre;
    // public static DemandeEmploi staticDemande1;
    //public static DemandeEmploi staticDemande;

    public MesDemandesPage(Resources res) {
        super("", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
//        getTitleArea().setUIID("Container");
        setTitle("");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        /*tb.addSearchCommand(e -> {
        });*/

        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("profile-background.jpg"), spacer1, "", "", "");
        //addTab(swipe, res.getImage("dog.jpg"), spacer2, "100 Likes  ", "66 Comments", "Dogs are cute: story at 11");

        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();

        Container list = new Container(new BoxLayout( BoxLayout.y().Y_AXIS) );
        //list.setScrollableY(true);

        /*
         
   d.setIdDemande((int) Float.parseFloat(obj.get("id_demande").toString()));
                //System.out.println(d.getIdDemande());

                d.setNomEmp((String) obj.get("NomEmp"));
              //  System.out.println(d.getNomEmp());

                d.setPrenomEmp((String) obj.get("PrenomEmp"));
             //   System.out.println(d.getPrenomEmp());

                d.setAdresse((String) obj.get("adresse"));
             //   System.out.println(d.getAdresse());

                d.setSexe((String) obj.get("sexe"));
             //   System.out.println(d.getPrenomEmp());
                
               d.setNumTel((String) obj.get("numTel"));
             //   System.out.println(d.getPrenomEmp());

                d.setEmail((String) obj.get("email"));
             //   System.out.println(d.getAdresse());

                d.setQualification((String) obj.get("qualification"));
             //   System.out.println(d.getPrenomEmp());
             
                d.setExperience(((int) Float.parseFloat(obj.get("experience").toString())));
              //  System.out.println(d.getDateNaissance());
                
         
         */
        for (DemandeEmploi d : getAllDemandes()) {
            Label l = new Label("Nom:");
            MultiButton mb = new MultiButton(d.getNomEmp());
            Label l1 = new Label("Prenom:");
            MultiButton mb1 = new MultiButton(d.getPrenomEmp());
            Label l2 = new Label("Adresse");
            MultiButton mb2 = new MultiButton(d.getAdresse().toString());
            Label l3 = new Label("Qualification:");
            MultiButton mb3 = new MultiButton(d.getQualification());
            //  Label l4 = new Label("Experience");
            //  MultiButton mb4 = new MultiButton(d.getExperience().toString());

            Button bt1 = new Button("Modifier demande");
            Button bt2 = new Button("Supprimer demande");

            // MultiButton mb5 = new MultiButton(e.getDateExpiration().toString());
            list.add(l);
            list.add(mb);

            list.add(l2);
            list.add(mb2);

            list.add(l1);
            list.add(mb1);

            list.add(l3);
            list.add(mb3);

            //  list.add(l4);
            //  list.add(mb4);
            list.add(bt1);
            list.add(bt2);

            bt1.addActionListener(new ActionListener() {
                @Override

                public void actionPerformed(ActionEvent evt) {

                    ModifierDemande modifier = new ModifierDemande(res, d);
                    modifier.show();

                }
            });

            bt2.addActionListener(new ActionListener() {
                @Override

                public void actionPerformed(ActionEvent evt) {

                    ConnectionRequest req = new ConnectionRequest();
                    //      req.setUrl("http://localhost:8082/crud/ajouterDemande.php?id_user_fk=" +idu+ "$id_offre_fk=" +ido+ "$NomEmp=" + nomt.getText() + "&PrenomEmp=" + prenomt.getText() + "&adresse=" + adresset.getText()+ "&sexe=" + sexet.getText() + "&numTel=" + numt.getText() + "&email=" + emailt.getText() + "&qualification=" + qualift.getText() + "&exper="+ expt.getText() + "");

                    req.setUrl("http://localhost:8082/crud/supprimerDemande.php?id_demande=" + d.getIdDemande() + "");

                    //MesDemandesPage m = new MesDemandesPage(res);
                    // m.show();
                    req.addResponseListener(new ActionListener<NetworkEvent>() {

                        @Override
                        public void actionPerformed(NetworkEvent evt) {
                            byte[] data = (byte[]) evt.getMetaData();
                            String s = new String(data);

                            if (s.equals("success")) {
                                Dialog.show("Confirmation", "ajout avec succé", "Ok", null);
                               

                            }

                        }
                    });

                    NetworkManager.getInstance().addToQueue(req);
                MesDemandesPage mesdemande = new MesDemandesPage(res);
                     mesdemande.show();

                }
            });

        }
      //   WebBrowser web = new WebBrowser("https://web.facebook.com"); 
       
        //list.add(web);
        add(list);
       
          
          
        
    }

    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();

    }

    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if (img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");

        Container page1
                = LayeredLayout.encloseIn(
                        image,
                        overlay,
                        BorderLayout.south(
                                BoxLayout.encloseY(
                                        new SpanLabel(text, "LargeWhiteText"),
                                        FlowLayout.encloseIn(likes, comments),
                                        spacer
                                )
                        )
                );

        swipe.addTab("", page1);
    }

    private void addButton(Image img, String title, boolean liked, int likeCount, int commentCount) {
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        cnt.setLeadComponent(image);
        TextArea ta = new TextArea(title);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);

        Label likes = new Label(likeCount + " Likes  ", "NewsBottomLine");
        likes.setTextPosition(RIGHT);
        if (!liked) {
            FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
        } else {
            Style s = new Style(likes.getUnselectedStyle());
            s.setFgColor(0xff2d55);
            FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
            likes.setIcon(heartImage);
        }
        Label comments = new Label(commentCount + " Comments", "NewsBottomLine");
        FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);

        cnt.add(BorderLayout.CENTER,
                BoxLayout.encloseY(
                        ta,
                        BoxLayout.encloseX(likes, comments)
                ));
        add(cnt);
        image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
    }

    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if (b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }

    public List<DemandeEmploi> getAllDemandes() {
        //    List<Map<String, Object>> all = new ArrayList<>();
        System.out.println(u.getIdUser());
        ConnectionRequest request = new ConnectionRequest("http://localhost:8082/crud/listeDemandes.php?id_user_fk=" + u.getIdUser() + "");
        NetworkManager.getInstance().addToQueueAndWait(request);

        Map<String, Object> result = null;

        try {
            result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));

            List<Map<String, Object>> response = (List<Map<String, Object>>) result.get("root");
            System.out.println();

            for (Map<String, Object> obj : response) {

                DemandeEmploi d = new DemandeEmploi();

                d.setIdUserFk(u.getIdUser());
                d.setIdOffreFk((int) Float.parseFloat(obj.get("id_offre_fk").toString()));

                d.setIdDemande((int) Float.parseFloat(obj.get("id_demande").toString()));
                //System.out.println(d.getIdDemande());

                d.setNomEmp((String) obj.get("nom_emp"));
                //  System.out.println(d.getNomEmp());

                d.setPrenomEmp((String) obj.get("prenom_emp"));
                //   System.out.println(d.getPrenomEmp());

                d.setAdresse((String) obj.get("adresse"));
                //   System.out.println(d.getAdresse());

                d.setSexe((String) obj.get("sexe"));
                //   System.out.println(d.getPrenomEmp());

                d.setNumTel((String) obj.get("num_tel"));
                //   System.out.println(d.getPrenomEmp());

                d.setEmail((String) obj.get("email"));
                //   System.out.println(d.getAdresse());

                d.setQualification((String) obj.get("qualification"));
                //   System.out.println(d.getPrenomEmp());

                //     d.setExperience(((int) Float.parseFloat(obj.get("experience").toString())));
                //  System.out.println(d.getDateNaissance());
                listDemandes.add(d);
            }
            System.out.println(listDemandes.size());
        } catch (IOException ex) {
            System.out.println("EXCEPTION : " + ex);

        }
        return listDemandes;

    }

}
