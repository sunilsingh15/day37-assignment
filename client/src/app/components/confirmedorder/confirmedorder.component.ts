import { Component, OnInit, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-confirmedorder',
  templateUrl: './confirmedorder.component.html',
  styleUrls: ['./confirmedorder.component.css']
})
export class ConfirmedorderComponent implements OnInit {

  orderID: string = '';

  activatedRoute = inject(ActivatedRoute);

  ngOnInit(): void {
    this.orderID = this.activatedRoute.snapshot.params['id'];
  }

}
