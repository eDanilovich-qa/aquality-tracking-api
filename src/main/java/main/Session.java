package main;

import main.controllers.*;
import main.controllers.Administration.AdministrationController;
import main.controllers.Administration.AppSettingsController;
import main.controllers.Project.ProjectController;
import main.controllers.Project.ProjectUserController;
import main.exceptions.AqualityException;
import main.model.db.dao.project.UserDao;
import main.model.db.imports.Importer;
import main.model.db.imports.TestNameNodeType;
import main.model.dto.project.APITokenDto;
import main.model.dto.project.ProjectUserDto;
import main.model.dto.project.TestRunDto;
import main.model.dto.settings.UserDto;
import main.model.email.TestRunEmails;

import javax.naming.AuthenticationException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Session {

    private UserDto user;
    private String session;
    public  ControllerFactory controllerFactory;

    public Session(String sessionId) throws AqualityException, AuthenticationException {
        if(isSessionValid(sessionId)) {
            controllerFactory = new ControllerFactory(user);
            return;
        }
        throw new AuthenticationException("Your session is not valid!");
    }

    public Session(UserDto user) throws AqualityException {
        this.user = user;
        setUserMembership();
        controllerFactory = new ControllerFactory(this.user);
    }

    public List<ProjectUserDto> getProjectPermissions(){
        return user.getProjectUsers();
    }

    public List<ProjectUserDto> getProjectPermissions(Integer projectId){
        return user.getProjectUsers().stream().filter(x -> x.getProject_id().equals(projectId)).collect(Collectors.toList());
    }

    public Importer getImporter(List<String> filePaths, TestRunDto testRunTemplate, String pattern, String format, TestNameNodeType nodeType, boolean singleTestRun) throws AqualityException {
        return new Importer(filePaths, testRunTemplate, pattern, format, nodeType, singleTestRun, user);
    }

    public TestRunEmails getTestRunEmails() throws AqualityException {
        return new TestRunEmails();
    }

    public AuditController getAuditController() {
        return new AuditController(user);
    }

    public AdministrationController getAdministrationController() {
        return new AdministrationController(user);
    }

    public ProjectController getProjectController (){
        return new ProjectController(user);
    }

    public CustomerController getCustomerController () {
        return new CustomerController(user);
    }

    public AppSettingsController getSettingsController () {
        return new AppSettingsController(user);
    }

    public UserDto getCurrentUser() {
        return user;
    }

    private void setUserMembership() throws AqualityException {
        ProjectUserDto projectUserDto = new ProjectUserDto();
        projectUserDto.setUser_id(user.getId());
        user.setProjectUsers(new ProjectUserController(user).getProjectUserForPermissions(projectUserDto));
    }

    public boolean isSessionValid() throws AqualityException {
        return isSessionValid(session);
    }

    private boolean isSessionValid(String sessionId) throws AqualityException {
        UserDao userDao = new UserDao();
        user = userDao.GetAuthorizedUser(sessionId);
        session = sessionId;
        return user != null;
    }
}
