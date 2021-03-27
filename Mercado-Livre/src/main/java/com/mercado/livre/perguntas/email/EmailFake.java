package com.mercado.livre.perguntas.email;

import com.mercado.livre.perguntas.PerguntaProduto;
import org.springframework.stereotype.Component;

        @Component
        public class EmailFake {
            public void send(String destino, String remetente, PerguntaProduto pergunta) {
                System.out.println("Remetente: "+remetente);
                System.out.println("Destino: "+destino);
                System.out.println("Conteudo: \n"+pergunta.toString());
    }
}
