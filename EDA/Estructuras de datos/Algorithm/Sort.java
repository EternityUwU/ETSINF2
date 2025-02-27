package Algorithm;

import java.util.Random;

public class Sort<T>{
    public static <T extends Comparable<T>> void insertionSort(T[] v){
        int i, j; 
        for (i = 1; i < v.length; i++){
            T elem = v[i];
            for (j = i; j > 0 && elem.compareTo(v[j-1]) < 0; j--)
                v[j] = v [j -1];
            v[j] = elem;
        }
    } 
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> void merge(T[] v, int ini, int half, int fin){
        int i = ini;
        int j = half+1;
        int k = 0;

        T[] aux = (T[]) new Comparable[fin - ini +1];

        while(i<= half && j <= fin){
            if (v[i].compareTo(v[j]) < 0)
                aux[k++] = v [i++];
            else
                aux[k++] = v [j++];
        
        }
        while (i <= half)
            aux[k++] = v[i++];

        while (j <= fin)
            aux[k++] = v[j++];

        for(T u : aux)
            v[ini++] = u;
       // for(i = 0; i < aux.length; i++)
         //   v[ini+i] = aux[i];
    }

    private static <T extends Comparable<T>> void mergeSort(T v[], int ini, int fin) {
        if ((fin - ini) >= 1) {
            int half = (fin + ini) / 2;
            mergeSort(v, ini, half);
            mergeSort(v, half + 1, fin);
            merge(v, ini, half, fin);
        }
    }

    public static <T extends Comparable<T>> void mergeSort(T v[]){
        mergeSort(v, 0, v.length -1);
    }

    public static <T extends Comparable<T>> void quickSort(T v[]){
        quickSort(v,0,v.length-1);
    }

    public static <T extends Comparable<T>> void quickSort(T v[], int ini, int fin){
        if(ini < fin){
            int pos = partition(v, ini, fin);
            quickSort(v,ini,pos);
            quickSort(v, pos+1, fin);
        }
    }

    public static <T extends Comparable<T>> int partition(T v[], int ini, int fin){
        
        Random gen = new Random();

        int random_pos_pivot = ini + gen.nextInt(fin - ini + 1);  
        swap(v, random_pos_pivot, ini);       
        
        T pivote = v[ini];

        int i = ini - 1;
        int j = fin + 1;


            while(i < j){
                do{
                    i++;
                }
                while(i <= fin && v[i].compareTo(pivote) < 0);
    
                do{
                    j--;
                }
                while(j >= ini && v[j].compareTo(pivote) > 0);  

                swap(v,i,j);
            }
        swap(v, i, j);
        return j; 
        
    }

    public static <T extends Comparable<T>> void swap (T []v, int i, int j){
        T aux = v[i];
        v[i] = v [j];
        v[j] = aux;
    }

    public static <T extends Comparable<T>> T selection (T v[], int ie){
        if (ie < 0 || ie >= v.length)
            return null;
        return selection(v, ie, 0,v.length-1);
    }
    
    public static <T extends Comparable<T>> T selection (T v[], int ie, int ini, int fin){
        if (ini == fin)
            return v[ie];
        
        int pos = partition(v, ini, fin);

        if (pos >= ie){
            return selection(v, ie, ini, pos);
        }
        else {
            return selection(v, ie, pos + 1, fin);
        }
    }
}
