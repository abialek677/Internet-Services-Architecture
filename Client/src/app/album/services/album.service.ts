import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {album} from '../models/album';
import {albums} from '../models/albums';
import {albumDetails} from '../models/albumDetails';
import {albumForm} from '../models/albumForm';

@Injectable({
  providedIn: 'root'
})
export class AlbumService {
  baseUrl = '/api/albums';
  constructor(private http: HttpClient ) { }

  getAlbums() : Observable<albums> {
    return this.http.get<albums>(this.baseUrl);
  }

  getAlbum(uuid: string | undefined) : Observable<albumDetails> {
    return this.http.get<albumDetails>(this.baseUrl + '/' + uuid);
  }

  deleteAlbum(uuid: string) : Observable<any> {
    return this.http.delete(this.baseUrl + '/' + uuid);
  }

  putAlbum(uuid: string | undefined, request: albumForm | undefined) : Observable<any> {
    return this.http.put(this.baseUrl + '/' + uuid, request);
  }

  postAlbum(request: albumForm) : Observable<any> {
    return this.http.post(this.baseUrl, request);
  }

  getSongsFromAlbum(uuid: string | undefined) : Observable<any> {
    return this.http.get(this.baseUrl + '/songs/' + uuid);
  }
}
