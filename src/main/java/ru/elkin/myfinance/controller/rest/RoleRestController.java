package ru.elkin.myfinance.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.elkin.myfinance.dto.RoleDto;
import ru.elkin.myfinance.entity.Role;
import ru.elkin.myfinance.entity.User;
import ru.elkin.myfinance.mapper.RoleMapper;
import ru.elkin.myfinance.service.rest.RoleService;

import java.util.List;

@RestController
@RequestMapping("/rest/role")
public class RoleRestController extends AbstractRestController<RoleDto, Role, RoleService> {

    protected RoleRestController(RoleService service) {
        super(service);
    }

    @Override
    public List<RoleDto> index(User user) {
        return RoleMapper.INSTANCE.mapList(super.service.index(user));
    }

    @Override
    public RoleDto getOne(Long id, User user) {
        return RoleMapper.INSTANCE.mapSingle(super.service.getOne(id, user));
    }

    @Override
    public RoleDto create(RoleDto modelDto, User user) {
        Role model = RoleMapper.INSTANCE.mapSingle(modelDto);
        return RoleMapper.INSTANCE.mapSingle(super.service.create(model, user));
    }

    @Override
    public RoleDto update(Long id, RoleDto modelDto, User user) {
        Role model = RoleMapper.INSTANCE.mapSingle(modelDto);
        return RoleMapper.INSTANCE.mapSingle(service.update(id, model, user));
    }

}
