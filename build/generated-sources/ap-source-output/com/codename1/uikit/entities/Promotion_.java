package com.codename1.uikit.entities;

import com.codename1.uikit.entities.Produit;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-01-10T15:09:07")
@StaticMetamodel(Promotion.class)
public class Promotion_ { 

    public static volatile SingularAttribute<Promotion, Double> tauxReduction;
    public static volatile SingularAttribute<Promotion, String> path;
    public static volatile SingularAttribute<Promotion, Produit> idProduit;
    public static volatile SingularAttribute<Promotion, Integer> idPromotion;
    public static volatile SingularAttribute<Promotion, Date> dateDebut;
    public static volatile SingularAttribute<Promotion, Date> dateFin;
    public static volatile SingularAttribute<Promotion, Date> updatedAt;

}