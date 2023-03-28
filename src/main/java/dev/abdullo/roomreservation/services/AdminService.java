package dev.abdullo.roomreservation.services;

import dev.abdullo.roomreservation.dao.UserDAO;
import dev.abdullo.roomreservation.domains.User;

import java.util.List;

public class AdminService {


    private static final AdminService service = ThreadLocal.withInitial(AdminService::new).get();


    public static AdminService getInstance() {
        return service;
    }

    public  List<User> usersList(Long adminId) {
        List<User> users = UserDAO.getInstance().getPage(adminId);
        return users;
    }


    public boolean changeRole(Integer id, String role) {
        //todo check role is valid
        boolean result = UserDAO.getInstance().changeRole(id, role);
        return result;
    }

    public boolean changeDeleted(Integer userId, boolean deleted) {

        //todo check userId  is valid
        boolean result = UserDAO.getInstance().changeDeleted(Long.valueOf(userId), deleted);
        return result;
    }
}