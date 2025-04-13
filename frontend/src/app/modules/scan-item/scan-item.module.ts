import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ScanItemComponent } from './scan-item.component';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    ScanItemComponent,
    HttpClientModule,
    RouterModule.forChild([
      {
        path: '',
        component: ScanItemComponent, 
      },
    ]),
  ]
})
export class ScanItemModule { }
