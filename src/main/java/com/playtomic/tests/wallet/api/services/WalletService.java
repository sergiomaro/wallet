package com.playtomic.tests.wallet.api.services;

import java.math.BigDecimal;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.playtomic.tests.wallet.api.entities.Wallet;
import com.playtomic.tests.wallet.api.models.MoneyTransaction;
import com.playtomic.tests.wallet.api.repositories.WalletRepository;
import com.playtomic.tests.wallet.service.StripeService;
import com.playtomic.tests.wallet.service.StripeServiceException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WalletService {

	@Autowired
	private WalletRepository walletRepository;

	@Autowired
	private StripeService stripeService;

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

	public Wallet addMoneyToWallet(long id, @Valid MoneyTransaction addMoney) throws WalletServiceException {
		Wallet wallet = this.getWalletById(id);

		try {
			stripeService.charge(addMoney.getCard().getNumber(), addMoney.getAmount());
		} catch (StripeServiceException se) {
			throw new WalletServiceException("Amount must be greater than 10 euros");
		} catch (Exception e) {
			log.error("Stripe service fail", e);
			throw new WalletServiceException("Stripe service fail");
		}

		wallet.addAmount(addMoney.getAmount());

		return walletRepository.save(wallet);
	}

}
