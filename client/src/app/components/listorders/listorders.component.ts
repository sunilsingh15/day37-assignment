import { Component, OnInit, inject } from '@angular/core';
import { OrderService } from 'src/app/order.service';

@Component({
  selector: 'app-listorders',
  templateUrl: './listorders.component.html',
  styleUrls: ['./listorders.component.css']
})
export class ListordersComponent implements OnInit {

  service = inject(OrderService);
  orders: any[] = [];

  ngOnInit(): void {
    this.service.getAllOrders().subscribe((result) => {
      for (let index = 0; index < result.length; index++) {
        const order = result[index];
        this.orders.push(order);
      }
    })
  }


}
