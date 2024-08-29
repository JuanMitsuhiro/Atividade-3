package view;

import javax.swing.JOptionPane;
import controller.RedesController;

public class Main {

	public static void main(String[] args) {
		RedesController rCont = new RedesController();
		int opc = Integer.parseInt(JOptionPane.showInputDialog("Escolha uma opção:\n1 - Método ip\n2 - Método ping\n9 - Sair"));
		do {
			switch(opc) {
			case 1: 
				rCont.ip();
				break;
			case 2: 
				rCont.ping();
				break;
			case 9:
				break;
			default:
				break;
			}	
		} while (opc != 9);
		
		

	}
}
