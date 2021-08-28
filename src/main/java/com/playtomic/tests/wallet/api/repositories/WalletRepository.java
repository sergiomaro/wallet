package com.playtomic.tests.wallet.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.playtomic.tests.wallet.api.entities.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

	public Optional<Wallet> findById(Long id);

}
