import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
   styleUrls: ['./home.component.css',
   '../../../styles.css'
]
})
export class HomeComponent {
  slides: string[] = ['../../../assets/banner.png', '../../../assets/testimonial.png', '../../../assets/career-banner.png'];
  currentSlide = 0;

  ngOnInit() {
    setInterval(() => this.showSlide(), 5000);
  }

  showSlide() {
    this.currentSlide = (this.currentSlide + 1) % this.slides.length;
  }
}
