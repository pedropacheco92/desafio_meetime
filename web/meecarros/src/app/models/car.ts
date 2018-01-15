import { IPerson } from './person';
export interface ICar {
    id: number;
    modelo: string;
    ano: string; // apenas carros com menos de 30 anos são aceitos
    cor: string; // obrigatoriamente deve ser branco, preto ou verde    
    personId: number;
}
