import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { TestimonialService } from 'src/app/service/testmon.service';
import { DialogeComponent } from '../dialoge/dialoge.component';
import { Testimonial } from 'src/app/model/testimonial.model';
import { FormBuilder, FormGroup } from '@angular/forms';
@Component({
  selector: 'app-admin-testimonial',
  templateUrl: './admin-testimonial.component.html',
  styleUrls: ['./admin-testimonial.component.css'
]
})
export class AdminTestimonialComponent {
showSlider = false;
testImage: any;
recentlyUploadedImage: any;
sliderRecentlyUploadedImage:any;
sliderData!:string[];
fileURL!: File;
imageData!:any;
urllink:string ="";
hideButton=true;
userdata!:any[];
successMessage: string | null = null;
successMessage2: string | null = null;
successMessage1: string | null = null;
successMessage3: string | null = null;
errorMessage: string | null = null;
approvedata!:any[];
addata!:FormGroup;
date!:Date
fileURL1!: File;
savedImageId!: number;
userIdtoView!: number
globalUrl = 'https://img.kraftconstructionco.com'
images: File[] = [];
sliderImages: any[] = [];
activeIndex = 0;
constructor(private testimonialService:TestimonialService, private dialog : MatDialog,private fb:FormBuilder ) {}
ngOnInit():void {
  this.getAllTestimonials();
  this.getApprovedTest();
  this.getAllSlider();
  this.addata = this.fb.group ({
   id:[''],
   imageUrl:[''],
    page:[''],
   
  });


this.testimonialService.getImage().subscribe((res) => {
  this.imageData = res;
  if (this.imageData.length > 0) {
    this.recentlyUploadedImage = this.imageData[this.imageData.length - 1];
    this.testImage = URL.createObjectURL(this.recentlyUploadedImage);
  }
});
this.testimonialService.getImage().subscribe(
  previousValue => {
   

    this.testImage= this.globalUrl+ this.imageData[0].imageUrl;
}) 

this.testimonialService.getSlider().subscribe((res)=> {
 this.sliderData=res;
 this.sliderRecentlyUploadedImage=this.sliderData[this.sliderData.length-1];
 

})
this.testimonialService.getSlider().subscribe(response => {
  this.sliderImages = response.map((image: { imageUrl: any; }) => this.globalUrl + image.imageUrl);

})

}
getAllSlider() {
this.testimonialService.getSlider().subscribe((res)=> {
  this.sliderData=res;
  
 })
 
}




onFileSelected1(event:any) {
this.fileURL = event.target?.files[0];
this.testImage = URL.createObjectURL(this.fileURL);

 
}
getAllImage() {
this.testimonialService.getImage().subscribe((res)=> {
    
  this.imageData=res;

  this.recentlyUploadedImage = this.imageData[this.imageData.length - 1];
 
});
}

deleteSlider() {
  this.sliderRecentlyUploadedImage=this.sliderData[this.sliderData.length-1];

  const dialogRef = this.dialog.open(DialogeComponent, {
    data: {
      message: `Do You want to delete ${this.sliderRecentlyUploadedImage.id}?`,
      showYesNoButtons: true
    }
  });
  dialogRef.afterClosed().subscribe(result => {
    if (result === true) {
      this.testimonialService.deleteSlider(this.sliderRecentlyUploadedImage.id)
        .subscribe(res => {
          this.successMessage3 = res?.message;
          
          setTimeout(() => {
            this.successMessage3 = '';

          }, 3000);
          this.sliderImages.pop();
        });
        this.getAllSlider(); 
      }
      this.getAllSlider();
  });
}
deleteTestimonialImage(id:number) {

  
  const dialogRef = this.dialog.open(DialogeComponent, {
    data: {
      message: `Do You want to delete ${id}?`,
      showYesNoButtons: true
    }
  });

  dialogRef.afterClosed().subscribe(result => {
    if (result === true) {
      this.testimonialService.deleteImage(id)
        .subscribe(res => {
          this.successMessage = res?.message
          setTimeout(() => {
            this.successMessage = '';
          }, 3000);
        });
    }
    this.getAllImage();
  });
}
testimonialImage() {

 
  const formData = new FormData();
  formData.append('image', this.fileURL);
  formData.append('page','TESTIMONIAL')
 
  console.log(this.fileURL,'image');
  this.testimonialService.saveImage(formData).subscribe(
    response => {
      this.savedImageId = response.id;
      this.successMessage=response?.message;
      

      setTimeout(() => {
        this.successMessage = '';
      }, 3000);
      
     
      
        },err=> {
          this.errorMessage= err?.message;
          setTimeout(() => {
            this.errorMessage = '';
          }, 3000);
          
        });
}
OnSliderSelected(event:any) {
  const files = event.target.files;
    for (let i = 0; i < files.length; i++) {
      const file = files[i];
      this.images.push(file);
      this.sliderImages.push(URL.createObjectURL(file));
    }
  
   
  }
onClick2() {
  const user=JSON.parse(localStorage.getItem("user")!);
  if(!user.roles.includes('ROLE_ADMIN')){
     this.dialog.open(DialogeComponent, {
      data: {
        message: `You don't have the access`,
        showYesNoButtons: false
      }
    });
  }
 
  const formData = new FormData();
 
  for (let i = 0; i < this.images.length; i++) {
    formData.append('images', this.images[i]);
  }
  this.testimonialService.saveSlider(formData).subscribe(
    response => {
      this.successMessage3= response?.message;
      
      setTimeout(() => {
        this.successMessage3 = '';
      }, 3000);
      
       
        },err=> {
          this.errorMessage = err?.message;
          setTimeout(() => {
            this.errorMessage = '';
          }, 3000);
          this.getAllSlider();
        });
}
saveSliderImage() {
this.onClick2();
}
getApprovedTest() {
  this.testimonialService.getapproveTest().subscribe((res)=> {
    this.approvedata=res;
});
}
content() {
  this.showSlider=true;
  this.hideButton=false;
}
hideContent() {
  this.showSlider=false;
  this.hideButton=true;
}
getAllTestimonials() {
  this.testimonialService.getAll().subscribe((res)=> {
    this.userdata=res;
  });
}
approveTestimonial(user:Testimonial,id:number) {
  const activeUser=JSON.parse(localStorage.getItem("user")!);
  if(!activeUser.roles.includes('ROLE_ADMIN')){
    this.dialog.open(DialogeComponent, {
      data: {
        message: `You don't have the access`,
        showYesNoButtons: false
      }
    });
  }
  else if(activeUser.roles.includes('ROLE_ADMIN')) {
  const dialogRef = this.dialog.open(DialogeComponent, {
    data: {
      message: `Do You want to Approve ${id}?`,
      showYesNoButtons: true
    }
  });

  dialogRef.afterClosed().subscribe(result => {
    if (result === true) {
      this.testimonialService.approveTest(user,id)
        .subscribe(res => {
          console.log(res?.message)
          this.successMessage2 = res?.approvalStatus;
          setTimeout(() => {
            this.successMessage2 = '';
            this.getApprovedTest();
          }, 3000);
         } ,err=> {
            this.errorMessage = err?.approvalStatus;
            setTimeout(() => {
             
              this.errorMessage = '';
            }, 3000);
            
          });
        }
        
        });
    }
  
  }
  deleteTestimonial(id:number) {
  const user=JSON.parse(localStorage.getItem("user")!);
  if(!user.roles.includes('ROLE_ADMIN')){
    this.dialog.open(DialogeComponent, {
      data: {
        message: `You don't have the access`,
        showYesNoButtons: false
      }
    });
  }
  else if(user.roles.includes('ROLE_ADMIN')) {
  const dialogRef = this.dialog.open(DialogeComponent, {
    data: {
      message: `Do You want to delete ${id}?`,
      showYesNoButtons: true
    }
  });

  dialogRef.afterClosed().subscribe(result => {
    if (result === true) {
      this.testimonialService.deleteTest(id)
        .subscribe(res => {
          this.successMessage = res?.message;
          setTimeout(() => {
            this.successMessage = '';
          }, 1000);
          this.getAllTestimonials();
        });
    }
  });
}
}
hideTestimonial(user:Testimonial,id:number) 
{ 
  
  const activeUser=JSON.parse(localStorage.getItem("user")!);
  if(!activeUser.roles.includes('ROLE_ADMIN')){
    this.dialog.open(DialogeComponent, {
      data: {
        message: `You don't have the access`,
        showYesNoButtons: false
      }
    });
  }
  else if(activeUser.roles.includes('ROLE_ADMIN')) {
  const dialogRef = this.dialog.open(DialogeComponent, {
    data: {
      message: `Do You want to hide ${id}?`,
      showYesNoButtons: true
    }
  });

  dialogRef.afterClosed().subscribe(result => {
    if (result === true) {
      this.testimonialService.hideTest(user,id)
        .subscribe(res => {
          this.successMessage1 = res?.approvalStatus;
          setTimeout(() => {
            this.getApprovedTest();
            this.successMessage1 = '';
          }, 3000);
          
        });
     
    }
    
  });

}
}
onCheckboxChange(user:Testimonial,id:number) {
    this.hideTestimonial(user,id) 
}

onCheckboxChange1(id:number) {
  this.deleteTestimonial(id); 
}

setActiveIndex(index: number) {
  this.activeIndex = index;
}

}



