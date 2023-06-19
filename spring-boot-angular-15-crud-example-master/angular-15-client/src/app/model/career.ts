export class CareerData {
    id!:number;
    firstName : string='';
    lastName :string ='';
    email : string='';
    phoneNumber:string='';
    address : string='';
    city : string='';
    state: string='';
    zip:string='';
    workExperience:string='';
    jobType:string='';
    workRestrictions:string='';
    hoursRestrictions='';
    skills="";
    resumeUrl:string='';
    otherNotes:string='';
    references: { id: number, name: string,relationship:string,phoneNumber:number }[] = [];
}