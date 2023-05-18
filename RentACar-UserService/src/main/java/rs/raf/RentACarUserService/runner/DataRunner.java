package rs.raf.RentACarUserService.runner;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import rs.raf.RentACarUserService.domain.Rank;
import rs.raf.RentACarUserService.domain.Role;
import rs.raf.RentACarUserService.domain.User;
import rs.raf.RentACarUserService.repository.RankRepository;
import rs.raf.RentACarUserService.repository.RoleRepository;
import rs.raf.RentACarUserService.repository.UserRepository;

import java.util.Date;

@AllArgsConstructor
@Profile({"default"})
@Component
public class DataRunner implements CommandLineRunner {

    private RoleRepository roleRepository;
    private UserRepository userRepository;

    private RankRepository rankRepository;

    @Override
    public void run(String[] args) throws Exception {
        Role roleAdmin = new Role("ADMIN", "Admin role");
        roleRepository.save(roleAdmin);
        roleRepository.save(new Role("MANAGER", "Manager role"));
        roleRepository.save(new Role("CLIENT", "Client role"));

        User admin = new User("admin", "admin1234", "admin@raf.rs", "+381000000000", new Date(97, 4, 31),
                "Ognjen", "Minic");
        admin.setRole(roleAdmin);
        userRepository.save(admin);

        Rank rank = new Rank("Bronze", "Bronze rank", 0,15,0);
        rankRepository.save(rank);
    }
}
