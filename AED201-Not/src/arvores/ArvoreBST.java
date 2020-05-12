package arvores;

public class ArvoreBST<T extends Comparable<T>> extends ArvoreBinariaAbstract<T> {

	public void inserir(T info) {
		if (this.vazia()) {
			this.setRaiz(new NoArvoreBST<T>(info));
		} else {
			((NoArvoreBST<T>) this.getRaiz()).inserir(info);
		}
	}

	public NoArvoreBST<T> buscar(T info) {
		if (this.vazia()) {
			return null;
		}
		return ((NoArvoreBST<T>) this.getRaiz()).buscar(info);
	}

	public void retirar(T info) {
		NoArvoreBST<T> aRetirar = this.buscar(info);
		if (aRetirar != null) {
			this.retirar(aRetirar);
		}
	}

	private void retirar(NoArvoreBST<T> aRetirar) {
		if (aRetirar == this.getRaiz()) {
			if (aRetirar.getEsq() == null && aRetirar.getDir() == null) { // � n� folha
				this.setRaiz(null);
			}
			else {
				if (aRetirar.getEsq() == null || aRetirar.getDir() == null) { // tem 1 filho
					if (aRetirar.getEsq() != null) { // filho est� � esquerda
						this.setRaiz(aRetirar.getEsq());
					}
					else { // filho est� � direita
						this.setRaiz(aRetirar.getDir());
					}
				} 
				else {// tem 2 filhos
					NoArvoreBST<T> sucessor = this.sucessor(aRetirar);
					T infoSucessor = sucessor.getInfo();
					this.retirar(sucessor);
					this.getRaiz().setInfo(infoSucessor);
				}
			}
		}
		else {  // aRetirar n�o � raiz
			NoArvoreBST<T> pai = this.buscarPai(aRetirar);
			if (aRetirar.getEsq() == null && aRetirar.getDir() == null) { // � n� folha
				if (pai.getDir() == aRetirar) {
					pai.setDir(null);
				}
				else {
					pai.setEsq(null);
				}
			}
			else {
				if (aRetirar.getEsq() == null || aRetirar.getDir() == null) { // tem 1 filho
					NoArvoreBST<T> filho;
					if (aRetirar.getEsq() != null) { // filho est� � esquerda
						filho = (NoArvoreBST<T>)aRetirar.getEsq();
					}
					else { // filho est� � direita
						filho = (NoArvoreBST<T>)aRetirar.getDir();
					}
					if (pai.getDir() == aRetirar) {
						pai.setDir(filho);
					}
					else {
						pai.setEsq(filho);
					}
				} 
				else {// tem 2 filhos
					NoArvoreBST<T> sucessor = this.sucessor(aRetirar);
					T infoSucessor = sucessor.getInfo();
					this.retirar(sucessor);
					aRetirar.setInfo(infoSucessor);
				}
			}
		}
	}

	private NoArvoreBST<T> buscarPai(NoArvoreBST<T> filho) {
		if (this.vazia() || filho == null || this.getRaiz()==filho) {
			return null;
		}
		NoArvoreBST<T> pai = (NoArvoreBST<T>)this.getRaiz();
		while (pai != null) {
			if (pai.getDir() == filho || pai.getEsq() == filho) {
				return pai;
			}
			if (pai.getInfo().compareTo(filho.getInfo()) > 0) { // est� � esquerda
				pai = (NoArvoreBST<T>)pai.getEsq();
			}
			else { // est� � direita
				pai = (NoArvoreBST<T>)pai.getDir();
			}
		}
		
		return null;
	}

	private NoArvoreBST<T> sucessor(NoArvoreBST<T> aRetirar) {
        NoArvoreBST<T> sucessor = (NoArvoreBST<T>)aRetirar.getDir();
        while (sucessor.getEsq() != null) {
            sucessor = (NoArvoreBST<T>)sucessor.getEsq();
        }
        return sucessor;
	}
	
	
}
