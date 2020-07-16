package main.view.survey_question;

import main.Session;
import main.model.dto.survey_question.SurveyQuestionDto;
import main.view.BaseServlet;
import main.view.IGet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/survquestions")
public class SurveyQuestionServlet extends BaseServlet implements IGet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        setPostResponseHeaders(resp);
        setEncoding(resp);

        try {
            Session session = createSession(req);
            SurveyQuestionDto surveyQuestionsTemplate = new SurveyQuestionDto();
            surveyQuestionsTemplate.getSearchTemplateFromRequestParameters(req);
            List<SurveyQuestionDto> surveyQuestions = session.controllerFactory.getHandler(surveyQuestionsTemplate).get(surveyQuestionsTemplate);
            setJSONContentType(resp);
            resp.getWriter().write(mapper.serialize(surveyQuestions));
        }catch (Exception e) {
            handleException(resp, e);
        }
    }

    @Override
    public void doOptions(HttpServletRequest req, HttpServletResponse resp){
        setOptionsResponseHeaders(resp);
    }
}