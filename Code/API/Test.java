/*import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {
        String jsonStr = "[\n" +
                "    {\n" +
                "        \"Klasse\": \"ITF213\",\n" +
                "        \"Vorname\": \"Jan\",\n" +
                "        \"Nachname\": \"Muelfarth\",\n" +
                "        \"Wahl1\": \"1\",\n" +
                "        \"Wahl2\": \"42\",\n" +
                "        \"Wahl3\": \"21\",\n" +
                "        \"Wahl4\": \"12\",\n" +
                "        \"Wahl5\": \"36\",\n" +
                "        \"Wahl6\": \"11\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Klasse\": \"ITF213\",\n" +
                "        \"Vorname\": \"Marvin\",\n" +
                "        \"Nachname\": \"Hardel\",\n" +
                "        \"Wahl1\": \"1\",\n" +
                "        \"Wahl2\": \"42\",\n" +
                "        \"Wahl3\": \"21\",\n" +
                "        \"Wahl4\": \"12\",\n" +
                "        \"Wahl5\": \"36\",\n" +
                "        \"Wahl6\": \"11\"\n" +
                "    }\n" +
                "]";

        ArrayList<String> resultList = parseJSONToList(jsonStr);
        for (String entry : resultList) {
            System.out.println(entry);
        }
    }

    public static ArrayList<String> parseJSONToList(String jsonStr) {
        ArrayList<String> result = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jsonStr);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                StringBuilder sb = new StringBuilder();
                sb.append(jsonObject.getString("Klasse")).append(",");
                sb.append(jsonObject.getString("Vorname")).append(",");
                sb.append(jsonObject.getString("Nachname")).append(",");
                sb.append(jsonObject.getString("Wahl1")).append(",");
                sb.append(jsonObject.getString("Wahl2")).append(",");
                sb.append(jsonObject.getString("Wahl3")).append(",");
                sb.append(jsonObject.getString("Wahl4")).append(",");
                sb.append(jsonObject.getString("Wahl5")).append(",");
                sb.append(jsonObject.getString("Wahl6"));
                result.add(sb.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}*/
