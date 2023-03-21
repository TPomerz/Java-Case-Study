import { Component, OnInit } from '@angular/core';
import { Product } from '../model/product';
import { Subscription } from 'rxjs';
import { ProductService } from '../service/product.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  products: {
    product: Product,
    quantity: number
  }[] = [];
  totalPrice: number = 0;
  cartProducts: Product[] = [];
  subscription!: Subscription;
  userId!: number;

  constructor(private productService: ProductService, private router: Router) {}

  /*
  * retrieves all the carts associated with a user and calculates the total price
  */
  ngOnInit(): void {
    this.productService.getUserId().subscribe((id)=> {
      this.productService.getCart2(id).subscribe((data: any)=>{
         data.forEach(
          (element: any)=> {
            this.productService.getSingleCartProduct(element.productId).subscribe((data2: any) =>{
              this.products.push({product: data2, quantity: element.quantity});
              this.totalPrice += data2.price *element.quantity;
            });
          }
          )
        }), this.userId = id;
      }
    );
  }

  /*
  * deletes a cart item
  * @param productId
  */
  delete(product_id: number): void{
    this.productService.removeCartItem(this.userId, product_id).subscribe(()=>{
      window.location.reload(); 
    });
    
  }

  /*
  * empties the cart
  */
  emptyCart(): void {
    this.productService.emptyCart(this.userId).subscribe(()=>{});
    this.router.navigate(['/home']);
  }
}
