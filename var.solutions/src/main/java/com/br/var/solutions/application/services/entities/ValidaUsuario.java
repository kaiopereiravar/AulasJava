package com.br.var.solutions.application.services.entities;

public class ValidaUsuario {

    public enum ValidaUSer{//lista de usuarios, em um método de enumeração. tipo de dado utilizado para armazenar um conjunto de valores constantes.
        USER_1("var","12345678"),
        USER_2("vitor.carvalho", "Teste01&"),
        USER_3("aluno_var", "senha1234");

        private String usuario;
        private  String senha; // os atributos desta lista

        ValidaUSer(String username, String password){//construtor desta lista. a informação que chegar no ValisaUSer é de fato username e password
            this.usuario = username;
            this.senha = password;
        }

        public String getUsuario() { //aqui são os getters dessa lista, nao podemos alterar o valor de senha e usuario pois isso sao so getters
            return usuario;
        }
        public String getSenha() {
            return senha;
        }
    }

    public static Boolean validaUsuario(String username, String password){ //é um metodo estatico dessa classe
        for (ValidaUSer validaUser : ValidaUSer.values()){ //pra pegar os valores de cada objeto da lista do metodo acima(USER_1, USER_2 ....)
            if (validaUser.getUsuario().equalsIgnoreCase(username) && validaUser.getSenha().equals(password)){ //se o usuario e a senha for igual os da lista acima, return true
                return true;
            }
        }
        return false;
    }

    public  static String returnPassword(String username){
        for (ValidaUSer validaUSer : ValidaUSer.values()){
            if (validaUSer.getUsuario().equalsIgnoreCase(username)){
                return validaUSer.getSenha();
            }
        }
        return null;
    }

    public  static String returnUsername(String username){
        for (ValidaUSer validaUSer : ValidaUSer.values()){
            if (validaUSer.getUsuario().equalsIgnoreCase(username)){
                return validaUSer.getUsuario();
            }
        }
        return null;
    }
}
