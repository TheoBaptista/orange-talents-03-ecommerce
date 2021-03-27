package br.com.edu.zup.ecommerce.shared;

public interface PasswordEncoder {

    public String encode();
    public PasswordEncoder build(String password);

}
