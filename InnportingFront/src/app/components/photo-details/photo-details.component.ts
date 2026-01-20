import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SearchService } from '../../services/search.service';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-photo-details',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './photo-details.component.html',
  styleUrl: './photo-details.component.css'
})
export class PhotoDetailsComponent implements OnInit {

  photo: any;
  photoUrl: string = "";
  loading = false;

  constructor(
    private route: ActivatedRoute,
    private searchService: SearchService
  ) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');

    if (!id){
      console.error('No se proporcionó ID de la foto');
      return;
    }else{
      this.loading = true;
      this.searchService.getPhotoById(id).subscribe({
        next: (res) => {
          this.photo = res;
          this.loading = false;
          this.photoUrl= `https://live.staticflickr.com/${this.photo.server}/${this.photo.id}_${this.photo.secret}_b.jpg`;
          console.log('Foto detalle:', this.photo);
          console.log('Foto url', this.photoUrl);
        },
        error: (err) => {
          console.error('Error al cargar detalle', err);
          this.loading = false;
        }
      });
    }
  }

  downloadPhoto(url: string, title: string): void {
    fetch(url)
      .then(res => res.blob()) // Convertimos la respuesta a un blob(objeto de tipo archivo)
      .then(blob => {
        const link = document.createElement('a');
        link.href = URL.createObjectURL(blob); 
        link.download = title || 'photoFlickr.jpg'; // Nombre del archivo
        document.body.appendChild(link);//Agregamos el link al dom 
        link.click();
        document.body.removeChild(link);//Lo quitamos del dom
      })
      .catch(err => console.error('Error al descargar la foto', err));
  }

}
