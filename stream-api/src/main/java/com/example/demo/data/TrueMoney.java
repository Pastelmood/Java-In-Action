package com.example.demo.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrueMoney {

    private List<Wallet> walletList;

    public void addWallet(Wallet wallet) {

        if (walletList == null) {
            walletList = new ArrayList<>();
        }
        walletList.add(wallet);
    }

}
