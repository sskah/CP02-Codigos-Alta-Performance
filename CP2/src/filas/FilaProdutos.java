package filas;

import entidades.Produto;

public class FilaProdutos{

	public final int N = 10;
	public Produto[] produtos = new Produto[N];
	int ini, fim;
    public int cont;

	public void init() {
		ini = fim = cont = 0;
	}
	public boolean isEmpty() {
		return (cont == 0);
	}
	public boolean isFull() {
		return (cont == N);
	}
	public void enqueue(Produto produto) {
		if (isFull())
			System.out.println("Fila cheia!");
		else {
			produtos[fim] = produto;
			cont++;
			fim = (fim + 1) % N;
		}
	}
	public Produto dequeue() {
		Produto valor = produtos[ini];
		cont--;
		ini = (ini + 1) % N;
		return valor;
	}
	public Produto first() {
		return produtos[ini];
	}

}
