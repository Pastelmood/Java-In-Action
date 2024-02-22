package com.example.demo;

import com.example.demo.data.TrueMoney;
import com.example.demo.data.Wallet;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class StreamApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {

        // Mock data
        TrueMoney trueMoney = new TrueMoney();
        trueMoney.addWallet(Wallet.builder().id(1).name("Lumine").balance(5000).build());
        trueMoney.addWallet(Wallet.builder().id(2).name("Paimon").balance(700).build());
        trueMoney.addWallet(Wallet.builder().id(3).name("Furina").balance(12000).build());
        trueMoney.addWallet(Wallet.builder().id(4).name("Zhongli").balance(0).build());

        return runner -> {

            // findMaxValueFromWallets(trueMoney);
            findBalanceBetween500To5000(trueMoney);

        };

    }

    private void findBalanceBetween500To5000(TrueMoney trueMoney) {

        List<Wallet> filteredWallets = trueMoney.getWalletList().stream()
                .filter(wallet -> wallet.getBalance() >= 500 && wallet.getBalance() <= 5000)
                .toList();

        filteredWallets.forEach(wallet -> System.out.println("Wallet " + wallet.getName() +
                " has balance: " + wallet.getBalance()));
    }

    private void findMaxValueFromWallets(TrueMoney trueMoney) {

        // find max balance using Stream API
        Optional<Wallet> maxBalanceWallet = trueMoney.getWalletList()
                .stream().max(Comparator.comparingInt(Wallet::getBalance));

        maxBalanceWallet.ifPresent(wallet -> {
            System.out.println("\n=====> Who is the most balance:");
            System.out.println("=====> Wallet Name: " + wallet.getName());
            System.out.println("=====> Wallet Name: " + wallet.getBalance());
        });

    }

}
