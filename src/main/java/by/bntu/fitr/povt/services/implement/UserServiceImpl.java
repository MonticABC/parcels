package by.bntu.fitr.povt.services.implement;

import by.bntu.fitr.povt.dao.entities.Role;
import by.bntu.fitr.povt.dao.interfaces.RoleDao;
import by.bntu.fitr.povt.services.UserService;
import by.bntu.fitr.povt.dao.entities.User;
import by.bntu.fitr.povt.dao.interfaces.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier(value = "encoder")
    public void setEncoder(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    BCryptPasswordEncoder encoder;


    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    UserDao userDao;

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    RoleDao roleDao;

    @Override
    @Transactional
    public void add(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoleByRole(roleDao.getRoleById(user.getRole()));
        userDao.add(user);


    }

    @Override
    @Transactional
    public void remove(int id) {
        userDao.remove(id);
    }

    @Override
    @Transactional
    public void update(User user) {
      //  user.setPassword(encoder.encode(user.getPassword()));
        userDao.update(user);
    }

    @Override
    @Transactional
    public List<User> getList() {
        return userDao.getList();
    }

    @Override
    @Transactional
    public User getById(int id) {
        return userDao.getById(id);
    }

    @Override
    @Transactional
    public User getByUsername(String username) {
        return userDao.getByUsername(username);
    }

    @Transactional
    @Override
    public User getByEmail(String email)
    {
        return userDao.getByEmail(email);
    }
}
