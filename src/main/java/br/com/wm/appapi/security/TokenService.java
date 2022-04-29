package br.com.wm.appapi.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.wm.appapi.dto.UsuarioDto;
import br.com.wm.appapi.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${appapi.jwt.expiration}")
	private String expiration;
	
	@Value("${appapi.jwt.secret}")
	private String secret;
	
	public String gerarToken(Authentication authentication) {
		Usuario usuarioLogado = (Usuario) authentication.getPrincipal();
		Date hoje = new Date();
		Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
		
		String userJson = usuarioLogadoToStringJson(usuarioLogado);
		
		return Jwts.builder()
				.setIssuer("APP API")
				.setSubject(userJson)
				.setIssuedAt(hoje)
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	public UsuarioDto getUser(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		try {
			return new ObjectMapper().readValue(claims.getSubject(), UsuarioDto.class);
		} catch (JsonProcessingException err) {
			throw new RuntimeException("Erro ao obter o usuário do token.");
		}
	}
	
	private String usuarioLogadoToStringJson(Usuario usuarioLogado) {
		UsuarioDto usuarioDto = new UsuarioDto(usuarioLogado);
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(usuarioDto);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Erro ao converter usuário logado para string json.");
		}
	}
}
