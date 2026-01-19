import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PhotoList } from '../interface/photo-list';

@Injectable({
  providedIn: 'root' 
})
export class SearchService {

  private api_url_search = "http://localhost:8080/api/images/search"

  constructor(private http: HttpClient) { }

  searchPhotos(query: string, page: number, size: number): Observable<PhotoList> {
    const params = new HttpParams()
      .set('query', query)
      .set('page', page.toString())
      .set('size', size.toString());

    return this.http.get<PhotoList>(this.api_url_search, { params });
  }
}
