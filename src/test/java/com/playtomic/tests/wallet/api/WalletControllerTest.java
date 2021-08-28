package com.playtomic.tests.wallet.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.playtomic.tests.wallet.api.entities.Wallet;
import com.playtomic.tests.wallet.api.repositories.WalletRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class WalletControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private WalletRepository walletRepository;

	String contentOk = "{\"card\":{\"number\":\"4242 4242 4242 4242\"},\"amount\":\"19.00\"}";
	String contentAmountError = "{\"card\":{\"number\":\"4242 4242 4242 4242\"},\"amount\":\"9.00\"}";

	@Test
	public void addMoneyToWallet_ok() throws Exception {

		Wallet newWallet = new Wallet();
		newWallet.setAmount(new BigDecimal("0.00"));
		Wallet wallet = walletRepository.save(newWallet);

		String url = "/wallet/" + wallet.getId() + "/add-money";

		mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(contentOk)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(wallet.getId().intValue())))
				.andExpect(jsonPath("$.amount").value(new BigDecimal("19.0")));
	}

	@Test
	public void addMoneyToWallet_amountError() throws Exception {

		Wallet newWallet = new Wallet();
		newWallet.setAmount(new BigDecimal("0.00"));
		Wallet wallet = walletRepository.save(newWallet);

		String url = "/wallet/" + wallet.getId() + "/add-money";

		mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(contentAmountError))
				.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$", is("Amount must be greater than 10 euros")));
	}

}
