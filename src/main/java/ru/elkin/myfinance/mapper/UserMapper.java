package ru.elkin.myfinance.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.elkin.myfinance.dto.UserDto;
import ru.elkin.myfinance.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    UserDto mapSingle(User item);
    @InheritInverseConfiguration
    User mapSingle(UserDto dto);

    List<UserDto> mapList(List<User> itemList);
    @InheritInverseConfiguration
    List<User> mapListInvert(List<UserDto> dtoList);

}
