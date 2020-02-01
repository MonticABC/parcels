package by.bntu.fitr.povt.services.implement;

import by.bntu.fitr.povt.services.RoleService;
import by.bntu.fitr.povt.dao.entities.Role;
import by.bntu.fitr.povt.dao.interfaces.RoleDao;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class RoleServiceImpl implements RoleService {

    RoleDao roleDao;



    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    @Transactional
    public Role getRoleById(int id) {
        return roleDao.getRoleById(id);
    }

    @Override
    @Transactional
    public List<Role> getList() {
        return roleDao.getList();
    }
}
