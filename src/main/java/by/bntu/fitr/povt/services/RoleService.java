package by.bntu.fitr.povt.services;

import by.bntu.fitr.povt.dao.entities.Role;

import java.util.*;

public interface RoleService {
    Role getRoleById(int id);
    List<Role> getList();
}
