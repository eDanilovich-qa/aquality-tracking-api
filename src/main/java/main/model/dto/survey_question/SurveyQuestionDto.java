package main.model.dto.survey_question;

import lombok.Data;
import lombok.EqualsAndHashCode;
import main.annotations.DataBaseInsert;
import main.annotations.DataBaseName;
import main.model.dto.BaseDto;

@Data @EqualsAndHashCode(callSuper = true)
public class SurveyQuestionDto  extends BaseDto {
    @DataBaseInsert
    @DataBaseName(name = "request_id")
    private Integer id;
    @DataBaseInsert
    @DataBaseName(name = "request_status_id")
    private Integer status_id;
    @DataBaseInsert
    @DataBaseName(name = "request_category_id")
    private Integer category_id;
    @DataBaseInsert
    @DataBaseName(name = "request_question")
    private String question;
    @DataBaseInsert
    @DataBaseName(name = "request_answer")
    private String answer;
    @DataBaseInsert
    @DataBaseName(name = "request_creator_id")
    private Integer creator_id;
    @DataBaseInsert
    @DataBaseName(name = "request_approver_id")
    private Integer approver_id;
}