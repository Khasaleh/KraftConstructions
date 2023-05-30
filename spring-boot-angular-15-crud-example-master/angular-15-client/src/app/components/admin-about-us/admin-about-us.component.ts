import { Component } from '@angular/core';
import { aboutusdata } from 'src/app/data-type';
import { UpdabtusService } from 'src/app/service/updabtus.service';
import { ImageService } from 'src/app/service/image.service';
@Component({
  selector: 'app-admin-about-us',
  templateUrl: './admin-about-us.component.html',
  styleUrls: ['./admin-about-us.component.css']
})
export class AdminAboutUsComponent {
  constructor(private aboutusdata : UpdabtusService , private imageService : ImageService) {}
urllink:string ="";

// selectFiles(event:any)
// {
//   if(event.target.files){
//     var reader = new FileReader()
//     reader.readAsDataURL(event.target.files[0])
//     reader.onload = (event:any) => {
//       this.urllink = event.target.result
//     }
//   }
// }
urllink1:string ="";
// selectFiles2(event:any)
// {
//   if(event.target.files){
//     var reader1 = new FileReader()
//     reader1.readAsDataURL(event.target.files[0])
//     reader1.onload = (event:any) => {
//       this.urllink1 = event.target.result
//     }
//   }
// }
submit(data: aboutusdata) {
  console.warn(data);
  this.aboutusdata.addata(data).subscribe((result)=> {
    console.log(result);
  })

}
changeImage() {
  this.imageService.changeImageSource('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSqgTBePNCAcvbvn-HBe8bAqe2HL4dlbAVNnmcfR7hZEg&s');
}

}
console.log("hello world");
