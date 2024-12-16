import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {Songs} from '../model/songs';
import {HttpClient} from '@angular/common/http';
import {SongDetails} from '../model/songDetails';
import {SongForm} from '../model/songForm';

@Injectable()
export class SongService {
  baseUrl = '/api/songs';
  constructor(private http: HttpClient) { }

  getSongs(): Observable<Songs> {
    return this.http.get<Songs>(this.baseUrl);
  }

  getSong(uuid: string | undefined): Observable<SongDetails> {
    return this.http.get<SongDetails>(this.baseUrl + '/' + uuid);
  }

  deleteSong(uuid: string): Observable<any> {
    return this.http.delete(this.baseUrl + '/' + uuid);
  }

  putSong(uuid: string, request: SongForm): Observable<any> {
    return this.http.put(this.baseUrl + '/' + uuid, request);
  }

  postSong(request: SongForm): Observable<any> {
    return this.http.post(this.baseUrl, request);
  }

  postSongForAlbum(uuid: string, request: SongForm): Observable<any> {
    return this.http.post(this.baseUrl + '/' + uuid, request);
  }

  putSongForAlbum(albumId: string, songId: string, request: SongForm): Observable<any> {
    return this.http.put(this.baseUrl + '/' + albumId + '/edit/' + songId, request);
  }
}
