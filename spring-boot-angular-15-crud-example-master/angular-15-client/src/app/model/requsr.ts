export class ReqUser {
  id!: number 
  firstName : string= ''
  lastName : string=''
  email : string=''
  phoneNumber:string=''
  address : string=''
  city!: string;
  state!: string;
  zip: string=''
  requestedServices!: [];
  budget: string ="";
  projectDescription:string=''
  aboutUs:any['']
}