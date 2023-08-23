import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  apiURL: string = 'http://localhost:8080/api/order';
  apiURLForAllOrders: string = 'http://localhost:8080/api/orders';

  constructor(private http: HttpClient) { }

  createOrder(order: any): Observable<any> {
    return this.http.post(this.apiURL, order);
  }

  getAllOrders(): Observable<any> {
    return this.http.get(this.apiURLForAllOrders);
  }
}
