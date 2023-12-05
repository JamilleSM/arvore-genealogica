public interface Amontoavel {
	void inserir(Object dado);

	Object extrair();

	Object obterRaiz();

	String detalharFamiliar(int i);

	boolean buscarFamiliar(String familiar);

	void remover(Object dado);

	String imprimir();

	boolean estaVazia();

	boolean estaCheia();
}
