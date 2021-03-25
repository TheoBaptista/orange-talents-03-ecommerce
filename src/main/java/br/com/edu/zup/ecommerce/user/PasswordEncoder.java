package br.com.edu.zup.ecommerce.user;

interface PasswordEncoder {

    public String encode();
    public PasswordEncoder build(String password);

}
