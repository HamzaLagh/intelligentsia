import { JobOffer } from "./jobOffers";
import { User } from "./user";


export class Society extends User {
  name!:String;
  address!:String;
  tel!:String;
  fileLogoName!:String;
  fileLogoPath!:String;
  specialization!:String;
  siret!:String;
  siren!:String;
  //description!:String;
  jobOffers!: JobOffer[];
}
