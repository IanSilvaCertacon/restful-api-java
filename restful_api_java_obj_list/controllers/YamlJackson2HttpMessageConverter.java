package br.com.certacon.restful_api_java_obj_list.controllers;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class YamlJackson2HttpMessageConverter extends AbstractHttpMessageConverter<Object> {

    private final Yaml yaml;

    public YamlJackson2HttpMessageConverter() {
        super(MediaType.parseMediaType("application/x-yaml"));
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setPrettyFlow(true);
        this.yaml = new Yaml(options);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        // Você pode adicionar lógica aqui para determinar quais classes são suportadas
        return true;
    }

    @Override
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        try (InputStreamReader reader = new InputStreamReader(inputMessage.getBody(), StandardCharsets.UTF_8)) {
            return yaml.loadAs(reader, clazz);
        }
    }

    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        try (OutputStreamWriter writer = new OutputStreamWriter(outputMessage.getBody(), StandardCharsets.UTF_8)) {
            yaml.dump(object, writer);
        }
    }
}
