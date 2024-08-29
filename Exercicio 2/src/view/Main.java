package view;

import javax.swing.JOptionPane;

import controller.KillController;

public class Main {

	public static void main(String[] args) {
		KillController kCont = new KillController();
		int opc;
		String valor = "";
		
		kCont.listaProcessos();
		
		do {
			opc = Integer.parseInt(JOptionPane.showInputDialog("1 - Processo por nome\n2 - Processo por PID\n9 - Finalizar"));
			
			switch(opc) {
				case 1:
					valor = JOptionPane.showInputDialog("Digite o nome do processo:");
					kCont.mataNome(valor);
					break;
				case 2:
					valor = JOptionPane.showInputDialog("Digite o PID do processo::");
					kCont.mataPid(valor);
					break;
				case 9:
					break;
				default:
					break;
			
			}
		} while(opc != 9);
		
	}

}
