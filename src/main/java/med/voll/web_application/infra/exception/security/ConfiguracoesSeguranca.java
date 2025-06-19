package med.voll.web_application.infra.exception.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//Quando incluímos a dependência do spring security, ao inicializarmos o projeto, o spring
// gera uma senha para ser usada na tela de login, também criada pelo spring.
//O usuário padrão é user. A senha é gerada aleatóriamente todas as vezes em que nós iniciali-
//zarmos o projeto.

//O objetivo da criação dessa classe é criarmos as permissões de usuário para a aplicação.
//As anotações @Configuration e @EnableWebSecurity indicam para o sping que essa é uma classe de
//configuração, que a segurança WEB está habilitada e que é o spring quem vai gerencia-la.

@Configuration
@EnableWebSecurity
public class ConfiguracoesSeguranca {

    //A criação dessa bean é para dizer ao spring que ñ queremos mais usar a o AutoConfiguration
    //UserDatailService (o usuário e a senha gerada pelo spring). Vamos criar o nosso proprio
    //UserDetailService
    @Bean
    public UserDetailsService dadosUsuariosCadastrados(){
        UserDetails usuario1 = User.builder()
                .username("joao@email.com")
                .password("{noop}joao123")
                .build();
        UserDetails usuario2 = User.builder()
                .username("maria@email.com")
                .password("{noop}maria123")
                .build();
        UserDetails usuario3 = User.builder()
                .username("luiz@email.com")
                .password("{noop}luiz123")
                .build();
        //Nesse momento não vamos trabalhar com BD. Por esse motivo criamos os usuários
        // e esses serão armazenados na memória
        return new InMemoryUserDetailsManager(usuario1, usuario2, usuario3);

    }

    @Bean
    public SecurityFilterChain filtrosSeguranca(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(req->{
                        req.requestMatchers("/css/**", "/js/**", "/assets/**").permitAll();
                        req.anyRequest().authenticated();
                })
                .formLogin(form ->form.loginPage("/login")
                        .defaultSuccessUrl("/")
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll())
                //.rememberMe(rememberMe -> rememberMe
                        //.key("rememberMe")
                        //.alwaysRemember(true))
                .build();
    }
}
