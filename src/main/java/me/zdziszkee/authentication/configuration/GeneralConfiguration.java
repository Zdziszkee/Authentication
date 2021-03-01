package me.zdziszkee.authentication.configuration;

import com.twodevsstudio.simplejsonconfig.api.Config;
import com.twodevsstudio.simplejsonconfig.interfaces.Configuration;
import lombok.Getter;
import me.zdziszkee.authentication.utils.Coordinates;
import org.bukkit.util.Vector;


import java.util.List;

import static java.util.Arrays.asList;

@Getter
@Configuration(name = "generalConfiguration.json")
public class GeneralConfiguration extends Config {
    private String severNameForTeleporting = "example";
    private List<String> kickMessage =  asList("&6Zostales","&7wyjebany");
    private Coordinates spaceVelocity = new Coordinates(2,2,2);
    private int spaceTeleportDelayInSeconds = 2;
}
