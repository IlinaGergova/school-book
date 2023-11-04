import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './data';
import { HttpClient } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(
    private http: HttpClient,
    private cookieService: CookieService,
  ) { }

  public login(username: string, password: string): Observable<User> {
    return this.http.post<User>(
      'http://localhost:8081/user/login',
      { username: username, password: password },
      { headers: {
        'X-CSRFToken': this.cookieService.get('csrftoken'),
      }}
    );
  }
}
