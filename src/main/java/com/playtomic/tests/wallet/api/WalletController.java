package com.playtomic.tests.wallet.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.playtomic.tests.wallet.api.entities.Wallet;
import com.playtomic.tests.wallet.api.services.WalletService;
import com.playtomic.tests.wallet.api.services.WalletServiceException;

@RestController
@RequestMapping("/wallet")
public class WalletController {
	private Logger log = LoggerFactory.getLogger(WalletController.class);

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
	Wallet createWallet() throws WalletServiceException {
		return walletService.createWallet();
	}
}
