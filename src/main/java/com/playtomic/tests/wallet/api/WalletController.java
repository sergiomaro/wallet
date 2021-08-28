package com.playtomic.tests.wallet.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.playtomic.tests.wallet.api.entities.Wallet;
import com.playtomic.tests.wallet.api.models.MoneyTransaction;
import com.playtomic.tests.wallet.api.services.WalletService;
import com.playtomic.tests.wallet.api.services.WalletServiceException;

@RestController
@RequestMapping("/wallet")
public class WalletController {

	@Autowired
	private WalletService walletService;

	@RequestMapping("/")
	String log() {
		return "Wallet API";
	}

	@GetMapping("/{id}")
	Wallet getWallet(@PathVariable("id") long id) throws WalletServiceException {
		return walletService.getWalletById(id);
	}

	@PostMapping("/")
	Wallet createWallet() {
		return walletService.createWallet();
	}

	@PostMapping("/{id}/add-money")
	Wallet addMoneyToWallet(@PathVariable("id") long id, @Valid @RequestBody MoneyTransaction addMoney)
			throws WalletServiceException {
		return walletService.addMoneyToWallet(id, addMoney);
	}

	@PostMapping("/{id}/subtract-money")
	Wallet subtractMoneyFromWallet(@PathVariable("id") long id, @Valid @RequestBody MoneyTransaction subtractMoney)
			throws WalletServiceException {
		return walletService.subtractMoneyFromWallet(id, subtractMoney);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(WalletServiceException.class)
	public String handleValidationExceptions(WalletServiceException ex) {
		return ex.getMessage();
	}
}
