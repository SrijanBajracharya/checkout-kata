import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScanItemComponent } from './scan-item.component';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CheckoutService } from '../../services/checkout.service';

describe('ScanItemComponent', () => {
  let component: ScanItemComponent;
  let fixture: ComponentFixture<ScanItemComponent>;
  let checkoutService: jasmine.SpyObj<CheckoutService>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ScanItemComponent, CommonModule, ReactiveFormsModule, HttpClientModule],
      providers: [{ provide: CheckoutService, useValue: {} }]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ScanItemComponent);
    component = fixture.componentInstance;
    checkoutService = TestBed.inject(CheckoutService) as jasmine.SpyObj<CheckoutService>;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
