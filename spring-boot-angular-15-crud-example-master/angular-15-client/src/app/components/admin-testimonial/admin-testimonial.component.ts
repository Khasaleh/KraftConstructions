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
Show = false;
imageLink: any;
recentlyUploadedImage: any;
recentlyUploadedImage1:any;
imagedata!:any;
fileURL!: File;
imageData!:any;
sliderdata!:any;
urllink:string ="";
abc=true;
userdata!:any[];
successMessage: string | null = null;
errorMessage: string | null = null;
approvedata!:any[];
addata!:FormGroup;
date!:Date
fileURL1!: File;
savedImageId!: number;
userIdtoView!: number
globalUrl="http://99.72.32.144:8083";
images: File[] = [];
imageLink1: any[] = [];
activeIndex = 0;
// image: any;
constructor(private testimonialService:TestimonialService, private dialog : MatDialog,private fb:FormBuilder ) {}
ngOnInit():void {
  this.getAll();
  this.getApprovedTest();
  this.addata = this.fb.group ({
   
    page:[''],
    date:[''],
    time:['']
  });
  let currentDate = new Date();
    let day = currentDate.getDate();
    let month = currentDate.getMonth() + 1;
    let year = currentDate.getFullYear();
    let formattedDate = day + "-" + month + "-" + year;
    this.addata.patchValue({
      date: formattedDate
    });
  // Get the current date and time
const currentTime = new Date();


let hours = currentTime.getHours();
let minutes = currentTime.getMinutes();


const ampm = hours >= 12 ? 'pm' : 'am';


hours = hours % 12;
hours = hours ? hours : 12;


let minute = minutes < 10 ? '0' + minutes : minutes;

// Format the time string
const formattedTime = hours + ':' + minute + ' ' + ampm;

this.addata.patchValue({
  time: formattedTime
});

this.testimonialService.getImage().subscribe((res) => {
  this.imageData = res;
  console.log(this.imageData);
  if (this.imageData.length > 0) {
    this.recentlyUploadedImage = this.imageData[this.imageData.length - 1];
    this.imageLink = URL.createObjectURL(this.recentlyUploadedImage);
  }
});


this.testimonialService.getSlider().subscribe((res)=> {
 this.imagedata=res;
 this.recentlyUploadedImage1=this.imagedata[this.imageData.length];
 

})
}
getAllSlider() {
this.testimonialService.getSlider().subscribe((res)=> {
  this.imagedata=res;
  this.recentlyUploadedImage1=this.imagedata[this.imageData.length];
 
 })
}
// getSliderbyId(id: number) {
//   this.userIdtoView = id;
//   this.testimonialService.getSliderById(this.userIdtoView).subscribe(
//     (res: Image) => {
//       console.log('Retrieved image:', res);
//     },
//     (error: any) => {
//       console.error('Failed to retrieve image:', error);
//     }
//   );
// }

onFileSelected1(event:any) {
this.fileURL = event.target?.files[0];
this.imageLink = URL.createObjectURL(this.fileURL);

 
}
getAllImage() {
this.testimonialService.getImage().subscribe((res)=> {
    
  this.imageData=res;
  console.log(this.imageData)
 
    
  this.recentlyUploadedImage = this.imageData[this.imageData.length - 1];
 
});
}

deleteSlider(id:number) {
  
  const dialogRef = this.dialog.open(DialogeComponent, {
    data: {
      message: `Do You want to delete ${id}?`,
      showYesNoButtons: true
    }
  });

  dialogRef.afterClosed().subscribe(result => {
    if (result === true) {
      this.testimonialService.deleteSlider(id)
        .subscribe(res => {
          this.successMessage = 'Data Deleted successfully.';
          setTimeout(() => {
            this.successMessage = '';
          }, 1000);
          this.getAllSlider();
        });
    }
  });
}
deleteImage(id:number) {
  
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
          this.successMessage = 'Data Deleted successfully.';
          setTimeout(() => {
            this.successMessage = '';
          }, 1000);
          this.getAllImage();
        });
    }
  });
}
onClick1() {
 
  const formData = new FormData();
  formData.append('image', this.fileURL);
  formData.append('page','TESTIMONIAL')
  // formData.append('id', this.userIdtoView)
  console.log(this.fileURL,'image');
  this.testimonialService.saveImage(formData).subscribe(
    response => {
      this.savedImageId = response.id;
      this.successMessage='Data Saved Successfully'
      

      setTimeout(() => {
        this.successMessage = '';
      }, 1000);
      
      console.log(response);
          
         
        },err=> {
          this.errorMessage='Unable to save data'
          setTimeout(() => {
            this.errorMessage = '';
          }, 1000);
          console.log(err);
        });
}
onFileSelected(event:any) {
  const files = event.target.files;
    for (let i = 0; i < files.length; i++) {
      const file = files[i];
      this.images.push(file);
      this.imageLink1.push(URL.createObjectURL(file));
    }
  
   
  }
onClick2() {
 
  const formData = new FormData();
 
  for (let i = 0; i < this.images.length; i++) {
    formData.append('images', this.images[i]);
  }

  // Perform upload logic here, e.g., send formData to server
  console.log(formData);
  this.testimonialService.saveSlider(formData).subscribe(
    response => {
      this.successMessage='Data Saved Successfully'
      
      setTimeout(() => {
        this.successMessage = '';
      }, 1000);
      
          
          
         
        },err=> {
          this.errorMessage='Unable to save data'
          setTimeout(() => {
            this.errorMessage = '';
          }, 1000);
          this.getAllSlider();
        });
}
submit() {
  this.onClick1();
}
submit1() {
this.onClick2();
}
getApprovedTest() {
  this.testimonialService.getapproveTest().subscribe((res)=> {
    console.log(res);
    this.approvedata=res;
    this.getAll();
});
}
content() {
  this.Show=true;
  this.abc=false;
}
content1() {
  this.Show=false;
  this.abc=true;
}
getAll() {
  this.testimonialService.getAll().subscribe((res)=> {
    console.log(res);
    this.userdata=res;
  });
}
approveTest(user:Testimonial,id:number) {
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
          this.successMessage = 'Data Approved Successfully.';
          setTimeout(() => {
            this.successMessage = '';
          }, 1000);
          this.getAll();
        });
    }
  });
}

deleteTest(id:number) {
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
          this.successMessage = 'Data Deleted successfully.';
          setTimeout(() => {
            this.successMessage = '';
          }, 1000);
          this.getAll();
        });
    }
  });
}
hideTest(user:Testimonial,id:number) 
{ 
  console.log(id)
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
          this.successMessage = 'Data Hidden Successfully.';
          setTimeout(() => {
            this.successMessage = '';
          }, 1000);
          this.getAll();
        });
    }
  });
}
onCheckboxChange(user:Testimonial,id:number) {

    
    this.hideTest(user,id) 
    
  
}
onCheckboxChange1(id:number) {

    
  this.deleteTest(id); 

  

}
setActiveIndex(index: number) {
  this.activeIndex = index;
}

}



