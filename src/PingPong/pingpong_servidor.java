package PingPong;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class pingpong_servidor {

	public static void main(String[] args) throws IOException {
		
		ServerSocket s = new ServerSocket(2001);
		
		while(true) {
			System.out.println("Esperando Conexão.");
			Socket conexao = s.accept();
			System.out.println("Esperando Mensagem.");			
			DataInputStream entrada = new DataInputStream(conexao.getInputStream());
			DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());
			String linha = entrada.readUTF();
			
			while(linha != null && !(linha.trim().equals(""))) {
				if(linha.equalsIgnoreCase("SAIR")) {
					System.out.println("Servidor Desligado!");
					saida.writeUTF("sair");
					conexao.close();
					s.close();
					break;
				} else {
					System.out.println("Mensagem Recebida: " + linha);
					System.out.print("Digite a resposta: ");				
					String resposta;
					BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));				
					resposta = teclado.readLine();				
					saida.writeUTF(resposta);
					System.out.println("Resposta Enviada.");
					linha = entrada.readUTF();
				}
			}
			conexao.close();
			break;
		}
	}
}