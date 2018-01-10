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

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.Storage;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.io.IOException;




/**
 * Base class for the forms with common functionality
 *
 * @author Shai Almog
 */
public class BaseForm extends Form {
    
    

    public BaseForm()  {
    }

    public BaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public BaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    protected void addSideMenu(Resources res)  {

//////////////////////          this will put profile picture in its place 
        String brochure = SignInForm.staticUser.getPath();
//        String brochure1 = SignInForm.staticUser.getPath();

//        System.out.println(brochure1);
        EncodedImage imgprofile = EncodedImage.createFromImage(
                Image.createImage(Display.getInstance().getDisplayWidth(), 150), true
        );
        URLImage imgg = URLImage.createToStorage(
                imgprofile, "http://localhost/TestUser/web/images/amine/" + brochure,
                "http://localhost/TestUser/web/images/amine/" + brochure
        );
//        URLImage imgg = URLImage.createToStorage(
//                imgprofile, brochure,
//                brochure
//        );
        imgg.fetch();
//        imgg1.fetch();

        ImageViewer imgv = new ImageViewer(imgg);
//        ImageViewer imgv1 = new ImageViewer(imgg1);

        int fiveMM = Display.getInstance().convertToPixels(20);
        final Image finalDuke = imgg.scaledWidth(fiveMM);
            //        final Image finalDuke1 = imgg1.scaledWidth(fiveMM);
//            Image finalDuke12 = Image.createImage(Storage.getInstance().createInputStream(brochure));
//////////////////////
        Toolbar tb = getToolbar();
        Image img = res.getImage("profile-background.jpg");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                sl,
                FlowLayout.encloseCenterBottom(
                        //new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond"))
                        new Label(finalDuke, "nothing_to_see_here"))
        ));
        tb.addMaterialCommandToSideMenu("Liste des Produits", FontImage.MATERIAL_UPDATE, e -> new Produittry2(res).show());


        tb.addMaterialCommandToSideMenu("Newsfeed", FontImage.MATERIAL_UPDATE,
                e -> new NewsfeedForm(res).show());

      

        tb.addMaterialCommandToSideMenu("Boutique", FontImage.MATERIAL_UPDATE,
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                BlankPage blankPage = new BlankPage(res);
                blankPage.show();
            }
        });

         tb.addMaterialCommandToSideMenu("Statistique", FontImage.MATERIAL_UPDATE,
                      new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                MyApplication my = new MyApplication();
                my.start();
            }
        });

        
        
        tb.addMaterialCommandToSideMenu("Evenement", FontImage.MATERIAL_UPDATE,
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EventPage eventpage = new EventPage(res);
                eventpage.show();
            }
        });

         tb.addMaterialCommandToSideMenu("Offre d'emploi", FontImage.MATERIAL_UPDATE,
              e -> new OffreEmploiPage(res).show());
        
        tb.addMaterialCommandToSideMenu("Mes demandes", FontImage.MATERIAL_UPDATE,
               new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
              //  User u = SignInForm.staticUser;
              //  id = u.getIdUser();
              //  System.out.println(id);
                MesDemandesPage mesdemande = new MesDemandesPage(res);
                mesdemande.show();
            }
        });
        

        tb.addMaterialCommandToSideMenu("Parking", FontImage.MATERIAL_UPDATE,
                e -> new NewsfeedForm(res).show());

        tb.addMaterialCommandToSideMenu("Jardin d'enfant", FontImage.MATERIAL_UPDATE,
                e -> new NewsfeedForm(res).show());

        tb.addMaterialCommandToSideMenu("Reclamation", FontImage.MATERIAL_UPDATE,
                e -> {
            
            try {
                new ReclamatioForm(res).show();
            } catch (IOException ex) {
               
            }
            
        });

        tb.addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_SETTINGS,
                e -> new ProfileForm(res).show());

        tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP,
                e -> new SignInForm(res).show());
    }
}
