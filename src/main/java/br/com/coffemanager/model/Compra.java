package br.com.coffemanager.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

//Compra Model
public class Compra {
 private Long id;
 private Long itemId;
 private BigDecimal valorUnitario;
 private int qtd;
 private LocalDate dataCompra;
 private LocalDate vencimento;
 private Long cadastroUsuarioId;
 private LocalDateTime criadoEm;
 private LocalDateTime atualizadoEm;

 // Getters and Setters
}