package by.bntu.fitr.povt.dao.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idRole;
    private String role;

    @OneToMany(mappedBy = "roleByRole")
    private Collection<User> usersByIdRole;


    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role1 = (Role) o;

        if (idRole != role1.idRole) return false;
        if (role != null ? !role.equals(role1.role) : role1.role != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRole;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }


    public Collection<User> getUsersByIdRole() {
        return usersByIdRole;
    }

    public void setUsersByIdRole(Collection<User> usersByIdRole) {
        this.usersByIdRole = usersByIdRole;
    }
}
