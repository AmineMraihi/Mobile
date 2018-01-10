/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;


import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.CheckBox;
import java.io.IOException;
//import util.Statics;
/**
 *
 * @author ASUS
 */
public class ReclamationDescriptionForm extends BaseForm{
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




//import java.awt.Checkbox;


/**
 *
 * @author MALEK
 */

     /**
     * @param args the command line arguments
     */
        private final Label l1;
        private final Container mainContainer;
        private final Button backBtn;
        Resources res;

  

         
         
            public ReclamationDescriptionForm (String type,String description) throws IOException{

        
         
              this.setLayout(new BorderLayout());
        mainContainer = new Container();
       // mainContainer.setLayout(new GridLayout(16,3));
        l1 = new Label("Description : "+description);
        //l1.getUnselectedStyle().setAlignment(Component.CENTER);
        l1.getUnselectedStyle().setFgColor(-16777216);
        Font l1_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        l1.getUnselectedStyle().setFont(l1_font);
        /*   Toolbar tb = getToolbar();
        tb.setTitleCentered(false); 
        Image profilePic = res.getImage("cropped-cropped-golf-auto-ecole2.jpg");
        Image tintedImage = Image.createImage(profilePic.getWidth(), profilePic.getHeight());
        Graphics g = tintedImage.getGraphics();
        g.drawImage(profilePic, 0, 0);
        g.drawImage(res.getImage("gradient-overlay.png"), 0, 0, profilePic.getWidth(), profilePic.getHeight());

        tb.getUnselectedStyle().setBgImage(tintedImage);
*/
        /*l2 = new Label("Description"+description);
       l2.getUnselectedStyle().setFgColor(-16777216);
        Font l2_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        l2.getUnselectedStyle().setFont(l2_font);*/

        
        mainContainer.add(l1);
        mainContainer.add(new Label());
       // Statics.setLabelStyle(l2);
        /*mainContainer.add(l2);
        mainContainer.add(new Label());*/
               

          //    f1 = new Checkbox(fausse1);

       
        //backBtn = Statics.createBackBtn(); 
        backBtn=new Button();
       mainContainer.add(backBtn);
       backBtn.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent evt) {
                      AfficherRecForm n=new AfficherRecForm(res);
                      n.show();
                  }
              });
       // retour.addActionListener(e -> new AutoEcoleForm(res).show());

       // retour.addActionListener((ActionListener) (ActionEvent evt) -> {
         //   });
       
        this.add(BorderLayout.NORTH, mainContainer);
            }
     
}

