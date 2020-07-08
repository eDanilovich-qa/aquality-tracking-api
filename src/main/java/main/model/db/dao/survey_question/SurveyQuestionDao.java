package main.model.db.dao.survey_question;

import main.model.db.dao.DAO;
import main.model.dto.survey_question.SurveyQuestionDto;

public class SurveyQuestionDao extends DAO<SurveyQuestionDto> {

    public SurveyQuestionDao(){
        super(SurveyQuestionDto.class);
        insert = "{call INSERT_SURVEY_QUESTION(?,?,?,?,?,?,?)}";
    }
}
