import java.util.Comparator;

public class ComparadorArista implements Comparator<Arista>{

	@ Override
	public int compare(Arista a, Arista b){
		if(a.costo > b.costo){
            return 1;	// Mayor elemento a la derecha
        } else if (a.costo < b.costo){
            return -1;	// Menor elemento a la izquierda
        } else {
            return 0;
        }
	}
}