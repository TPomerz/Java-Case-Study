import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CarouselComponent } from './carousel/carousel.component';
import { CartComponent } from './cart/cart.component';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { HomeItemComponent } from './home-item/home-item.component';
import { OrderComponent } from './order/order.component';
import { OrderDetailsComponent } from './order-details/order-details.component';
import { ProductComponent } from './product/product.component';
import { UserProfileComponent } from './user-profile/user-profile.component';

@NgModule({
  declarations: [
    AppComponent,
    CarouselComponent,
    CartComponent,
    HeaderComponent,
    HomeComponent,
    HomeItemComponent,
    OrderComponent,
    OrderDetailsComponent,
    ProductComponent,
    UserProfileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
