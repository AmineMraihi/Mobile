/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.tunisiamall.service.ReclamationService;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.entities.Reclamation;
import util.Statics;

/**
 *
 * @author ASUS
 */
public class AfficherRecForm extends BaseForm{

   private Form current;
    Resources res;
    public AfficherRecForm(Resources res){
        super(new BorderLayout());
        if (!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout) getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
          setUIID("jassem");
       ReclamationService reclamationService=new ReclamationService();
       reclamationService.findAllReclamation();
    }
    
}
