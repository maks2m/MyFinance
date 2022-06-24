package ru.elkin.myfinance.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.elkin.myfinance.repo.BaseRepositoryImpl;

@Configuration
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class, basePackages = {"ru.elkin.myfinance.repo"})
public class JpaConfig {

}
