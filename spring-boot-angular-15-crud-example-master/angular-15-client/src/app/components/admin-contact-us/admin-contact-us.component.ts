import { Component } from '@angular/core';
import { ContactUsService } from 'src/app/service/contact.us.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Address } from 'src/app/model/address';
import { DialogeComponent } from '../dialoge/dialoge.component';
import { MatDialog } from '@angular/material/dialog';
@Component({
  selector: 'app-admin-contact-us',
  templateUrl: './admin-contact-us.component.html',
  styleUrls: ['./admin-contact-us.component.css',
  '../../../styles.css'
]
})
export class AdminContactUsComponent {
  userdata!:any[];
  userDetail!:FormGroup;
  userDetails!:FormGroup;
  data!:any;
  successMessage: string | null = null;
  errorMessage:string | null= null;
  constructor(private conus:ContactUsService,private formbuilder: FormBuilder, private dialog : MatDialog ) {}
  ngOnInit():void {
 

    this.getAll();
    this.userDetail = this.formbuilder.group ({ 
      id:[''],
      firstname : [''],
      lastname : [''],
      email : [''],
      phonenumber: [''],
      message:[''],
      date:['']
    })
    this.userDetails = this.formbuilder.group ({ 
     address:['']
    })
   


    let currentDate = new Date();
    let day = currentDate.getDate();
    let month = currentDate.getMonth() + 1;
    let year = currentDate.getFullYear();
    let formattedDate = day + "-" + month + "-" + year;

    this.userDetail.patchValue({
      date: formattedDate
    });
    this.conus.getContactAll().subscribe(
      previousValue => {
        this.userDetails.controls['address'].setValue(previousValue.address);

  })
}
  
        
  getAll() {
    this.conus.getAll().subscribe((res) => {
      console.log(res);
      this.userdata = res;
  
    });
  }
  

  deleteUser(id: number) {
    const dialogRef = this.dialog.open(DialogeComponent, {
      data: {
        message: `Do You want to delete ${id}?`,
        showYesNoButtons: true,
        id:id
      }
    });
  
    dialogRef.afterClosed().subscribe(result => {
   
      if (result === true) 
     
      {
        this.conus.deleteUser(id)
          .subscribe(res => {
            this.successMessage = 'Deleted successfully.';
            setTimeout(() => {
              this.successMessage = '';
            }, 1000);
            this.getAll();
          });
          
      }
    });
  }
  edit(data:Address) {
    
  this.conus.updateContact(data).subscribe(res=> {
    this.successMessage = 'Address Edited successfully.';
    setTimeout(() => {
      this.successMessage = '';
    }, 1000);
    console.log(res);
    this.getAll();
    
  })
}


}
