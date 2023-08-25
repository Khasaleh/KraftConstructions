import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AddCareerNewsService } from 'src/app/service/add-career-news.service';

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
  errorMessage: string | null = null;
  successMessage: string | null = null;
  careerNewsForm!: FormGroup;
  status: boolean = false;
  sliderData: any;
  constructor(private formBuilder: FormBuilder, private careerNewsService: AddCareerNewsService) { }
  ngOnInit(): void {
    this.careerNewsForm = this.formBuilder.group({

      currentNews: ['', Validators.required],
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
      }
    )
  }
  buttonToggle() {
    this.careerNewsService.toggleButton().subscribe(
      response => {
      }
    )
  }
  onSubmit() {
    if (this.careerNewsForm.valid) {
      const currentNews = this.careerNewsForm.get('currentNews')?.value;
      const backgroundColor = this.selectedColor;
      const textColor = this.textColor
      const startDate = this.careerNewsForm.get('startDate')?.value;
      const endDate = this.careerNewsForm.get('endDate')?.value;
      const status = this.careerNewsForm.get('status')?.value;
      this.careerNewsService.careerNewsData(currentNews, backgroundColor, textColor, startDate, endDate, status).subscribe(
        response => {
          this.successMessage = "Career news added successfully";
          setTimeout(() => {

            this.successMessage = '';

          }, 2000);
        },
        error => {
          this.errorMessage = error?.message;
          setTimeout(() => {

            this.errorMessage = '';

          }, 2000);
        }
      )
    }

  }
}
