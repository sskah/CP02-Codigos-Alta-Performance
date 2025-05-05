package filas;

import entidades.Encomenda;

public class FilaEncomendas{

	public final int N = 10;
	public Encomenda[] encomendas = new Encomenda[N];
	int ini, fim, cont;

	public void init() {
		ini = fim = cont = 0;
	}
	public boolean isEmpty() {
		return (cont == 0);
	}
	public boolean isFull() {
		return (cont == N);
	}
	public void enqueue(Encomenda encomenda) {
		if (isFull())
			System.out.println("Fila cheia!");
		else {
			encomendas[fim] = encomenda;
			cont++;
			fim = (fim + 1) % N;
		}
	}
	public Encomenda dequeue() {
		Encomenda valor = encomendas[ini];
		cont--;
		ini = (ini + 1) % N;
		return valor;
	}
	public Encomenda first() {
		return encomendas[ini];
	}

}

