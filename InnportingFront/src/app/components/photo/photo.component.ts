import { Component, OnInit} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SearchService } from '../../services/search.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';


@Component({
  selector: 'app-photo',
  imports: [CommonModule], //Para usar *ngFor y *ngIf
  standalone: true,
  templateUrl: './photo.component.html',
  styleUrl: './photo.component.css'
})
export class PhotoComponent implements OnInit {

  photos: any = { photo: [] };
  loading: boolean = false;

  constructor(private route: ActivatedRoute,
    private searchService: SearchService,
    private router: Router
  ) {}

  //Eliminamos la foto del array
  removePhoto(index: number): void {
    this.photos.photo.splice(index, 1);
  }
  openPhotoDetails(photo: any): void {
    console.log('ID:', photo.id);
    this.router.navigate(['photoDetail/', photo.id]);
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      const query = params['query'];
      const page  = +params['page'] ;
      const size  = +params['size'] ;
      
      if (query) {
        this.loading = true;
        this.searchService.searchPhotos(query, page, size)
          .subscribe({
          next: (res) => {
            this.photos = res;
            this.loading = false;
            console.log('Resultado de las fotos:', this.photos);
            console.log('Página:', page);
            console.log('Cantidad fotos:', size);
            // Hacer scroll al inicio de la página después de cargar las fotos
            window.scrollTo({ top: 0, behavior: 'smooth' });
          },
          error: (err) => {
            console.error('Error al buscar fotos', err);
            this.loading = false;
          }
        });
      }
    });
  }

}
