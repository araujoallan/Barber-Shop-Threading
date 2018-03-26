public class Barbeiro extends  {
	Barbearia barbearia;

	public Barbeiro(int id, Barbearia b) 	{
		this.setName("Barbeiro_"+id);
		this.barbearia = b;
		this.start();
	}

	public synchronized void cortarCabelo()	{

		try {
			barbearia.menos1BarbeiroLivre();
			Cliente c = barbearia.getFilaCliente().remove();
			c.cortarCabelo();
			System.out.println(this.getName() + " - " + "Estou cortando o cabelo de " + c.getName() + ".");

			// Tempo de Servico
			try {
			    Thread.sleep(1000);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}

			barbearia.mais1BarbeiroLivre();
			System.out.println(this.getName() + " - " + "Acabei de cortar o cabelo de "+ c.getName() + ".");

		} catch (FilaVaziaException e) {
			e.printStackTrace();
		}
	}

	private void cochilar()	{
		synchronized(barbearia) {
			try {
				System.out.println(this.getName() + " - " + "Cochilando.");
				barbearia.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		while(true) {
			if(barbearia.getFilaCliente().isEmpty())
				cochilar();
			else
				cortarCabelo();
		}
	}

}