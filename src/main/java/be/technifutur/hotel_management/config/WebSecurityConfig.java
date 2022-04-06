package be.technifutur.hotel_management.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

// WebSecurityConfigurerAdapter permet de redéfinir les méthodes qui définissent la sécurité de mon app
// Il faut aussi l'annoter avec Configuration pour le container Spring
// Ainsi qu'activer la sécurité pour que je puisse faire des modifications avec EnableWebSecurity
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // Après avoir créé PasswordEncryptionConfig
    private final PasswordEncoder encoder;

    public WebSecurityConfig(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    // AuthenticationManagerBuilder crée l'Objet qui va définir si je suis connecté ou non, et avec quel rôle (authorities)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Forme la plus simple d'authentification : en mémoire (pas bien du tout)
        // Données supprimées quand l'app est fermée
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(encoder.encode("userpassword"))
                .authorities("USER")
                .and()
                .withUser("admin")
                .password(encoder.encode("adminpassword"))
                .authorities("USER", "ADMIN");
                // => Pas bien car on doit prendre l'habitude de ne pas donner nos mdp en dur, au clair
                // GOTO create PasswordEncryptionConfig
    }

    // Ensuite je veux sécuriser les méthodes grâce à HttpSecurity
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // D'abord désactiver le csrf sinon problème d'update
        // csrf permet d'utiliser le compte de qq d'autre pour simuler qu'on est lui
        // Ta requête ne se lance pas sur ton PC mais depuis celui qui possède les droits
        http.csrf().disable();
        // Plus tard on utilisera JWT évidemment, mais pour le moment : httpBasic
        http.httpBasic();

        // Permet de ne pas conserver l'état en mémoire
        // Si je tente un GET dans Postman, ne retient aucun cookie
        http.sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Processus d'autorisation ou non à différents éléments
        // D'abord, je permets tout à tout le monde
        http.authorizeRequests()
//                .antMatchers("/demo/for-all")
                // L'ordre de déclaration est important
                // Du plus spécifique au plus général - les 3 premières étant interchangeable car au même niveau
                .antMatchers("/demo/for-connected").authenticated()
                .antMatchers("/demo/for-user").hasAuthority("USER")
                .antMatchers("/demo/for-admin").hasAuthority("ADMIN")
                .anyRequest().permitAll();
    }
}
