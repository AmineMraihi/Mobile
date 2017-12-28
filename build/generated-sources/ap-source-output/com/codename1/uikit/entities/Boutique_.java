package com.codename1.uikit.entities;

import com.codename1.uikit.entities.DemandePub;
import com.codename1.uikit.entities.OffreEmploi;
import com.codename1.uikit.entities.Produit;
import com.codename1.uikit.entities.Publicite;
import com.codename1.uikit.entities.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-26T00:27:29")
@StaticMetamodel(Boutique.class)
public class Boutique_ { 

    public static volatile SingularAttribute<Boutique, Date> dateCreation;
    public static volatile CollectionAttribute<Boutique, Produit> produitCollection;
    public static volatile SingularAttribute<Boutique, Date> dateExpiration;
    public static volatile CollectionAttribute<Boutique, DemandePub> demandePubCollection;
    public static volatile CollectionAttribute<Boutique, Publicite> publiciteCollection;
    public static volatile CollectionAttribute<Boutique, User> userCollection;
    public static volatile SingularAttribute<Boutique, Integer> idBoutique;
    public static volatile SingularAttribute<Boutique, String> position;
    public static volatile SingularAttribute<Boutique, String> type;
    public static volatile SingularAttribute<Boutique, String> nom;
    public static volatile CollectionAttribute<Boutique, OffreEmploi> offreEmploiCollection;

}