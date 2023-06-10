import { Component, OnInit } from '@angular/core';
import {FormGroup, FormBuilder} from '@angular/forms'
import { MatDialog } from '@angular/material/dialog';
import { AddUser } from 'src/app/model/add-user';
import { AddUserService } from 'src/app/service/add-user.service';
import { DialogeComponent } from '../dialoge/dialoge.component';
@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {
  urllink = '';

selectFiles(event:any)
{
  if(event.target.files){
    var reader = new FileReader()
    reader.readAsDataURL(event.target.files[0])
    reader.onload = (event:any) => {
      this.urllink = event.target.result
    }
  }
}
  userDetail!: FormGroup ;
  userObj:AddUser = new AddUser();
  userList!:any[] ;


  constructor(private formBuilder : FormBuilder, private adService : AddUserService, private dialog : MatDialog) { }
ngOnInit(): void {
  
  this.userDetail = this.formBuilder.group ({
    username : [''],
    email : [''],
    firstname : [''],
    lastname : [''],
    password : [''],
    role: ['']
  
    
  });
}
  addUser() {
    
    
    this.userObj.username = this.userDetail.value?.username;
    this.userObj.email = this.userDetail.value?.email;
    this.userObj.firstname = this.userDetail.value?.firstname;
    this.userObj.lastname = this.userDetail.value?.lastname;
    this.userObj.password = this.userDetail.value?.password;
    this.userObj.role = [this.userDetail.value?.role];
    console.log(this.userObj.role)
     this.adService.AddUser(this.userObj).subscribe(res=>{
      console.log(res);
      this.openDialog();
    
      

     }, err=> {
      console.log(err)
     })
  }

  showTable = false;
  openDialog(): void {
    const dialogRef = this.dialog.open(DialogeComponent, {
      data: {
        message: `${this.userObj.username} has been Submitted Successfully`,
        showYesNoButtons: false,
        customButton: { label: 'Ok', action: 'custom-action' }
      }
  
    });
    dialogRef.afterClosed().subscribe(result => {
    
      console.log(result);
    });
  
   
  }
}

