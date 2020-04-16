package lineares;

public class FilaVetor<T> implements Fila<T> {
	// quantidade de valores armazenados
	private int n;
	// posi��o do pr�x elemento a ser retirado
	private int ini;
	// vetor que armazena elementos
	private T[] vet;

	// implementa��o dos m�todos...

	public FilaVetor(int tam) {
		this.vet = (T[]) new Object[tam];
	}

	@Override
	public void insere(T v) throws Exception {
		if (n == vet.length) { // a fila est� cheia?
			throw new RuntimeException("Fila cheia");
		}
		vet[this.getFim()] = v;
		n++;
	}

	private int getFim() {
		return (ini + n) % vet.length;
	}

	@Override
	public T retira() throws Exception {
		if (this.vazia()) {
			throw new RuntimeException("Fila vazia");
		}
		T valor = vet[ini];
		vet[ini] = null;
		ini = (ini + 1) % vet.length;
		n--;
		return valor;
	}

	@Override
	public boolean vazia() {
		return (n == 0);
	}

	@Override
	public void libera() {
		this.vet = (T[]) new Object[this.vet.length];
		this.n = 0;
		this.ini = 0;
	}

	public String toString() {
		String resultado = "";
		int indice;
		for (int i = 0; i < this.n; i++) {
			indice = (i + ini) % vet.length;
			if (i > 0) {
				resultado += ",";
			}
			resultado = resultado + vet[indice];
		}
		return resultado;
	}

}
