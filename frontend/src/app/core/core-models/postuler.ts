import { JobOffer } from "./jobOffers";
import { Student } from "./student";

export class Postuler{
  id!:Number;
  jobOffer!:JobOffer;
  student!:Student;
  accept!:boolean;
  comment!: String;
  fileCvName!:String;
  fileCvPath!:String;
  date!: Date;  
}
