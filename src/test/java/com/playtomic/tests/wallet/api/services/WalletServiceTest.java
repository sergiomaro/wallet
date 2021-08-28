package com.playtomic.tests.wallet.api.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.playtomic.tests.wallet.api.entities.Wallet;
import com.playtomic.tests.wallet.api.repositories.WalletRepository;

@SpringBootTest
public class WalletServiceTest {

	@Autowired
	private WalletService walletService;

	@MockBean
	private WalletRepository walletRepository;

	@Test
	public void getWalletById_ok() throws WalletServiceException {
		Wallet wallet = new Wallet();
		wallet.setId(1L);
		wallet.setAmount(new BigDecimal("10.00"));

		Mockito.when(walletRepository.findById(wallet.getId())).thenReturn(Optional.of(wallet));

		assertThat(walletService.getWalletById(1)).isEqualTo(wallet);
	}

	@Test
	public void getWalletById_error() throws WalletServiceException {
		assertThrows(WalletServiceException.class, () -> walletService.getWalletById(1));
	}

}
