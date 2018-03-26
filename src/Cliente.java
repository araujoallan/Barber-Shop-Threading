public class Cliente extends Thread {
		Barbearia barbearia;

		public Cliente(int id, Barbearia b) {
			this.setName("Cliente_" + id);
			this.barbearia = b;
			this.start();
		}

		public void cortarCabelo() {
			System.out.println(this.getName() + " - " + "Estou cortando meu cabelo.");
		}


		public void run() {
			System.out.println(this.getName() + " - " + "Cheguei na barbearia.");

			if(barbearia.FilaClientesCheia()) {
				System.out.println(this.getName() + " - " + "Barbearia lotada.");
				return;
			}

			else {
				try {
					synchronized(barbearia) {
						//Cliente entra na fila de espera
						barbearia.adicionaCliente(this);
						if(barbearia.getFilaCliente().size() > barbearia.getCadeiras())
							System.out.println(this.getName() + " - " + "Entrei na fila.");
					}

				} catch (FilaCheiaException e) {
					e.printStackTrace();
				}

				if(barbearia.existeBarbeiroLivre()) {
					synchronized(barbearia) {
						// acorda algum barbeiro
						System.out.println(this.getName() + " - " + "Acordando barbeiro.");
						barbearia.notify();
					}
				}
			}
		}

	}