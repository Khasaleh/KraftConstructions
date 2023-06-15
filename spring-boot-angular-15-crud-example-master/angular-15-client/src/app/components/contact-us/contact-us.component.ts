import { Component } from '@angular/core';
import {FormGroup, FormBuilder} from '@angular/forms'
import { MatDialog } from '@angular/material/dialog';
import { ContactUs } from 'src/app/model/conus';
import { ContactUsService } from 'src/app/service/contact.us.service';
import { DialogeComponent } from '../dialoge/dialoge.component';
@Component({
  selector: 'app-contact-us',
  templateUrl: './contact-us.component.html',
  styleUrls: ['./contact-us.component.css']
})
export class ContactUsComponent {
  userDetail!:FormGroup;
  userObj: ContactUs = new ContactUs();
  data!:any;
  constructor(private formBuilder : FormBuilder, private conus:ContactUsService, private dialog:MatDialog) {}
ngOnInit(): void {
this.userDetail = this.formBuilder.group ({
  firstname : [''],
  lastname : [''],
  email : [''],
  phonenumber: [''],
  message:['']

  
});
this.conus.getContactAll().subscribe(result=> {
console.log(result);
this.data=result;
})

}

submit() {
  this.userObj = this.userDetail.value; 
  console.log(this.userObj); 
 
  this.conus.saveData(this.userObj).subscribe(res => {
    this.openDialog();
    console.log(res);
    
    
  }, err=> {
    console.log(err)
   })
  
}
openDialog(): void {
  const dialogRef = this.dialog.open(DialogeComponent, {
    data: {
      message: 'Submitted Successfully',
      showYesNoButtons: false,
      customButton: { label: 'Ok', action: 'custom-action' }
    }

  });
  dialogRef.afterClosed().subscribe(result => {
  
    console.log(result);
  });

 
}

}
