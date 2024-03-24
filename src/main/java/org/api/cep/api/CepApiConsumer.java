package org.api.cep.api;


import org.api.cep.Model.Address;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;


public class CepApiConsumer {


    public Address getEndereco(String cep) throws IOException, InterruptedException,  DocumentException {
        var url = "http://viacep.com.br/ws/".concat(cep).concat("/xml/");

        var request = HttpRequest.newBuilder()
                .GET().uri(URI.create(url))
                .build();

        var httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();

        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());


        //Dom4j
        Document document = DocumentHelper.parseText(response.body());
        Element root = document.getRootElement();

        Address endereco = new Address();
        endereco.setCep(root.elementText("cep"));
        endereco.setLogradouro(root.elementText("logradouro"));
        endereco.setComplemento(root.elementText("complemento"));
        endereco.setBairro(root.elementText("bairro"));
        endereco.setLocalidade(root.elementText("localidade"));
        endereco.setUf(root.elementText("uf"));
        endereco.setIbge(root.elementText("ibge"));
        endereco.setGia(root.elementText("gia"));
        endereco.setDdd(root.elementText("ddd"));
        endereco.setSiafi(root.elementText("siafi"));

        return endereco;
    }
}