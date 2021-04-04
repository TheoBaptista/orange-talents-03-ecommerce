package br.com.edu.zup.ecommerce.question;

import br.com.edu.zup.ecommerce.shared.Action;


public class SendEmailAfterQuestion implements Action {

    private final String message;
    private final String destination;
    private final String sender;

    public SendEmailAfterQuestion(Question question, String sender) {
        this.message = question.getTitle();
        this.destination = question.getProduct().getUser().getUsername();
        this.sender = sender;
    }

    @Override
    public void execute() {
        System.out.println("Enviando e-mail...");
        System.out.println("De :" + sender);
        System.out.println("Para :" + destination);
        System.out.println("Mensagem :"+ message);
    }
}
