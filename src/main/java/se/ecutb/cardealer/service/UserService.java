package se.ecutb.cardealer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import se.ecutb.cardealer.entities.User;
import se.ecutb.cardealer.repository.UserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Cacheable(value = "carCache")
    public List<User> findAll(String name, boolean sortOnBirthday) {
        log.info("Request to find all users");
        log.info("Fresh data...");
        var users = userRepository.findAll();
        if (name != null) {
            users = users.stream()
                    .filter(user -> user.getName().contains(name))
                    .collect(Collectors.toList());
        }
        if (sortOnBirthday){
            users.sort(Comparator.comparing(User::getBirthday));
        }
        return users;
    }

    @Cacheable(value = "carCache", key = "#id")
    public User findById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, // 404 -> Not found
                        String.format("Could not find the user by id %s.", id)));
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Could not find the user by username %s", username)));
    }

    @CachePut(value = "carCache")
    public User save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    //Kanske behövs ändra en del för att kunna anpassa vad vi behöver
    @CachePut(value = "carCache", key = "#id")
    public void update(String id, User user) {
        /*var isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().toUpperCase().equals("ROLES_ADMIN"));
        var isCurrentUser = SecurityContextHolder.getContext().getAuthentication()
                .getName().toLowerCase().equals(user.getUsername().toLowerCase());
        if(!isAdmin && !isCurrentUser){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Update only your own details, you're not admin");
        }*/
        if(!userRepository.existsById(id)) {
            log.error(String.format("Could not find the user by id %s.", id));
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, // 404 -> Not found
                    String.format("Could not find the user by id %s.", id));
        }
        user.setId(id);
        userRepository.save(user);
    }


    @CacheEvict(value = "carCache", key = "#id")
    public void delete(String id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Could not find the user by id %s", id));
        }
        userRepository.deleteById(id);
    }
}
