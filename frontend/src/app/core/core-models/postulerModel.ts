import { JobOffer } from "./jobOffers";
import { Student } from "./student";

export class PostulerModel{
  jobOfferId!:Number;
  studentId!:Number;
  accept!:boolean;
  comment!: String;
  fileCvName!:String;
  fileCvPath!:String;
  date!: Date;  
}
