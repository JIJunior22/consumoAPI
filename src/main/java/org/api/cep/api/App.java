package org.api.cep.api;

import org.dom4j.DocumentException;
import javax.swing.*;
import java.io.IOException;

public class App {
    public static void main(String[] agrs) throws IOException, InterruptedException, DocumentException {

        var cepConsumer = new CepApiConsumer();

        var cep = JOptionPane.showInputDialog(null, "Digite o CEP:", "Buscar CEP", JOptionPane.QUESTION_MESSAGE);


        var endereco = cepConsumer.getEndereco(cep);

        var enderecoBuilder = new StringBuilder();


        JOptionPane.showMessageDialog(null, enderecoBuilder.append("Endereço: \n")
                .append("Cidade : " + endereco.getLocalidade()).append("\n")
                .append("UF: " + endereco.getUf()).append("\n")
                .append("Gia: " + endereco.getGia()).append("\n")
                .append("Bairro: " + endereco.getBairro()).append("\n")
                .append("Complemento: " + endereco.getComplemento()).append("\n")
                .append("Logradouro: " + endereco.getLogradouro()).append("\n")
                .append("CEP: " + endereco.getCep()).append("\n"));


    }
}


