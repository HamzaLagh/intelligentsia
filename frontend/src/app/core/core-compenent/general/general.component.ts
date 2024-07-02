import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators,ReactiveFormsModule } from '@angular/forms';
import { Student } from '../../core-models/student';
import { Observable } from 'rxjs';
import { StudentModel } from '../../core-models/studentModel';
import { SocietyModel } from '../../core-models/societyModel';
import { Society } from '../../core-models/society';
import { Router } from '@angular/router';
import { InscriptionService } from '../../core-services/inscription-service';

@Component({
  selector: 'app-general',
  templateUrl: './general.component.html',
  styleUrls: ['./general.component.css']
})
export class GeneralComponent implements OnInit{
  change !:boolean;
  form!:FormGroup;
  nomCtrl!: FormControl;
  prenomCtrl!: FormControl;
  telCtrl!: FormControl;
  adressCtrl!: FormControl;
  emailCtrl!: FormControl;
  passwordCtrl!: FormControl;
  experienceCtrl!: FormControl;
  sexeCtrl!: FormControl;
  typeFormationCtrl!:FormControl;
  niveauEtudeCtrl!:FormControl;
  student!: Observable<Student>;
  studentModel: StudentModel= new StudentModel();
  filecv!: File;
  sirenCltrl!: FormControl ;
  siretCltrl!: FormControl ;
  specializationCltrl!:  FormControl ;
  society!: Observable<Society>;
  societyModel: SocietyModel= new SocietyModel();

  constructor(private formBuilder:FormBuilder, private inscriptionServices:InscriptionService,private router: Router){}

  ngOnInit(): void {
  this.initFormControls();
  this.initMainForm();
  this.change=false;
}

onClick1(){
  if(this.change===false){
    this.change=true;
    //console.log(this.change);
   }
}
onClick2(){
  if(this.change===true){
    this.change=false;
    //console.log(this.change);
   }
}

  
onSelectCv(event: any) {
  this.filecv = event.target.files[0];
  // traitement du fichier ici
}

private initMainForm(): void{
  this.form= this.formBuilder.group(
    {
      nom : this.nomCtrl,
      prenom : this.prenomCtrl,
      tel : this.telCtrl,
      adress : this.adressCtrl,
      email : this.emailCtrl,
      password : this.passwordCtrl,
      experience : this.experienceCtrl,
      sexe : this.sexeCtrl,
      typeFormation:this.typeFormationCtrl,
      niveauEtude:this.niveauEtudeCtrl,
      specialization:this.specializationCltrl,
      siren:this.sirenCltrl,
      siret:this.siretCltrl
      
    }
  );
}
private initFormControls(): void{
  this.nomCtrl= this.formBuilder.control('',Validators.required);
  this.prenomCtrl= this.formBuilder.control('',Validators.required);
  this.emailCtrl= this.formBuilder.control('',[Validators.required, Validators.email]);
  this.passwordCtrl = this.formBuilder.control('',Validators.required);
  this.telCtrl= this.formBuilder.control('',Validators.required);
  this.adressCtrl= this.formBuilder.control('',Validators.required);
  this.experienceCtrl= this.formBuilder.control('',Validators.required);
  this.sexeCtrl= this.formBuilder.control('',Validators.required);
  this.sirenCltrl= this.formBuilder.control('',Validators.required);
  this.siretCltrl= this.formBuilder.control('',Validators.required);
  this.specializationCltrl= this.formBuilder.control('',Validators.required);
  this.typeFormationCtrl= this.formBuilder.control('',Validators.required);
  this.niveauEtudeCtrl= this.formBuilder.control('',Validators.required);

}

onSubmitForm(){
  if(this.change===false){
  this.studentModel.name=this.form.value.nom;
  this.studentModel.firstname=this.form.value.prenom;
  this.studentModel.tel=this.form.value.tel;
  this.studentModel.role=this.form.value.role;
  this.studentModel.address=this.form.value.adress;
  this.studentModel.email=this.form.value.email;
  this.studentModel.password=this.form.value.password;
  this.studentModel.experience=this.form.value.experience;
  this.studentModel.typeDeFormation=this.form.value.typeFormation;
  this.studentModel.sexe=this.form.value.sexe;
  this.studentModel.niveauEtude=this.form.value.niveauEtude;
  this.student=this.inscriptionServices.saveStudent(this.studentModel,this.filecv);
  this.student.subscribe((student:Student)=>{
  if(student.enabled==true)
    this.router.navigateByUrl('/connexion');
  else
    this.router.navigateByUrl(`/confirmation?id=${student.id}`);
})
  
}else{
  this.societyModel.name=this.form.value.nom;
  this.societyModel.address=this.form.value.adress;
  this.societyModel.email=this.form.value.email;
  this.societyModel.password=this.form.value.password;
  this.societyModel.siren=this.form.value.siren;
  this.societyModel.siret=this.form.value.siret;
  this.societyModel.specialization=this.form.value.specialization;
  this.societyModel.tel=this.form.value.tel;
  this.society=this.inscriptionServices.saveSociety(this.societyModel);
  this.society.subscribe((society:Society)=>{
    if(society.enabled==true)
    this.router.navigateByUrl('/connexion');
  else
    this.router.navigateByUrl('/attente');
  })
}
}
}
