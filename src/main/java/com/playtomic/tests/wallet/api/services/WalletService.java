package com.playtomic.tests.wallet.api.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.playtomic.tests.wallet.api.entities.Wallet;
import com.playtomic.tests.wallet.api.repositories.WalletRepository;

@Service
public class WalletService {

	@Autowired
	private WalletRepository walletRepository;

	public Wallet getWalletById(long id) throws WalletServiceException {
		Optional<Wallet> wallet = walletRepository.findById(id);

		if (wallet.isEmpty()) {
			throw new WalletServiceException("The wallet ID not exist");
		}

		return wallet.get();
	}

	public Wallet createWallet() {
		Wallet wallet = new Wallet();
		wallet.setAmount(new BigDecimal("0.00"));
		return walletRepository.save(wallet);
	}

}
