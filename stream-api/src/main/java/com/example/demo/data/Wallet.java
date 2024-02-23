package com.example.demo.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {
    private int id;
    private String name;
    private int balance;
    private vision vision;

    public enum vision {

        ELECTRO,
        HYDRO,
        PYRO,
        DENDRO,
        CRYO,
        ANEMO,
        GEO

    }


}
