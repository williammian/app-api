package br.com.wm.appapi.dto;

import br.com.wm.appapi.model.Perfil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PerfilDto {
	
	private Long id;
	private String nome;
	
	public PerfilDto(Perfil perfil) {
		this.id = perfil.getId();
		this.nome = perfil.getNome();
	}

}
