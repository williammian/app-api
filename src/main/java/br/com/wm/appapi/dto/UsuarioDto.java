package br.com.wm.appapi.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.wm.appapi.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {
	
	private Long id;
	private String nome;
	private String email;
	private List<PerfilDto> perfis;
	
	public UsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		
		this.perfis = new ArrayList<>();
		this.perfis.addAll(usuario.getPerfis().stream().map(PerfilDto::new).collect(Collectors.toList()));
	}

}
