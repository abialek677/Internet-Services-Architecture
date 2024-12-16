import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AlbumListComponent } from './album/view/album-list/album-list.component';
import { AlbumAddComponent } from './album/view/album-add/album-add.component';
import { AlbumEditComponent } from './album/view/album-edit/album-edit.component';
import { AlbumDetailsComponent } from './album/view/album-details/album-details.component';
import { SongAddComponent } from './song/view/song-add/song-add.component';
import {SongEditComponent} from './song/view/song-edit/song-edit.component';
import {SongViewComponent} from './song/view/song-view/song-view.component';
//import {SongEditComponent} from './song/view/song-edit/song-edit.component';
//import {SongDetailsComponent} from './song/view/song-details/song-details.component';
//import {SongListComponent} from './song/view/song-list/song-list.component';

const routes: Routes = [
  { path: 'albums', component: AlbumListComponent },
  { path: 'albums/add', component: AlbumAddComponent },
  { path: 'albums/edit/:id', component: AlbumEditComponent },
  { path: 'albums/:id', component: AlbumDetailsComponent },

  // Song Routes for a specific album
  { path: 'albums/:albumId/add', component: SongAddComponent },
  { path: 'albums/:albumId/songs/:songId/edit', component: SongEditComponent },
  { path: 'albums/:albumId/songs/:songId', component: SongViewComponent },  // Song Details page
//  { path: 'albums/:albumId/songs', component: SongListComponent },  // Song list page for an album

  { path: '', redirectTo: '/albums', pathMatch: 'full' },
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
