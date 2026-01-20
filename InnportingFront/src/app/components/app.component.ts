import { Component, NgModule, ViewChild } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { SearchService } from '../services/search.service';
import { FormsModule } from '@angular/forms';
import { Router, NavigationEnd } from '@angular/router';
import { CommonModule } from '@angular/common';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, FormsModule, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  
  searchQuery: string = '';
  size: number = 10;
  page: number = 1;
  hasSearched: boolean = false;
  isDetail: boolean = false;
  
  constructor(private router: Router) {

    // Detectamos cambios de ruta para saber si estamos en detalle o no para el btn de atrás
    this.router.events
      .pipe(filter(event => event instanceof NavigationEnd))
      .subscribe((event: any) => {
        const url = event.url.split('?')[0]; // Obtener la URL sin los parámetros de consulta
        if(url.startsWith('/photo')) {
          this.isDetail = true;
        } else {
          this.isDetail = false; 
        }
      });
  }
  
  // Obtener el valor del input de búsqueda
  getInputSearch(event: Event): void {
    const input = event.target as HTMLInputElement; 
    this.searchQuery = input.value;
    console.log("Valor del input de búsqueda: "+this.searchQuery);
    this.page = 1; // Reseteamos el valor de la pagina.
  }
  getValueOtions(event: Event): void {
    const select = event.target as HTMLSelectElement; 
    this.size = parseInt(select.value,10);
    console.log("Valor del select de tamaño: "+this.size);
  }

  nextPage(): void {
    this.page++;
    this.search();
    
  }

  prevPage(): void {
    if (this.page > 1) {
      this.page--;
      this.search();
    }
  }
  goBack(): void {
    this.router.navigate(['/search'], {
      queryParams: {
        query: this.searchQuery,
        page: this.page,
        size: this.size
      }
    });
  }

  /*
  En vez de hacer que el size se guarde al cambiar el valor del select
  hacemos que lo guarde al hacer la búsqueda.
  (Sino cambiabas el select y al darle siguiente/anterior buscaba con el valor anterior)
  */
  search(sizeFromSelect?:string):void{

    if (sizeFromSelect) {
      this.size = parseInt(sizeFromSelect, 10);
    }

    console.log("Buscando fotos con query: "+this.searchQuery);
    console.log("Numero de fotos en la pagina "+this.size);
    console.log("Estado de busqueda "+this.hasSearched);
    //Si es vacío o null que no haga nada
    if(this.searchQuery.trim().length === 0 || !this.searchQuery){
      return;
    }
    this.hasSearched = true;
    this.isDetail = false;
    this.router.navigate(['/search'], {
      queryParams: {
        query: this.searchQuery,
        page: this.page,
        size: this.size
      }
    });
  }
}
