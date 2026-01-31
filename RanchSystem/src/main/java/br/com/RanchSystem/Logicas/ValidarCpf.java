package br.com.RanchSystem.Logicas;

public class ValidarCpf {
    public boolean cpfValido(String cpf) {

        // CPF precisa ter 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // CPFs inválidos conhecidos (todos dígitos iguais)
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int soma = 0;
        int peso = 10;

        // primeiro dígito verificador
        for (int i = 0; i < 9; i++) {
            soma += (cpf.charAt(i) - '0') * peso--;
        }

        int resto = (soma * 10) % 11;
        if (resto == 10) resto = 0;

        if (resto != (cpf.charAt(9) - '0')) {
            return false;
        }

        soma = 0;
        peso = 11;

        // segundo dígito verificador
        for (int i = 0; i < 10; i++) {
            soma += (cpf.charAt(i) - '0') * peso--;
        }

        resto = (soma * 10) % 11;
        if (resto == 10) resto = 0;

        return resto == (cpf.charAt(10) - '0');
    }

}
