package fr.jpierrot.superbowlbackend.service.impl;

import fr.jpierrot.superbowlbackend.pojo.auth.ErrorResponse;
import fr.jpierrot.superbowlbackend.pojo.auth.RegisterResponse;
import fr.jpierrot.superbowlbackend.pojo.entities.Admin;
import fr.jpierrot.superbowlbackend.pojo.entities.Role;
import fr.jpierrot.superbowlbackend.repository.AdminRepository;
import fr.jpierrot.superbowlbackend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public List<Admin> getAllAdmins() {

        return adminRepository.findAllByRoleIs(Role.ROLE_ADMIN);
    }

    @Override
    public Admin getAdminById(Long id) {
        return adminRepository.findByIdAndRoleIs(id, Role.ROLE_ADMIN);
    }

    @Override
    public RegisterResponse createAdmin(Admin newAdmin) {
        String responseBody = ErrorResponse.ERROR_401_UNAUTHORIZED;
        Admin admin = null;

        if(adminRepository.findByEmailIs(newAdmin.getEmail()) != null){
            responseBody = ErrorResponse.ERROR_403_FORBIDDEN;
            return RegisterResponse.builder()
                    .message(responseBody)
                    .build();
        }

        // TODO : Encrypt the password when security is up
        admin = Admin.builder()
                .firstname(newAdmin.getFirstname())
                .lastname(newAdmin.getLastname())
                .email(newAdmin.getEmail())
                .password(newAdmin.getPassword())
                .isEnabled(true)
                .isPwdChecked(false)
                .isSuperAdmin(false)
                .role(Role.ROLE_ADMIN)
                .build();

        /* insert into database */
        admin = adminRepository.save(admin); /* we get newly DBMS inserted infos back here */

        responseBody = RegisterResponse.OK_201_CREATED;

        return RegisterResponse.builder()
                .message(responseBody)
                .id(admin.getId()) /* id returned by DBMS */
                .build();
    }

    @Override
    public RegisterResponse updateAdminById(Admin admin, Long id) {
        return updateAdminByIdWithRole(admin, id, Role.ROLE_ADMIN);
    }

    @Override
    public RegisterResponse updateAdminByIdWithRole(Admin admin, Long id, Role role) {
        Admin adminToUpdate;
        String responseBody = ErrorResponse.ERROR_404_NOT_FOUND; /* Default error message*/

        if(adminRepository.existsById(id)){
            adminToUpdate = adminRepository.findByIdAndRoleIs(id, role);
            if(adminToUpdate != null) {
                if(adminToUpdate.isActivated() && adminToUpdate.hasRole(role)) {
                    // only name and firstname can be modified with this method
                    // email = login and password need special method to be updated
                    adminToUpdate.setLastname(admin.getLastname());
                    adminToUpdate.setFirstname(admin.getFirstname());
                    adminToUpdate.setEmail(admin.getEmail());
                    adminRepository.save(adminToUpdate);
                    responseBody = RegisterResponse.OK_201_UPDATED;
                } else if (!adminToUpdate.isEnabled() || !adminToUpdate.hasRole(role)) {
                    responseBody = ErrorResponse.ERROR_403_FORBIDDEN;
                } else if (!adminToUpdate.isPwdChecked()) {
                    responseBody = ErrorResponse.ERROR_401_ACCOUNT_NOT_CHECKED;
                } else {
                    responseBody = ErrorResponse.ERROR_404_NOT_FOUND;
                }
            }
        }

        return RegisterResponse.builder()
                .message(responseBody)
                .build();
    }



    /**
     * only Admin users can be deleted by this method
     */
    @Override
    public RegisterResponse deleteAdminById(Long id) {
        return deleteAdminByIdWithRole(id, Role.ROLE_ADMIN); /* we force the role ROLE_ADMIN here */
    }

    @Override
    public RegisterResponse deleteAdminByIdWithRole(Long id, Role role) {
        String responseBody = ErrorResponse.ERROR_404_NOT_FOUND;

        Admin adminToDelete = adminRepository.findByIdAndRoleIs(id, role);
        if (adminToDelete != null) {
            if (adminToDelete.hasRole(role)) { // Check ROLE before deleting, must be ROLE_ADMIN
                try {
                    adminRepository.deleteById(id);
                    responseBody = RegisterResponse.OK_201_DELETED;
                } catch (Exception e) {
                    responseBody = e.getMessage().toLowerCase();
                }
            } else {
                responseBody = ErrorResponse.ERROR_403_FORBIDDEN;
            }
        }
        return RegisterResponse.builder()
                .message(responseBody)
                .build();

    }
}
