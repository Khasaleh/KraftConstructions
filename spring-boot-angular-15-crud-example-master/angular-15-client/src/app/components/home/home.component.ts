import { Component, OnInit } from '@angular/core';
import { HomeServiceService } from '../../service/home-service.service'
import { data } from 'jquery';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css',
    '../../../styles.css'
  ]
})
export class HomeComponent implements OnInit {
  slides: string[] = [];
  currentSlide = 0;
  showSlide() {
    console.log(this.currentSlide);
    console.log(this.slides[this.currentSlide])
    if (this.currentSlide == this.slides.length - 1) {
      this.currentSlide = 0;
    }
    else {
      this.currentSlide = this.currentSlide + 1;
    }
  }
  apiData: any;
  sliderData: any;
  bannerData: any;
  globalUrl = 'https://img.kraftconstructionco.com';
  bannerDescription: any;
  objects: any[] = [];
  activeSlideIndex: number = 0;
  serviceData: any;
  constructor(private homeService: HomeServiceService) { }
  ngOnInit(): void {
    this.homeService.getHomePageBanner().subscribe(
      response => {
        if (response) {
          this.slides = response.map((p: { id: any, imageUrl: any; }) => this.globalUrl + p.imageUrl.replace(' ', '%20'));
          setInterval(() =>
            this.showSlide(),
          3000);
        }
      },
    )

    this.homeService.getHomePageData().subscribe(
      data => {
        this.apiData = data;
      },
    )
    this.homeService.getsliderdata().subscribe(
      data => {
        this.sliderData = data;
      },
    );
    this.homeService.getTestimonialsData().subscribe(
      response => {
        this.objects = response;
      }
    )
    this.homeService.getHomeBannerDescription().subscribe(
      response => {
        this.bannerDescription = response
      }
    )
    this.getSrviceData();
  }
  getSrviceData() {
    this.homeService.getServicesData().subscribe(
      response => {
        this.serviceData = response;
      }
    )
  }
  setActiveSlide(index: number) {
    this.activeSlideIndex = index;
  }
}
