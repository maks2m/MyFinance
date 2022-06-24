package ru.elkin.myfinance.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.elkin.myfinance.dto.RoleDto;
import ru.elkin.myfinance.entity.Role;

import java.util.List;

@Mapper
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper( RoleMapper.class );

    RoleDto mapSingle(Role item);
    @InheritInverseConfiguration
    Role mapSingle(RoleDto dto);

    List<RoleDto> mapList(List<Role> itemList);
    @InheritInverseConfiguration
    List<Role> mapListInvert(List<RoleDto> dtoList);

}
