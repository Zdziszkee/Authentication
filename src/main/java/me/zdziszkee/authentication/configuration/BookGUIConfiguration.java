package me.zdziszkee.authentication.configuration;

import com.twodevsstudio.simplejsonconfig.api.Config;
import com.twodevsstudio.simplejsonconfig.interfaces.Configuration;
import lombok.Getter;

@Getter
@Configuration("bookGuiConfiguration.json")
public class BookGUIConfiguration extends Config {
    private String genderInfo = "https://wystern.com/rules";
}
