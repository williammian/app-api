package br.com.wm.appapi.security;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.wm.appapi.dto.UsuarioDto;
import br.com.wm.appapi.exception.handler.AppApiExceptionHandler;
import br.com.wm.appapi.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class TokenService {
	
	@Value("${appapi.jwt.expiration}")
	private String expiration;
	
	@Value("${appapi.jwt.secret}")
	private String secret;
	
	private static final Logger logger = LoggerFactory.getLogger(TokenService.class);
	
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
		if (token == null || token.trim().isEmpty()) {
	        return false;
	    }
		
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
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
