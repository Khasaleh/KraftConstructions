import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AddCareerNewsService } from 'src/app/service/add-career-news.service';
// import { ColorPickerControl } from 'ngx-color-picker';

@Component({
  selector: 'app-admin-add-careers-news',
  templateUrl: './admin-add-careers-news.component.html',
  styleUrls: ['./admin-add-careers-news.component.css']
})
export class AdminAddCareersNewsComponent {
  selectedColor: string = '';
  textColor: string = '';
  isCardBodyVisible: boolean = false;
  toggleCardBody() {
    this.isCardBodyVisible = !this.isCardBodyVisible;
    this.buttonToggle();
  }
  careerNewsForm!: FormGroup;
  status: boolean = false;
  sliderData: any;
  // backgroundColorControl!: FormControl;
  constructor(private formBuilder: FormBuilder, private careerNewsService: AddCareerNewsService) { }
  ngOnInit(): void {
    // this.backgroundColorControl = new FormControl('');
    this.careerNewsForm = this.formBuilder.group({

      currentNews: ['', Validators.required],
      // backgroundColor: ['', Validators.required],

      // backgroundColor: this.backgroundColorControl,
      // textColor: ['', Validators.required],
      startDate: ['', Validators.required],
      endDate: ['', Validators.required],
      status: [this.status]
    });
    this.careerNewsService.getsliderdata().subscribe(
      previousValue => {
        this.careerNewsForm.controls['status'].setValue(previousValue.status);
        this.selectedColor = previousValue.backgroundColor;
        this.careerNewsForm.controls['currentNews'].setValue(previousValue.description);
        this.textColor = previousValue.textColor;
        this.isCardBodyVisible = previousValue.status;
        this.careerNewsForm.controls['startDate'].setValue(previousValue.startDate);
        this.careerNewsForm.controls['endDate'].setValue(previousValue.endDate);
      

        console.log(previousValue, "pervious value");

      },
      error => {
        console.error(error, "pervious value error");
      },
    )

  }
  buttonToggle() {
    this.careerNewsService.toggleButton().subscribe(
      response => {

        console.log("response for toggle", response)
      },
      error => {
        // Handle any error that occurs during the API request
        console.error(error);
      }
    )
  }
  onSubmit() {
    console.log(this.selectedColor, "backgroud color");
    console.log(this.textColor, "text color");
    console.log(this.careerNewsForm, "form");


    if (this.careerNewsForm.valid) {
      const currentNews = this.careerNewsForm.get('currentNews')?.value;
      const backgroundColor = this.selectedColor;
      const textColor = this.textColor

      // const backgroundColor = this.careerNewsForm.get('backgroundColor')?.value;
      // const textColor = this.careerNewsForm.get('textColor')?.value;
      const startDate = this.careerNewsForm.get('startDate')?.value;
      const endDate = this.careerNewsForm.get('endDate')?.value;
      const status = this.careerNewsForm.get('status')?.value;
      this.careerNewsService.careerNewsData(currentNews, backgroundColor, textColor, startDate, endDate, status).subscribe(
        response => {
          // Handle the API response here
          console.log("data submit successfully");
          console.log("response", response)
        },
        error => {
          // Handle any error that occurs during the API request
          console.error(error);
        }
      )
    }

  }
}
