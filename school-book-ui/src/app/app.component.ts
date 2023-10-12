import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './data';
import { DataService } from './data.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'school-book-ui';
  public user = new User('', '');

  public constructor(private dataService: DataService) {}

  public login(username: string, password: string): void {
    this.dataService.login(username,password).subscribe(user => {
      this.user.username = user.username;
      this.user.role = user.role;
    });
  }
}
