package br.com.wm.appapi.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.wm.appapi.model.enums.ABC;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "item", 
uniqueConstraints = { @UniqueConstraint(name = "uk_item_tipo_codigo", columnNames = { "tipo", "codigo" }) })
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Item {
	
	public static final int TIPO_MATERIAL = 0;
	public static final int TIPO_PRODUTO = 1;
	public static final int TIPO_MERCADORIA = 2;
	public static final int TIPO_SERVICO = 3;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private Integer tipo;
	
	@NotNull
	@Column(length = 10)
	@Size(min = 1, max = 10)
	private String codigo;
	
	@NotNull
	@Column(length = 50)
	@Size(min = 1, max = 50)
	private String descricao;
	
	@NotNull
	@Column(name = "data_cadastro")
	private LocalDate dataCadastro;
	
	@NotNull
	private Boolean ativo;
	
	@NotNull
	@Enumerated(value = EnumType.STRING)
	private ABC abc;
	
	@NotNull
	private BigDecimal preco;

}
