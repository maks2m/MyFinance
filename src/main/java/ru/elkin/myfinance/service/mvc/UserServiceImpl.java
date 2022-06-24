package ru.elkin.myfinance.service.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.elkin.myfinance.entity.Role;
import ru.elkin.myfinance.entity.User;
import ru.elkin.myfinance.exception.NotFoundException;
import ru.elkin.myfinance.repo.RoleRepo;
import ru.elkin.myfinance.repo.UserRepo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService, UserService<User> {

    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.roleRepo = roleRepo;
    }

    //UserDetailsService impl
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findUserByUsername(username).orElseThrow(NotFoundException::new);
    }
    // ***********************

    //EntityService impl
    @Override
    public List<User> list() {
        return userRepo.findAllByOrderById();
    }

    @Override
    public User create() {
         return new User();
    }

    @Override
    public User getById(Long id) {
         return userRepo.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public boolean save(Long id, Map<String, String> mapModel, Model model) {
        User itemFromDB;
        if (id == null) {
            itemFromDB = new User();
            itemFromDB.getRoles().add(roleRepo.findByRole("USER"));
            itemFromDB.setActive(true);
        } else {
            itemFromDB = getById(id);
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

        if (userRepo.findUserByUsername(mapModel.get("username")).orElse(null) != null) {
            model.addAttribute("errorAddUser", "User exists!");
            model.addAttribute("user", itemFromDB);
            model.addAttribute("roleList", roleRepo.findAllByOrderById());
            return false;
        } else {
            userRepo.save(itemFromDB);
            return true;
        }
    }

    @Override
    public void delete(Long id) {
        userRepo.deleteById(id);
    }
    // *************************
}
