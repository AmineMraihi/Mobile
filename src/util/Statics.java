/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.codename1.ui.Button;
import com.codename1.ui.Font;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.NewsfeedForm;
import java.io.IOException;

/**
 *
 * @author ASUS
 */
public class Statics {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author Amal
 */

    
    public static void setLabelStyle(Label l){
        l.getUnselectedStyle().setFgColor(-16777216);
        l.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));
    }
     
     public static Button createBackBtn() throws IOException{
         Resources res=null;
         res=Resources.open("/theme.res");
         NewsfeedForm n =new NewsfeedForm(res);
         Button b=new Button("Back");
         b.getUnselectedStyle().setFgColor(5542241);
         b.addActionListener((ActionListener) (ActionEvent evt) -> {
            n.show();
           
         });
         return b;
     }
    
}


