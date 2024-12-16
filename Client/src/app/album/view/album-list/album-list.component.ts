import { Component } from '@angular/core';
import { AlbumService } from '../../services/album.service';
import { albums } from '../../models/albums';

@Component({
  selector: 'app-album-list',
  standalone: false,
  templateUrl: './album-list.component.html',
  styleUrls: ['./album-list.component.css'], // Fixing the typo from styleUrl to styleUrls
})
export class AlbumListComponent {
  albums: albums | undefined;

  constructor(private service: AlbumService) {}

  ngOnInit() {
    this.service.getAlbums().subscribe({
      next: (albums) => {
        console.log('Albums fetched successfully:', albums);
        this.albums = albums;
      },
      error: (error) => {
        console.error('Error fetching albums:', error);
      },
    });
  }

  onDelete(id: string) {
    this.service.deleteAlbum(id).subscribe(() => {
      this.ngOnInit();
    });
  }
}
