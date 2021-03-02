package me.zdziszkee.authentication.configuration;

import lombok.Getter;
import me.zdziszkee.authentication.gson.com.twodevsstudio.simplejsonconfig.api.Config;
import me.zdziszkee.authentication.gson.com.twodevsstudio.simplejsonconfig.interfaces.Configuration;

@Getter
@Configuration( "bookGuiConfiguration.json")
public class BookGUIConfiguration extends Config {
}
