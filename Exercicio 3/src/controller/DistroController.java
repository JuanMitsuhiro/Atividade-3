package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DistroController {
	
	public DistroController() {
		super();
	}
	
	private String os() {
		String os = System.getProperty("os.name");
		return os;
	}
	
	public void exibeDistro() {
		String os = os();
		String comando = "";
		
		if(os.contains("Linux")) {
			comando = "cat /etc/os-release";
			
			String[] procArr = comando.split(" ");
			try {
				Process p = Runtime.getRuntime().exec(procArr);
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				while (linha != null){
					if (linha.contains("NAME=")) {
						System.out.println(linha);
					} else if (linha.contains("VERSION=")){
						System.out.println(linha);
					}
					linha = buffer.readLine();
				}
				
				buffer.close();
				leitor.close();
				fluxo.close();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		} else {
			System.out.println("Sistema Operacional é diferente de Linux.");
		}
		
	}
	
}
