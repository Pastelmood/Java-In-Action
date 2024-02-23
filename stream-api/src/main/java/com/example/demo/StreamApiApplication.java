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
import java.util.stream.Collectors;

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
        trueMoney.addWallet(Wallet.builder().id(5).name("Nahida").balance(3500).build());
        trueMoney.addWallet(Wallet.builder().id(6).name("Venti").balance(6000).build());
        trueMoney.addWallet(Wallet.builder().id(7).name("Raiden").balance(250).build());
        trueMoney.addWallet(Wallet.builder().id(8).name("Childe").balance(100000).build());

        return runner -> {

            // findMaxValueFromWallets(trueMoney);
            // findBalanceBetween500To5000(trueMoney);
            // findBalanceBetween500To5000AndSortByName(trueMoney);
            findThreeMostBalanceWallet(trueMoney);
            // sortByName(trueMoney);
            // sortByNameDesc(trueMoney);

            
        };

    }

    private void findThreeMostBalanceWallet(TrueMoney trueMoney) {
        List<Wallet> sortedWallets = trueMoney.getWalletList().stream()
                .sorted(Comparator.comparingInt(Wallet::getBalance).reversed())
                .limit(3)
                .toList();

        System.out.println("\n=====> Find 3 most wallet balance: ");
        sortedWallets.forEach(System.out::println);
    }

    private void findBalanceBetween500To5000AndSortByName(TrueMoney trueMoney) {
        List<Wallet> sortedWallets = trueMoney.getWalletList().stream()
                .filter(wallet -> wallet.getBalance() >= 500 && wallet.getBalance() <= 5000)
                .sorted(Comparator.comparing(Wallet::getName))
                .toList();

        System.out.println("\n=====> Find wallets have balance from 500 to 5000 and Sort wallets by name: ");
        sortedWallets.forEach(System.out::println);
    }

    private void sortByNameDesc(TrueMoney trueMoney) {
        List<Wallet> sortedWallets = trueMoney.getWalletList().stream()
                .sorted(Comparator.comparing(Wallet::getName).reversed()) // Sort by name in descending order
                .toList();

        System.out.println("\n=====> Sort wallets by name (descending): ");
        sortedWallets.forEach(System.out::println);
    }

    private void sortByName(TrueMoney trueMoney) {
        List<Wallet> sortedWallets = trueMoney.getWalletList().stream()
                .sorted(Comparator.comparing(Wallet::getName))
                .toList();

        System.out.println("\n=====> Sort wallets by name: ");
        sortedWallets.forEach(System.out::println);
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
