import { Component, NgModule, ViewChild } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { SearchService } from '../services/search.service';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

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
  hasSearched = false;
  
  constructor(private router: Router) {}
  
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
    this.router.navigate(['/search'], {
      queryParams: {
        query: this.searchQuery,
        page: this.page,
        size: this.size
      }
    });
  }
}
