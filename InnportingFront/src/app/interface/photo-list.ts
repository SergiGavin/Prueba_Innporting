import { Photo } from "./photo";

export interface PhotoList {
    id: string;
    url: string;
    photo: Photo[];
}
