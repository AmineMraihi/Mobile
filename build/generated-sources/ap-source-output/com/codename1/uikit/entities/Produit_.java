package com.codename1.uikit.entities;

import com.codename1.uikit.entities.Boutique;
import com.codename1.uikit.entities.Promotion;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-26T00:27:29")
@StaticMetamodel(Produit.class)
public class Produit_ { 

    public static volatile SingularAttribute<Produit, String> path;
    public static volatile SingularAttribute<Produit, Integer> idProduit;
    public static volatile SingularAttribute<Produit, Double> prix;
    public static volatile CollectionAttribute<Produit, Promotion> promotionCollection;
    public static volatile SingularAttribute<Produit, Double> prixAchatGros;
    public static volatile SingularAttribute<Produit, Boutique> idBoutique;
    public static volatile SingularAttribute<Produit, String> description;
    public static volatile SingularAttribute<Produit, String> type;
    public static volatile SingularAttribute<Produit, String> nom;
    public static volatile SingularAttribute<Produit, Integer> nbVente;
    public static volatile SingularAttribute<Produit, Integer> quantite;
    public static volatile SingularAttribute<Produit, Date> updatedAt;

}