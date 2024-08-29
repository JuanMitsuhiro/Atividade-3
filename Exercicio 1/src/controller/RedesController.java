package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {
	
	public RedesController() {
		super();
	}
	
	private String os() {
		String os = System.getProperty("os.name");
		return os;
	}
	
	public void ip() {

		String os = os();
		String ipComando = "";
		if (os.contains("Windows")) {
			ipComando = "ipconfig";
		} else if (os.contains("Linux")) {
			ipComando = "ifconfig";
		}
		
		String[] procArr = ipComando.split(" ");
		String adaptador;
		try {
			Process p = Runtime.getRuntime().exec(procArr);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while (linha != null) {
				if (linha.contains("Adaptador")) {
					adaptador = linha;
					do {
						linha = buffer.readLine();
						if (linha == null) {
							return;
						} else if (linha.contains("IPv4")) {
							System.out.println(adaptador + "\n" + linha);
							linha = buffer.readLine();
							break;
						}
					} while ((!linha.contains("Adaptador") || linha != null));
				}
				
				if (!linha.contains("Adaptador") && linha != null) {
					linha = buffer.readLine();
				}
				
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} catch(Exception e){
			System.err.println(e.getMessage());
		}
		
	}

	public void ping() {
		String os = os();
		String ipComando = "";
		if (os.contains("Windows")) {
			ipComando = "ping -4 -n 10 www.google.com.br";
		} else if (os.contains("Linux")) {
			ipComando = "ping -4 -c 10 www.google.com.br";
		}
		
		String[] procArr = ipComando.split(" ");
		try {
			Process p = Runtime.getRuntime().exec(procArr);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while (linha != null) {
				if (os.contains("Windows")) {
					if (linha.contains("ms,")) {
	                    String[] linhaMedia = linha.split(", ");
	                    System.out.println(linhaMedia[2]);
	                }
					linha =  buffer.readLine();
				} else if (os.contains("Linux")) {
					if (linha.contains("/")) {
	                    String[] linhaMedia = linha.split("/");
	                    System.out.println(linhaMedia[4]);
	                }
				}
				linha =  buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} catch(Exception e){
			System.err.println(e.getMessage());
		}
		
	}
	
}

