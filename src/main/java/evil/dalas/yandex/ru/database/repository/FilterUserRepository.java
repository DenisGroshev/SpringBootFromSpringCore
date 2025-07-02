package evil.dalas.yandex.ru.database.repository;

import evil.dalas.yandex.ru.dto.PersonalInfo;
import evil.dalas.yandex.ru.dto.PersonalInfo2;
import evil.dalas.yandex.ru.dto.UserFilter;
import evil.dalas.yandex.ru.entity.Role;
import evil.dalas.yandex.ru.entity.User;

import java.util.List;


public interface FilterUserRepository {

    List<User> findAllByFilter(UserFilter filter);

    List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Role role);

    void updateCompanyAndRole(List<User> users);

    void updateCompanyAndRoleNamed(List<User> users);
}
