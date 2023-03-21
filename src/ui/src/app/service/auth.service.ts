import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environment/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  authUrl: string = `${environment.baseUrl}/auth`;
  loggedIn: boolean = false;

  constructor(private http: HttpClient) { }

  login(email: string, password: string): Observable<any> {
    const payload = {email:email, password:password};
    console.log(payload);
    return this.http.post<any>(`${this.authUrl}/login`, JSON.stringify(payload), {headers: environment.headers});
  }

  logout(): void{
    this.http.post(`${this.authUrl}/logout`, null);
  }

  register(firstName: string, lastName: string, email: string, password: string, country: string): Observable<any> {
    const payload = {firstName: firstName, lastName: lastName, email: email, password: password, country: country};
    return this.http.post<any>(`${this.authUrl}/register`, JSON.stringify(payload), {headers: environment.headers});
  }
}
