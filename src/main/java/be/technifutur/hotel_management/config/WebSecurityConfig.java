package be.technifutur.hotel_management.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

// WebSecurityConfigurerAdapter permet de redéfinir les méthodes qui définissent la sécurité de mon app
// Il faut aussi l'annoter avec Configuration pour le container Spring
// Ainsi qu'activer la sécurité pour que je puisse faire des modifications avec EnableWebSecurity
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true, // Donne accès à preauthorized - permet @PreAuthorize("permitAll()") dans DemoController
        securedEnabled = true, // Donne accès à secured - permet @Secured("ROLE_USER") dans DemoController
        jsr250Enabled = true // Donne accès à role
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // Après avoir créé PasswordEncryptionConfig
    private final PasswordEncoder encoder;
    private final UserDetailsService userDetailsService;

    public WebSecurityConfig(PasswordEncoder encoder, UserDetailsService userDetailsService) {
        this.encoder = encoder;
        this.userDetailsService = userDetailsService;
    }

    // AuthenticationManagerBuilder crée l'Objet qui va définir si je suis connecté ou non, et avec quel rôle (authorities)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder);

        // Forme la plus simple d'authentification : en mémoire (pas bien du tout)
        // Données supprimées quand l'app est fermée
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password(encoder.encode("userpassword"))
//                // Les rôles donnent accès aux authorities, pas l'inverse
////                .authorities("USER")
//                .roles("USER")
//                .and()
//                .withUser("admin")
//                .password(encoder.encode("adminpassword"))
////                .authorities("USER", "ADMIN")
//                .roles("USER", "ADMIN");
//                // => Pas bien car on doit prendre l'habitude de ne pas donner nos mdp en dur, au clair
//                // GOTO create PasswordEncryptionConfig
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

        // Solution au fait que quand on accède à la DB avec l'user,
        // les frames de ma DB ne s'affichent pas : on voit en console le X Frame Options = DENY
        // Je modifie donc les headers de base, plus particulièrement frameOptions, que je désactive
        // Désormais, je lance ma DB et je n'ai plus le problème de frames
        http.headers()
                .frameOptions()
                .disable();
                // Je peux jouer avec les méthodes de frameOptions()
//                        .cacheControl().disable()
//                        .permissionsPolicy().policy("my policy");

        // Processus d'autorisation ou non à différents éléments
        // D'abord, je permets tout à tout le monde
        http.authorizeRequests()
//                .antMatchers("/demo/for-all")
                // L'ordre de déclaration est important
                // Du plus spécifique au plus général - les 3 premières étant interchangeable car au même niveau
                // Pour les requêtes en GET, il faut être authentifié
//                .antMatchers(HttpMethod.GET, "/hotel/**").authenticated()
//                // Toute autorité (user ou admin) peut GET les gérants
//                .antMatchers(HttpMethod.GET, "/manager/**").hasAnyAuthority("USER", "ADMIN")
//                // Peut être écrit avec role aussi, fait la même chose :
////                .antMatchers(HttpMethod.GET, "/manager/**").hasAnyRole("ROLE_USER", "ROLE_ADMIN")
//                // POST-DELETE-PUT-PATCH ne peuvent être effectués que par l'admin
//                .antMatchers(HttpMethod.POST).hasAuthority("ADMIN")
//                .antMatchers(HttpMethod.DELETE).hasAuthority("ADMIN")
//                .antMatchers(HttpMethod.PUT).hasAuthority("ADMIN")
//                .antMatchers(HttpMethod.PATCH).hasAuthority("ADMIN")
//                .antMatchers("/demo/for-connected").authenticated()
//                .antMatchers("/demo/for-user").hasAuthority("USER")
//                .antMatchers("/demo/for-admin").hasAuthority("ADMIN")
                .anyRequest().permitAll();

        // ... Etc. Avec beaucoup d'accès, on remarque que ça peut générer énormément de lignes
        // On peut donc indiquer @EnableGlobalMethodSecurity en annotation de la classe

        // Soit avec des méthodes comme commenté ci-dessus
        // Soit avec des annotations PreAuthorize-Secured-RolesAllowed comme dans DemoController
        // Conseil : sécurités générales ici
        // Conseil : sécurités spécifiques avec les annotations dans DemoController
    }
}
