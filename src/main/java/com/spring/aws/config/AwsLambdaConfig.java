package com.spring.aws.config;

import com.spring.aws.domain.Character;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class AwsLambdaConfig {

    @Bean
    public Filter getFilter(){
        return new SecurityFilter();
    }

    // GET
    @Bean(name = "Saludar")
    public Supplier<String> greeting() {
        return () -> "Hello world!";
    }

    //POST
    @Bean
    public Consumer<String> printParam() {
        return (param) -> {
            System.out.println(param);
        };
    }

    // GET Y POST
    @Bean
    public Function<String, String> receiveParam(){
        return (param) -> {
            String name = param.toUpperCase();
            return name;
        };
    }

    // Generate to JSON
    @Bean
    public Supplier<Map<String, Object>> createCharacter(){
        return ()-> {
          Map<String, Object> character = new HashMap<>();
          character.put("name", "Goku");
          character.put("healthPoints", 100);
          character.put("skill", "Kame Hame Ha!");

          return character;
        };
    }

    // Reicve un JSON and return a String
    @Bean
    public Function<Map<String, Object>, String> receiveCharacter(){
        return (param) -> {
            param.forEach((key, value) -> System.out.println(key + " - " + value.toString()));

            return "Personaje recibido";
        };
    }

    // Receive an OBJECT and return an OBJECT
    @Bean
    public Function<Character, Character> receivedAnObject(){
        return (param)-> param;
    }

    // Receive a JSON and return a JSON
    @Bean
    public Function<Map<String, Object>, Map<String, Object>> processCharacters(){
        return (param)-> {
            Map<String, Object> mapCharacter = param;

            mapCharacter.forEach((key, value) -> System.out.println(key + " - " + value.toString()));

            Map<String, Object> newCharacter = new HashMap<>();
            newCharacter.put("name", "krillin");
            newCharacter.put("healthPoints", 50);
            newCharacter.put("skills", new String[]{"Ki En Zan!", "Kame hame Ha!"});

            return newCharacter;
        };
    }
}
