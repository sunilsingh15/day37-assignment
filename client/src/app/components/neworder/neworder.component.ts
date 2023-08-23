import { Component, OnInit, inject } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { OrderService } from 'src/app/order.service';

@Component({
  selector: 'app-neworder',
  templateUrl: './neworder.component.html',
  styleUrls: ['./neworder.component.css']
})
export class NeworderComponent implements OnInit {

  orderForm!: FormGroup;
  orderDetails!: FormArray;
  orderID: string = '';

  builder = inject(FormBuilder);
  service = inject(OrderService);
  router = inject(Router);

  ngOnInit(): void {
    this.orderForm = this.initializeForm();
  }

  initializeForm() {
    this.orderDetails = this.builder.array([]);

    return this.builder.group({
      name: this.builder.control<string>('', [Validators.required, Validators.minLength(3)]),
      email: this.builder.control<string>('', [Validators.required, Validators.email]),
      express: this.builder.control<boolean>(false),
      items: this.orderDetails
    });
  }

  processOrder() {
    console.log(this.orderForm.value);

    this.service.createOrder(this.orderForm.value).subscribe((result) => {
      this.orderID = result.success;
      this.router.navigate(['order', this.orderID]);
    })
  }

  addOrderItem() {
    this.orderDetails.push(
      this.builder.group({
        name: this.builder.control<string>('', [Validators.required]),
        quantity: this.builder.control<number>(1, [Validators.required, Validators.min(1)]),
        unit_price: this.builder.control<number>(0.5, [Validators.required, Validators.min(0.01)])
      })
    )
  }

  removeOrderItem(index: number) {
    this.orderDetails.removeAt(index);
  }

  checkIfValid(): boolean {
    return this.orderForm.invalid || this.orderDetails.controls.length <= 0;
  }

}
