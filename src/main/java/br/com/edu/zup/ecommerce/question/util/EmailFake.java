package br.com.edu.zup.ecommerce.question.util;

import org.springframework.stereotype.Component;

@Component
public class EmailFake implements Email {

    @Override
    public void send(String from, String to, String subject) {
        System.out.println("Enviando e-mail...");
        System.out.println("De :" + from);
        System.out.println("Para :" + to);
        System.out.println("Mensagem :"+ subject);
    }
}
