package application.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * Configuration class
 */
@Configuration
    public class AppConfig {

        @Bean
        public Context getContext() throws Exception {
            File configuration = new File("config/configuration.json");

            return new Context(configuration);
        }
    }

