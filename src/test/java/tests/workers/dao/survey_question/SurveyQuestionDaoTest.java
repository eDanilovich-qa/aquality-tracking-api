package tests.workers.dao.survey_question;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mysql.cj.core.conf.url.ConnectionUrlParser;
import main.exceptions.AqualityException;
import main.model.db.dao.survey_question.SurveyQuestionDao;
import main.model.dto.survey_question.SurveyQuestionDto;
import org.json.JSONArray;
import org.json.JSONException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.workers.dao.IDaoTest;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static utils.Validations.assertSQLToParams;

public class SurveyQuestionDaoTest extends SurveyQuestionDao implements IDaoTest {
    private String currentSql;
    private List<ConnectionUrlParser.Pair<String, String>> currentParameters;
    private List<SurveyQuestionDto> resultList;

    @BeforeMethod
    public void cleanUpResults(){
        resultList = new ArrayList<>();
    }

    public void searchAllTest() throws AqualityException {
        resultList.add(new SurveyQuestionDto());
        resultList.add(new SurveyQuestionDto());
        List<SurveyQuestionDto> result = searchAll(new SurveyQuestionDto());
        assertSQLToParams(currentSql, currentParameters);
        assertEquals(result.size(), 2);
    }

    @Test
    public void insertTest() throws AqualityException {
        resultList.add(new SurveyQuestionDto());
        create(new SurveyQuestionDto());
        assertSQLToParams(currentSql, currentParameters);
    }

    @Test(expectedExceptions = AqualityException.class, expectedExceptionsMessageRegExp = "SQL procedure 'REMOVE' is not define for DAO.+SurveyQuestionDao.+")
    public void removeTest() throws AqualityException {
        delete(new SurveyQuestionDto());
    }

    @Override
    protected JSONArray CallStoredProcedure(String sql, List<ConnectionUrlParser.Pair<String, String>> parameters){
        currentSql = sql;
        currentParameters = parameters;
        try {
            return new JSONArray(dtoMapper.serialize(resultList));
        } catch (JSONException | JsonProcessingException e) {
            e.printStackTrace();
        }
        return new JSONArray();
    }
}
