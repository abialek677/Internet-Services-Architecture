import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AlbumService} from '../../services/album.service';
import {albumForm} from '../../models/albumForm';

@Component({
  selector: 'app-album-edit',
  standalone: false,

  templateUrl: './album-edit.component.html',
  styleUrl: './album-edit.component.css'
})
export class AlbumEditComponent implements OnInit{
  uuid: string | undefined;

  album: albumForm | undefined;

  original: albumForm | undefined;

  constructor(
    private service: AlbumService,
    private router: Router,
    private route: ActivatedRoute
  ) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.service.getAlbum(params['id']).subscribe(album => {
        this.uuid = album.id;
        this.album = {
          title: album.title,
          author: album.author,
        };
        this.original = { ...this.album };
      });
    });
  }

  submit() {
    this.service.putAlbum(this.uuid, this.album).subscribe(() => {
      this.router.navigate(['/albums']);
    });
  }
}
