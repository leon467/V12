package Infra;

public class DataList {
    public static String setJSON(String method, String scenario) {
        String methodName = method;

        if (methodName.equals("EmptyCredentials")) {
            String username = "null";
            String password = "null";


            String jsonString = "{\n" +
                    "  \"" + methodName + "\": [\n" +
                    "    {\n" +
                    "      \"Username\": \"" + username + "\",\n" +
                    "      \"Password\": \"" + password + "\",\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}";

            return jsonString;
        }

        if (methodName.equals("InvalidCredentials")) {
            String username = "Leon123";
            String password = "Leon@123";


            String jsonString = "{\n" +
                    "  \"" + methodName + "\": [\n" +
                    "    {\n" +
                    "      \"Username\": \"" + username + "\",\n" +
                    "      \"Password\": \"" + password + "\",\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}";

            return jsonString;
        }

        if (methodName.equals("VerifyingCredentials")) {
            String Scenario = scenario;

            String RemoveOpenBracket = Scenario.replace("[", "");
            String RemoveCloseBracket = RemoveOpenBracket.replace("]", "");

            String[] ScenarioParts = RemoveCloseBracket.split(",");

            String username = ScenarioParts[0];
            String password = ScenarioParts[1];


            String jsonString = "{\n" +
                    "  \"" + methodName + "\": [\n" +
                    "    {\n" +
                    "      \"Username\": \"" + username + "\",\n" +
                    "      \"Password\": \"" + password + "\",\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}";

            return jsonString;
        }

        return"{}";
    }
}
