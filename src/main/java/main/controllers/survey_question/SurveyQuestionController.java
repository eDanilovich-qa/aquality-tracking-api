package main.controllers.survey_question;

import main.controllers.BaseController;
import main.exceptions.AqualityException;
import main.exceptions.AqualityPermissionsException;
import main.model.db.dao.survey_question.SurveyQuestionDao;
import main.model.dto.settings.UserDto;
import main.model.dto.survey_question.SurveyQuestionDto;

import java.util.List;

public class SurveyQuestionController extends BaseController<SurveyQuestionDto> {
    private SurveyQuestionDao surveyQuestionDao;

    public SurveyQuestionController(UserDto user) {
        super(user);
        surveyQuestionDao = new SurveyQuestionDao();
    }

    @Override
    public List<SurveyQuestionDto> get(SurveyQuestionDto template) throws AqualityException {
        if (baseUser.isAdmin()) {
            return surveyQuestionDao.searchAll(template);
        } else {
            throw new AqualityPermissionsException("Account is not allowed to get Survey Questions list", baseUser);
        }
    }

    @Override
    public SurveyQuestionDto create(SurveyQuestionDto entity) throws AqualityException {
        return null;
    }

    @Override
    public boolean delete(SurveyQuestionDto entity) throws AqualityException {
        return false;
    }
}
