package com.codename1.uikit.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-27T19:53:07")
@StaticMetamodel(DemandeEmploi.class)
public class DemandeEmploi_ { 

    public static volatile SingularAttribute<DemandeEmploi, String> prenomEmp;
    public static volatile SingularAttribute<DemandeEmploi, Integer> idUserFk;
    public static volatile SingularAttribute<DemandeEmploi, String> qualification;
    public static volatile SingularAttribute<DemandeEmploi, Date> dateNaissance;
    public static volatile SingularAttribute<DemandeEmploi, String> adresse;
    public static volatile SingularAttribute<DemandeEmploi, Integer> idOffreFk;
    public static volatile SingularAttribute<DemandeEmploi, Integer> idDemande;
    public static volatile SingularAttribute<DemandeEmploi, String> sexe;
    public static volatile SingularAttribute<DemandeEmploi, Integer> experience;
    public static volatile SingularAttribute<DemandeEmploi, String> numTel;
    public static volatile SingularAttribute<DemandeEmploi, String> email;
    public static volatile SingularAttribute<DemandeEmploi, String> nomEmp;

}