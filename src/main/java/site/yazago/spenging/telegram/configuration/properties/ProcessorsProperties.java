package site.yazago.spenging.telegram.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties("app.telegram.processor")
public class ProcessorsProperties {
    private Integer maxMessageTime;
    private String defaultProcessorMessage;
    private String errorProcessorMessage;
    private String helpProcessorMessage;
}
