import {Component, OnInit} from '@angular/core';
import {AlbumService} from '../../services/album.service';
import {SongService} from '../../../song/services/song.service';
import {ActivatedRoute} from '@angular/router';
import {albumDetails} from '../../models/albumDetails';

@Component({
  selector: 'app-album-details',
  standalone: false,

  templateUrl: './album-details.component.html',
  styleUrl: './album-details.component.css'
})
export class AlbumDetailsComponent implements OnInit{
  album: albumDetails | undefined;

  constructor(
    private albumService: AlbumService,
    private songService: SongService,
    private route: ActivatedRoute
  ) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      let albumId = params['id'];
      this.albumService.getAlbum(albumId).subscribe(album => {
        console.log('Album:', album);
        this.album = album;
        this.albumService.getSongsFromAlbum(albumId).subscribe(songs => {
          console.log('Songs:', songs);
          this.album!.songs = songs;
        });
      });
    });
  }



  delete(id: string) {
    this.songService.deleteSong(id).subscribe(() => {
      this.ngOnInit();
    });
  }

}
