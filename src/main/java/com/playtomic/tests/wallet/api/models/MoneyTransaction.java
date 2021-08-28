package com.playtomic.tests.wallet.api.models;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MoneyTransaction {

	@NotNull(message = "Card is mandatory")
	private CreditCard card;

	@NotNull(message = "Amount is mandatory")
	private BigDecimal amount;

}
