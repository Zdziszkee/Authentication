package me.zdziszkee.authentication.configuration;

import me.zdziszkee.authentication.gson.com.twodevsstudio.simplejsonconfig.api.Config;
import me.zdziszkee.authentication.gson.com.twodevsstudio.simplejsonconfig.interfaces.Configuration;
import lombok.Getter;
import me.zdziszkee.authentication.utils.Coordinates;


import java.util.List;

import static java.util.Arrays.asList;

@Getter
@Configuration("generalConfiguration.json")
public class GeneralConfiguration extends Config {
    private String severNameForTeleporting = "example";
    private List<String> kickMessage =  asList("&6Zostales","&7wyjebany");
    private Coordinates spaceVelocity = new Coordinates(2,2,2);
    private int spaceTeleportDelayInSeconds = 2;
}
