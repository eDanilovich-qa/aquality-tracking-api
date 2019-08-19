package main.exceptions;

public class AqualitySQLException extends RPException {

    public AqualitySQLException(String sqlcode) {
        super(getErrorMessage(sqlcode));
        this.responseCode = getErrorCode(sqlcode);
    }



    private static String getErrorMessage(String sqlcode){
        switch (sqlcode){
            case "23516":
            case "45000":
            case "23505":
                return "You are trying to create duplicate entity.";
            default:
                return "Unknown SQL Error";
        }
    }

    private static Integer getErrorCode(String sqlcode){
        switch (sqlcode){
            case "23516":
            case "45000":
            case "23505":
                return 409;
            default:
                return 500;
        }
    }
}
