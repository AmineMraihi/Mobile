package com.codename1.uikit.entities;

import com.codename1.uikit.entities.Boutique;
import com.codename1.uikit.entities.DemandeEmploi;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-08T12:36:14")
@StaticMetamodel(OffreEmploi.class)
public class OffreEmploi_ { 

    public static volatile SingularAttribute<OffreEmploi, Integer> idUserFk;
    public static volatile SingularAttribute<OffreEmploi, Boutique> idBoutiqueFk;
    public static volatile SingularAttribute<OffreEmploi, String> specialite;
    public static volatile SingularAttribute<OffreEmploi, Date> dateExpiration;
    public static volatile CollectionAttribute<OffreEmploi, DemandeEmploi> demandeEmploiCollection;
    public static volatile SingularAttribute<OffreEmploi, Integer> nbrDemandé;
    public static volatile SingularAttribute<OffreEmploi, Integer> idOffre;
    public static volatile SingularAttribute<OffreEmploi, String> poste;
    public static volatile SingularAttribute<OffreEmploi, Double> salaire;

}