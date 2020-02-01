package by.bntu.fitr.povt.dao.interfaces;

import by.bntu.fitr.povt.dao.entities.User;

import java.util.List;

public interface UserDao {
    User getByEmail(String email);
    void add(User user);
    void remove(int id);
    void update(User user);
    List<User> getList();
    User getById(int id);

    User getByUsername(String username);
}
