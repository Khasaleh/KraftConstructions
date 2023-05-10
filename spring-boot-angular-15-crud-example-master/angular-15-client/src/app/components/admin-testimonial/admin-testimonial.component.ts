import { Component } from '@angular/core';
@Component({
  selector: 'app-admin-testimonial',
  templateUrl: './admin-testimonial.component.html',
  styleUrls: ['./admin-testimonial.component.css'
]
})
export class AdminTestimonialComponent {
Show = false;
urllink:string ="";
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
}

