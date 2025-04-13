import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ItemPayload } from '../interface/item.interface';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ItemService {
  private readonly baseUrl = '/api/v1/items';

  constructor(private http: HttpClient) {}

  addItem(item: ItemPayload): Observable<any> {
    return this.http.post(this.baseUrl, item);
  }

  getItems(): Observable<string[]> {
    return this.http.get<string[]>(this.baseUrl);
  }
}
