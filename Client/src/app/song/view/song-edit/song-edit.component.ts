import { Component, OnInit } from '@angular/core';
import { SongService } from '../../services/song.service';
import { ActivatedRoute, Router } from '@angular/router';
import { SongForm } from '../../model/songForm';
import { albums } from '../../../album/models/albums';

@Component({
  selector: 'app-song-edit',
  standalone: false,
  templateUrl: './song-edit.component.html',
  styleUrls: ['./song-edit.component.css']
})
export class SongEditComponent implements OnInit {
  uuid: string | undefined;
  song: SongForm | undefined;
  original: SongForm | undefined;
  albums: albums | undefined;

  constructor(
    private songService: SongService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.uuid = params['songId'];
      this.songService.getSong(this.uuid).subscribe(song => {
        this.song = song;
        this.song.albumId = params['albumId'];
        this.original = JSON.parse(JSON.stringify(song));
      });
    });
  }

  submit(): void {
    if (this.song && this.uuid) {
      this.songService.putSongForAlbum(this.song.albumId, this.uuid, this.song).subscribe(() => {
        this.router.navigate([`/albums/${this.song!.albumId}`]);
      });
    }
  }
}
