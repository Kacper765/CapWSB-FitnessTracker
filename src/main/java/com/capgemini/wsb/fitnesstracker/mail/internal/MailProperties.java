package com.capgemini.wsb.fitnesstracker.mail.internal;

import com.capgemini.wsb.fitnesstracker.mail.api.EmailSender;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * Configuration of the {@link EmailSender} (additional to the Spring mail configuration for {@link JavaMailSender} bean autoconfiguration).
 */
@ConfigurationProperties(prefix = "mail")
@Getter
@RequiredArgsConstructor
public class MailProperties {

    private String host = "";
    @Setter
    private int port;
    private String username = "";
    private String password = "";

    public MailProperties(String host, String username, String password) {
        this.host = host;
        this.username = username;
        this.password = password;
    }

}
