package com.chemicalshop.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "userstatus", schema = "chemshop", catalog = "")
public class UserstatusEntity {
    private int userStatusId;
    private String status;
    private List<UsersEntity> users;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getUserStatusId() {
        return userStatusId;
    }

    public void setUserStatusId(int userStatusId) {
        this.userStatusId = userStatusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @OneToMany(mappedBy = "userStatus")
    public List<UsersEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UsersEntity> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserstatusEntity that = (UserstatusEntity) o;

        if (userStatusId != that.userStatusId) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userStatusId;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
