import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ItemService } from '../../services/item.service';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-add-items',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './add-items.component.html',
  styleUrl: './add-items.component.scss',
  providers: [ItemService],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class AddItemsComponent implements OnInit{

  itemForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private itemService: ItemService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.itemForm = this.fb.group({
      name: ['', Validators.required],
      unitPrice: [null, [Validators.required, Validators.min(1)]],
      hasOffer: [false],
      offer: this.fb.group({
        quantity: [null],
        discountPrice: [null]
      })
    });

    this.toggleOfferFields(false);

    this.itemForm.get('hasOffer')?.valueChanges.subscribe((hasOffer) => {
      this.toggleOfferFields(hasOffer);
    });
  }

  toggleOfferFields(enable: boolean) {
    const offerGroup = this.itemForm.get('offer') as FormGroup;
    if (enable) {
      offerGroup.get('quantity')?.setValidators([Validators.required, Validators.min(1)]);
      offerGroup.get('discountPrice')?.setValidators([Validators.required, Validators.min(0)]);
    } else {
      offerGroup.get('quantity')?.clearValidators();
      offerGroup.get('discountPrice')?.clearValidators();
      offerGroup.reset();
    }
    offerGroup.get('quantity')?.updateValueAndValidity();
    offerGroup.get('discountPrice')?.updateValueAndValidity();
  }

  onSubmit() {
    if (this.itemForm.invalid) return;

    const { name, unitPrice, hasOffer, offer } = this.itemForm.value;
    const payload = hasOffer
      ? { name, unitPrice, offer }
      : { name, unitPrice };

    this.itemService.addItem(payload).subscribe({
      next: () => {
        this.itemForm.reset();
        this.toggleOfferFields(false);
      },
      error: (err) => {
        console.error(err);
        alert('Failed to add item.');
      }
    });
  }

  onScanButton() {
    this.router.navigate(['/checkout']);
  }
  
}
