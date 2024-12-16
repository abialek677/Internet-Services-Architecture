import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AlbumListComponent } from './album/view/album-list/album-list.component';
import { AlbumAddComponent } from './album/view/album-add/album-add.component';
import { AlbumEditComponent } from './album/view/album-edit/album-edit.component';
import { AlbumDetailsComponent } from './album/view/album-details/album-details.component';
import { SongAddComponent } from './song/view/song-add/song-add.component';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {AlbumService} from './album/services/album.service';
import {SongService} from './song/services/song.service';
import { SongEditComponent } from './song/view/song-edit/song-edit.component';
import { SongViewComponent } from './song/view/song-view/song-view.component';

@NgModule({
  declarations: [
    AppComponent,
    AlbumListComponent,
    AlbumAddComponent,
    AlbumEditComponent,
    AlbumDetailsComponent,
    SongAddComponent,
    SongEditComponent,
    SongViewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [AlbumService, SongService],
  bootstrap: [AppComponent]
})
export class AppModule { }
