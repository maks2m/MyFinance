package ru.elkin.myfinance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.elkin.myfinance.entity.Role;
import ru.elkin.myfinance.entity.User;
import ru.elkin.myfinance.repo.RoleRepo;
import ru.elkin.myfinance.repo.UserRepo;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService, TransferEntityService<User> {

    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @Autowired
    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.roleRepo = roleRepo;
    }

    //UserDetailsService impl
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }
    // ***********************

    //EntityService impl
    @Override
    public void list(Model model) {
        model.addAttribute("userList", userRepo.findAllByOrderById());
    }

    @Override
    public void create(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roleList", roleRepo.findAllByOrderById());

    }

    @Override
    public void edit(User item, Model model) {
        model.addAttribute("user", item);
        model.addAttribute("roleList", roleRepo.findAllByOrderById());
    }

    @Override
    public void save(User itemFromDB, Map<String, String> mapModel) {
        if (itemFromDB == null) {
            itemFromDB = new User();
            itemFromDB.getRoles().add(roleRepo.findByRole("USER"));
            itemFromDB.setActive(true);

            User userWithDuplicateUsername = userRepo.findByUsername(mapModel.get("username"));

            if (userWithDuplicateUsername != null) {
                mapModel.put("errorAddUser", "User exists!");
                return;
            }
        }
        itemFromDB.getRoles().clear();
        Map<String, Role> roles = roleRepo.findAllByOrderById().stream().collect(Collectors.toMap(Role::getRole, r->r));
        System.out.println();
        for (String key : mapModel.keySet()) {
            if (roles.containsKey(key)){
                itemFromDB.getRoles().add(roles.get(key));
            }
            switch (key){
                case "username":
                    itemFromDB.setUsername(mapModel.get(key));
                    break;
                case "password":
                    if (!mapModel.get(key).equals("")) itemFromDB.setPassword(passwordEncoder.encode(mapModel.get(key)));
                    break;
                case "full_name":
                    itemFromDB.setFullName(mapModel.get(key));
                    break;
                case "first_name":
                    itemFromDB.setFirstName(mapModel.get(key));
                    break;
                case "last_name":
                    itemFromDB.setLastName(mapModel.get(key));
                    break;
                case "active":
                    itemFromDB.setActive(true);
                    break;
            }
        }
        userRepo.save(itemFromDB);

    }

    @Override
    public void delete(User item) {
        userRepo.delete(item);
    }
    // *************************
}
