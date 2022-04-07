package be.technifutur.hotel_management.business.service.impl;

import be.technifutur.hotel_management.data.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    // Besoin du repository pour accéder aux entités de la DB
    private final UserRepository repository;

    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Je veux trouver un user par sans username, sans faire de findAll() ni trouver par id
        // Dans UserRepository, je crée une méthode findByUsername() grâce à JPA Data
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
    }
}
