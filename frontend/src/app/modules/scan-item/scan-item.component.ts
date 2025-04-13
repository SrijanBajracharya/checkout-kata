import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CheckoutService } from '../../services/checkout.service';
import { ItemService } from '../../services/item.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-scan-item',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './scan-item.component.html',
  styleUrl: './scan-item.component.scss',
  providers: [],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ScanItemComponent implements OnInit{

  checkoutForm: FormGroup;
  scannedItems: any[] = [];
  checkoutResponse: any = null;
  items$!: Observable<string[]>;

  constructor(
    private fb: FormBuilder, 
    private readonly checkoutService: CheckoutService, 
    private readonly itemService: ItemService,
    private cdr: ChangeDetectorRef
  ) {
    this.checkoutForm = this.fb.group({
      itemName: ['', Validators.required],
      quantity: [1, [Validators.required, Validators.min(1)]]
    });
  }

  ngOnInit(): void {
    this.items$ = this.itemService.getItems();
  }

  onScan() {
    if (this.checkoutForm.valid) {
      const item = this.checkoutForm.value;
      this.scannedItems.push(item);
  
      this.checkoutService.checkout(this.scannedItems).subscribe(response => {
        this.checkoutResponse = response;
      });
  
      this.checkoutForm.reset({ quantity: 1 });
    }
  }

  onCheckout() {
    this.checkoutService.checkout(this.scannedItems).subscribe(response => {
      this.checkoutResponse = response;
    });
  }

}
