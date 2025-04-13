import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ItemPayload } from '../interface/item.interface';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CheckoutService {
  private readonly baseUrl = '/api/v1/checkout';

  constructor(private http: HttpClient) {}

  checkout(items: any[]): Observable<any> {
    return this.http.post(this.baseUrl, { items });
  }
}
