package me.zdziszkee.authentication.configuration;

import com.twodevsstudio.simplejsonconfig.api.Config;
import com.twodevsstudio.simplejsonconfig.interfaces.Configuration;
import lombok.Getter;


import java.util.List;

import static java.util.Arrays.asList;

@Getter
@Configuration(name = "generalConfiguration.json")
public class GeneralConfiguration extends Config {
    private List<String> kickMessage =  asList("&6Zostales","&7wyjebany");
}
