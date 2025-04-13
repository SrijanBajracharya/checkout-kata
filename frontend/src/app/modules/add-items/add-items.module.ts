import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AddItemsComponent } from './add-items.component';
import { HttpClientModule } from '@angular/common/http';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    AddItemsComponent,
    HttpClientModule,
    RouterModule.forChild([
      {
        path: '',
        component: AddItemsComponent, 
      },
    ]),
  ]
})
export class AddItemsModule { }
