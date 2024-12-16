import {Song} from '../../song/model/song';

export interface albumDetails {
    id: string;
    title: string;
    author: string;
    songs: Song[];
}
