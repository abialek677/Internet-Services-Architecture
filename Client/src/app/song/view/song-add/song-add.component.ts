import { Component, OnInit } from '@angular/core';
import { SongService } from '../../services/song.service';
import { ActivatedRoute, Router } from '@angular/router';
import { SongForm } from '../../model/songForm';

@Component({
  selector: 'app-song-add',
  standalone: false,
  templateUrl: './song-add.component.html',
  styleUrls: ['./song-add.component.css']
})
export class SongAddComponent implements OnInit {
  song: SongForm = { title: '', duration: '', albumId: '' };

  constructor(
    private songService: SongService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.song.albumId = params['albumId'];
    });
  }

  submit(): void {
    this.songService.postSongForAlbum(this.song.albumId, this.song).subscribe(() => {
      this.router.navigate([`/albums/${this.song.albumId}`]);
    });
  }

  cancel(): void {
    this.router.navigate([`/albums/${this.song.albumId}`]); // Redirect to album details
  }
}
