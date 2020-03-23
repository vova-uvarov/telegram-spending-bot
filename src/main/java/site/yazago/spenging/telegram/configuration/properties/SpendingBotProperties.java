package site.yazago.spenging.telegram.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;

@Setter
@Getter
@Component
@ConfigurationProperties("app.telegram.bot")
public class SpendingBotProperties {
    private String name;
    private String token;
    private String proxyHost;
    private Integer proxyPort;
    private DefaultBotOptions.ProxyType proxyType;
    private String proxyUser;
    private String proxyPassword;
}
