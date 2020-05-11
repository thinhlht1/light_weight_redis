package code.learning.unittest;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(
        basePackages = {
                "code.learning.api",
                "code.learning.domain",
                "code.learning.unittest"
        })
@Configuration
@EnableAutoConfiguration
public class ApplicationTest {
}

