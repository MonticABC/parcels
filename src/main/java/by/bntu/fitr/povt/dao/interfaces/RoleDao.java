package by.bntu.fitr.povt.dao.interfaces;

import by.bntu.fitr.povt.dao.entities.Role;

import java.util.List;

public interface RoleDao {
    Role getRoleById(int id);
    List<Role> getList();
}
