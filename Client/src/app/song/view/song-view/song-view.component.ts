import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SongService } from '../../services/song.service';
import { SongDetails } from '../../model/songDetails';

@Component({
  selector: 'app-song-view',
  standalone: false,
  templateUrl: './song-view.component.html',
  styleUrls: ['./song-view.component.css']
})
export class SongViewComponent implements OnInit {
  song: SongDetails | undefined;

  constructor(
    private songService: SongService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const songId = params['songId'];
      this.songService.getSong(songId).subscribe({
        next: (song) => {
          this.song = song;
          this.song.albumId = params['albumId'];
        },
        error: (err) => {
          console.error('Error fetching song:', err);
        }
      });
    });
  }
}
