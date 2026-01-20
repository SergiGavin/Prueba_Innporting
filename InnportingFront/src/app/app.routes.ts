import { Routes } from '@angular/router';
import { AppComponent } from './components/app.component';
import { PhotoComponent } from './components/photo/photo.component';
import { PhotoDetailsComponent } from './components/photo-details/photo-details.component';

export const routes: Routes = [

{
    path:'search',
    component: PhotoComponent
},
{
    path:'photoDetail/:id',
    component: PhotoDetailsComponent
}

];
