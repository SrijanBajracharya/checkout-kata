import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path:'',
    pathMatch:'full',
    loadChildren:()=>
      import('./modules/add-items/add-items.module').then((m) => m.AddItemsModule)
  },
  {
    path:'checkout',
    pathMatch:'full',
    loadChildren:()=>
      import('./modules/scan-item/scan-item.module').then((m) => m.ScanItemModule)
  }
];
