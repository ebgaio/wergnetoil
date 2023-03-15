package com.wergnet.wergnetoil.api.util;

import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class StringUtil {

    public static boolean isEquals(String descricao1, String descricao2) {
        if ((descricao1 == null && descricao2 != null) || (descricao1 != null && descricao2 == null)) {
            return false;
        } else if (descricao1 != null && descricao2 != null) {
            return descricao1.equals(descricao2);
        }
        return true;
    }

    public static Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

    public static String getStringByTamanhoMaximo(String descricao, int tamanhoMaximo) {
        if (descricao.length() > tamanhoMaximo) {
            return descricao.substring(0, tamanhoMaximo);
        }
        return descricao;
    }

    public static void teste() {
        BigDecimal valorLanceFormated = new BigDecimal("1.23");
        String valorLance = String.format("R$ %."+3+"f", valorLanceFormated);
        System.out.println("\n----------------------> " + valorLance + "\n-----------------");
    }
    public static void main(String[] args) {
//        String numero = null;
//        System.out.println("----> apache: " + StringUtils.leftPad(numero, 6, "0"));
//        System.out.println("---->   java: " + String.format("%06d", Integer.parseInt(numero)));
        teste();
    }
}
