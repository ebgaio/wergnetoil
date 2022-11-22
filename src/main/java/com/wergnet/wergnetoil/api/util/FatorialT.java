package com.wergnet.wergnetoil.api.util;

import javax.swing.JOptionPane;

public class FatorialT {

    public static void main(String[] args) {

        Long n = Long.parseLong(JOptionPane.showInputDialog("Digite um valor"));

        Fatorial b = new Fatorial();

        n = b.fatorialRecursivo(n);

        JOptionPane.showMessageDialog(null, "O fatorial é: " + n);

    }

}

class Fatorial {
    public Long fatorialRecursivo(Long num) {

        if (num == 0) {
            return 1L;
        }
        return num * fatorialRecursivo(num - 1);
    }

}
