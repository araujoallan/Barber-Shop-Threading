public class Start {

	public static void main(String[] args) {

		int ncadeiras = 4; // n de cadeiras
		int nbarbeiros = 3; // n de barbeiros
		int nclientes = 2*(ncadeiras + nbarbeiros);

		Barbearia b = new Barbearia(nbarbeiros,ncadeiras);

		try {
		    Thread.sleep(1000);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}

		for (int i = 1; i <= nclientes;i++) {
			new Cliente(i,b);
		}
	}
}