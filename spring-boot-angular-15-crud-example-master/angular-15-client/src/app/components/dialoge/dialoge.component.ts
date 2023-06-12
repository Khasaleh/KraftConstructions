import { Component } from '@angular/core';
import {Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
@Component({
  selector: 'app-dialoge',
  templateUrl: './dialoge.component.html',
  styleUrls: ['./dialoge.component.css']
})

export class DialogeComponent {

    constructor(
      public dialogRef: MatDialogRef<DialogeComponent>,
      @Inject(MAT_DIALOG_DATA) public data: any
    ) {}
}



