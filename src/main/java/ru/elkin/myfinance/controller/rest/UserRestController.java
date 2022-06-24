package ru.elkin.myfinance.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.elkin.myfinance.dto.UserDto;
import ru.elkin.myfinance.entity.User;
import ru.elkin.myfinance.mapper.UserMapper;
import ru.elkin.myfinance.service.rest.UserService;

import java.util.List;

@RestController
@RequestMapping("/rest/user")
public class UserRestController extends AbstractRestController<UserDto, User, UserService>{

    protected UserRestController(UserService service) {
        super(service);
    }

    @Override
    public List<UserDto> index(User user) {
        return UserMapper.INSTANCE.mapList(super.service.index(user));
    }

    @Override
    public UserDto getOne(Long id, User user) {
        return UserMapper.INSTANCE.mapSingle(super.service.getOne(id, user));
    }

    @Override
    public UserDto create(UserDto modelDto, User user) {
        User model = UserMapper.INSTANCE.mapSingle(modelDto);
        return UserMapper.INSTANCE.mapSingle(super.service.create(model, user));
    }

    @Override
    public UserDto update(Long id, UserDto modelDto, User user) {
        User model = UserMapper.INSTANCE.mapSingle(modelDto);
        return UserMapper.INSTANCE.mapSingle(service.update(id, model, user));
    }

}
