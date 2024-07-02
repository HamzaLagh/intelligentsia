import { Society } from "./society";

export class JobOffer{
  id!: Number;
  titre!:String;
  description!:String;
  dateCreate!:Date;
  dateLimite!:Date;
  nbrPostePropose!: Number;;
  secteurActivite!:String;// Informatique,..
  fonction!:String;
  niveauEtude!:String;
  typeContrat!:String;// CDI ou ...
  typeDeTravail!:String; // En distance ou en Présentiel
  avantage!:String;
  region!:String;
  softkil!:String;
  experience!:String;
  society!: Society;
  //descriptionProfilRecherche!:String;
}
