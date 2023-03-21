import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable} from 'rxjs';
import { Product } from '../model/product';
import { environment } from 'src/environment/environment';
import { EMPTY } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private productUrl: string = "/product";

  constructor(private http: HttpClient) { }

  public getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(environment.baseUrl+this.productUrl, {headers: environment.headers});
  }

  public getSingleProduct(id: number): Observable<Product> {
    return this.http.get<Product>(environment.baseUrl+this.productUrl+'/'+id, {headers: environment.headers});
  }

  public purchase(products: {id:number, quantity:number}[]): Observable<any> {
    const payload = JSON.stringify(products);
    return this.http.patch<Product[]>(environment.baseUrl+this.productUrl, payload, {headers: environment.headers}
    );
  }

  public getCart2(id: number): Observable<Product[]>{
    let queryParams = new HttpParams();
    queryParams = queryParams.append("userId", id);
    return this.http.get<any>(environment.baseUrl+this.productUrl+"/cart", {params:queryParams, headers: environment.headers, withCredentials: environment.withCredentials});
  }

  // adds a product to the cart
  public addCart(userId: number, productId: number, quantity: number){
    let queryParams = new HttpParams();
    queryParams = queryParams.append("userId", userId);
    queryParams = queryParams.append("prodId", productId);
    queryParams = queryParams.append("quantity", quantity);
    return this.http.post<any>(environment.baseUrl+this.productUrl+"/cart",{}, {params:queryParams, headers: environment.headers, withCredentials: environment.withCredentials});
  }

  // retrieves a single cart product
  public getSingleCartProduct(id: number): Observable<Product> {
    return this.http.get<Product>(environment.baseUrl+this.productUrl+"/cart"+"/"+id, {headers: environment.headers, withCredentials: environment.withCredentials});
  }

  // empties the cart
  public emptyCart(id: number){
    let queryParams = new HttpParams();
    queryParams = queryParams.append("userId", id);
    console.log('empty cart');
    return this.http.delete<any>(environment.baseUrl + this.productUrl + "/cart", {params: queryParams, headers: environment.headers, withCredentials: environment.withCredentials})
  }

  // removes a cart item
  public removeCartItem(userId: number, productId: number){
    let queryParams = new HttpParams();
    queryParams = queryParams.append("prodId", productId);
    return this.http.delete<any>(environment.baseUrl+this.productUrl+"/cart" + "/" + userId, {params: queryParams, headers: environment.headers, withCredentials: environment.withCredentials});
  }

  // retrives the userid from http session
  public getUserId(){
    return this.http.get<number>(environment.baseUrl+this.productUrl+"/cart" + "/user", {headers: environment.headers, withCredentials: environment.withCredentials});
  }
}
