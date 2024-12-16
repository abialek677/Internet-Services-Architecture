import {Component, OnInit} from '@angular/core';
import {AlbumService} from '../../services/album.service';
import {Router} from '@angular/router';
import {albumForm} from '../../models/albumForm';

@Component({
  selector: 'app-album-add',
  standalone: false,

  templateUrl: './album-add.component.html',
  styleUrl: './album-add.component.css'
})
export class AlbumAddComponent implements OnInit{
  album: albumForm = { title : '', author : ''};

  constructor(
    private service: AlbumService,
    private router: Router
  ) {
  }

  ngOnInit() {
  }

  submit() {
    this.service.postAlbum(this.album).subscribe(() => {
      this.router.navigate(['/albums']);
    });
  }

  cancel() {
    this.router.navigate(['/albums']);
  }
}
