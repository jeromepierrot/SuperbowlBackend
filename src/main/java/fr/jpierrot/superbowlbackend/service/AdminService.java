package fr.jpierrot.superbowlbackend.service;

import fr.jpierrot.superbowlbackend.pojo.auth.RegisterResponse;
import fr.jpierrot.superbowlbackend.pojo.entities.Admin;
import fr.jpierrot.superbowlbackend.pojo.entities.Role;

import java.util.List;


public interface AdminService {
    /**
     * ADMIN SERVICES: Read *
     *     // For USERS as ROLE_ADMIN
      */

    Admin getAdminById(Long id);

    /**
     * ADMIN SERVICES: ReadAll/Create/Update/Delete *
     *      // For USERS as ROLE_ADMIN and isSuperAdmin = true
     */

    List<Admin> getAllAdmins(); /* for super admin usage only*/

    RegisterResponse createAdmin(Admin newAdmin);

    RegisterResponse updateAdminById(Admin admin, Long id);

    RegisterResponse deleteAdminById(Long id);

    RegisterResponse deleteAdminByIdWithRole(Long id, Role role);
}
