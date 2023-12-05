public interface Amontoavel {
	void inserir(Object dado);

	Object extrair();

	Object obterRaiz();

	String detalharFamiliar(int i);

	Object[] buscarFamiliar(String familiar);

	void remover(Object dado);

	void informacoesFamiliar();

	String imprimir();

	boolean estaVazia();

	boolean estaCheia();
}
