package br.com.wm.appapi.resource;

import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class TesteResource {
	
	@Autowired
	Environment environment;
	
	@GetMapping()
	public String teste() {
		Teste teste1 = new Teste("001", "Teste 1");
		Teste teste2 = new Teste("002", "Teste 2");
		Teste teste3 = new Teste("003", "Teste 3");
		
		List<Teste> testes = Arrays.asList(teste1, teste2, teste3);
		
		String portAndAddress = getPortAndAddress();
		
		System.out.println(testes.toString());
		System.out.println(portAndAddress);
		
		return testes.toString();
	}
	
	@GetMapping("/porta")
	public String porta() {
		return environment.getProperty("server.port");
	}
	
	private String getPortAndAddress() {
		StringBuilder retorno = new StringBuilder("");
		try {
			// Port
			String port = environment.getProperty("server.port");
			retorno.append("Server Port: ").append(port);
			
			// Local address
		    String localAddress = InetAddress.getLocalHost().getHostAddress();
		    String localHost = InetAddress.getLocalHost().getHostName();
		    retorno.append("\nLocal Address: ").append(localAddress);
		    retorno.append("\nLocal Host: ").append(localHost);
		    
		    // Remote address
		    String remoteAddress = InetAddress.getLoopbackAddress().getHostAddress();
		    String remoteHost = InetAddress.getLoopbackAddress().getHostName();
		    retorno.append("\nRemote Address: ").append(remoteAddress);
		    retorno.append("\nRemote Host: ").append(remoteHost);
		    
		    return retorno.toString();
		}catch (Exception e) {
			System.out.println("Erro ao obter address/port.");
			e.printStackTrace();
			return retorno.toString();
		}
	}

	class Teste {
		String codigo;
		String descricao;
		public Teste(String codigo, String descricao) {
			this.codigo = codigo;
			this.descricao = descricao;
		}
		public String getCodigo() {
			return codigo;
		}
		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}
		public String getDescricao() {
			return descricao;
		}
		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
		@Override
		public String toString() {
			return "{\"codigo\":\"" + this.codigo + "\",\"descricao\":\"" + this.descricao + "\"}";
		}
	}
}


