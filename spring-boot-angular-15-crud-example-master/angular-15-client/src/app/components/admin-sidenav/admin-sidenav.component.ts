import { Component, ViewChild, ElementRef } from '@angular/core';

@Component({
  selector: 'app-admin-sidenav',
  templateUrl: './admin-sidenav.component.html',
  styleUrls: ['./admin-sidenav.component.css', '../../../styles.css']
})
export class AdminSidenavComponent {

  isDropdownOpen = false;
  isDropdown2Open = false;
  isDropdown3Open = false;
  isDropdown4Open = false;

  toggleDropdown(): void {
    this.isDropdownOpen = !this.isDropdownOpen;
  }
  toggleDropdown2(): void {
    this.isDropdown2Open = !this.isDropdown2Open;
  }
  toggleDropdown3(): void {
    this.isDropdown3Open = !this.isDropdown3Open;
  }
  toggleDropdown4(): void {
    this.isDropdown4Open = !this.isDropdown4Open;
  }
}
