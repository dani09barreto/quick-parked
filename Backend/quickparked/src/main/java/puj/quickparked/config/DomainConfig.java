package puj.quickparked.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("puj.quickparked.domain")
@EnableJpaRepositories("puj.quickparked.repos")
@EnableTransactionManagement
public class DomainConfig {
}
