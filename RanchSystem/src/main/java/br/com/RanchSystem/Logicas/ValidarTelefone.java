package br.com.RanchSystem.Logicas;

public class ValidarTelefone {

    public boolean telefoneValido(String telefone) {

        // remove espaços, traços, parênteses
        telefone = telefone.replaceAll("\\D", "");

        // deve ter exatamente 11 dígitos (DDD + 9 + número)
        if (telefone.length() != 11) {
            return false;
        }

        // DDD não pode começar com 0
        if (telefone.startsWith("0")) {
            return false;
        }

        // celular no Brasil começa com 9
        if (telefone.charAt(2) != '9') {
            return false;
        }

        return true;
    }
}
