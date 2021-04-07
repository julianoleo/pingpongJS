package PingPong;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class pingpong_cliente {

	public static void main(String[] args) throws IOException {

		Socket conexao = new Socket("localhost", 2001);
		
		DataInputStream entrada = new DataInputStream(conexao.getInputStream());
		DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());
		
		String linha;
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			System.out.print("Digite mensagem: ");
			linha = teclado.readLine();
				saida.writeUTF(linha);
				System.out.println("Mensagem enviada.");
				System.out.println("Esperando resposta.");
				linha = entrada.readUTF();
				if(linha.equalsIgnoreCase("sair")) {
					System.out.println("Desconectando.");
					conexao.close();
					break;
				} else {
					System.out.println("Resposta Recebida: " + linha);									
				}
		}
	}
}