package br.com.edu.zup.ecommerce.email;

public interface Email {
    public void send(String from, String to, String subject);
}
