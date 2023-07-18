package fr.jpierrot.superbowlbackend.service;

import fr.jpierrot.superbowlbackend.pojo.auth.AdminRegisterRequest;
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

    RegisterResponse createAdmin(AdminRegisterRequest newAdmin);

    RegisterResponse createAdminWithRole(AdminRegisterRequest newAdmin, Role role);

    RegisterResponse updateAdminById(Admin admin, Long id);

    RegisterResponse updateAdminByIdWithRole(Admin admin, Long id, Role role);

    // TODO : change returned type to DeleteResponse (not hurry)
    RegisterResponse deleteAdminById(Long id);

    // TODO : change returned type to DeleteResponse (not hurry)
    RegisterResponse deleteAdminByIdWithRole(Long id, Role role);
}
