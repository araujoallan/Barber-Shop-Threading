import java.util.*;

public class Barbearia {

	private Queue<Cliente> filaCliente = new LinkedList<Cliente>();

	int nBarbeiros;
	int nCadeiras;

	int nBarbeirosLivres = nBarbeiros;

	public Barbearia(int m, int n) {
		nBarbeiros = m;
		nCadeiras = n;

		//criação dos barbeiros
		for (int i = 1; i <= m; i++)
			new Barbeiro(i, this);
	}

	public Queue<Cliente> getFilaCliente() {
		return filaCliente;
	}

	public int getBarbeiros() {
		return nBarbeiros;
	}

	public int getCadeiras() {
		return nCadeiras;
	}

	public boolean FilaClientesCheia {

		if(filaCliente.size()==nCadeiras) {
			return true;
		}

		return false;
	}

	public synchronized void adicionaCliente(Cliente c) throws FilaCheiaException {
		
		if(FilaClientesCheia()) {
			new FilaCheiaException();
		}

		else {
			filaCliente.add(c);
		}
	}

	public synchronized boolean existeBarbeiroLivre() {
		return nBarbeirosLivres < nBarbeiros;
	}

	public synchronized void mais1BarbeiroLivre() {
		nBarbeirosLivres++;
	}

	public synchronized void menos1BarbeiroLivre() {
		nBarbeirosLivres--;
	}
}