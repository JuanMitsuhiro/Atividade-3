package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class KillController {

	public KillController() {
		super();
	}
	
	private String os() {
		String os = System.getProperty("os.name");
		return os;
	}
	
	public void listaProcessos() {
		String os = os();
		String comandoLista = "";
		
		if (os.contains("Windows")) {
			comandoLista = "TASKLIST /FO TABLE";
		} else if(os.contains("Linux")) {
			comandoLista = "ps -ef";
		}
		
		String[] procArr = comandoLista.split(" ");
		try {
			Process p = Runtime.getRuntime().exec(procArr);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while (linha != null) {
				System.out.println(linha);
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} catch(Exception e){
			System.err.println(e.getMessage());
		}
		
	}

	public void mataPid(String procPid) {
		String os = os();
		String comando = "";
		
		
		if (os.contains("Windows")) {
			comando = "TASKKILL /PID " + procPid;
		} else if (os.contains("Linux")) {
			comando = "kill -9 " + procPid;
		}
		String[] procArr = comando.split(" ");
		try {
			Runtime.getRuntime().exec(procArr);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}
	
	public void mataNome(String procNome) {
		String os = os();
		String comando = "";
		
		
		if (os.contains("Windows")) {
			comando = "TASKKILL /IM " + procNome;
		} else if (os.contains("Linux")) {
			comando = "pkill -f " + procNome;
		}
		String[] procArr = comando.split(" ");
		try {
			Runtime.getRuntime().exec(procArr);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
