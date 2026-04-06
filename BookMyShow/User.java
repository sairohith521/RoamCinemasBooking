package BookMyShow;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedHashMap;
import java.util.Map;

public class User {
    private static final String USER_FILE = "users.txt";

    public static String getOrCreateUserId(String email) throws Exception {
        Map<String, String> userMap = readUsers();

        if (userMap.containsKey(email)) {
            return userMap.get(email);
        }

        String userId = "U" + (1000 + userMap.size() + 1);
        userMap.put(email, userId);

        BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE, true));
        bw.write(email + "=" + userId);
        bw.newLine();
        bw.close();

        return userId;
    }

    private static Map<String, String> readUsers() throws Exception {
        Map<String, String> userMap = new LinkedHashMap<>();

        File file = new File(USER_FILE);
        if (!file.exists()) {
            return userMap;
        }

        BufferedReader br = new BufferedReader(new FileReader(USER_FILE));
        String line;
        while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty() || !line.contains("=")) {
                continue;
            }
            String[] parts = line.split("=");
            if (parts.length == 2) {
                userMap.put(parts[0].trim(), parts[1].trim());
            }
        }
        br.close();
        return userMap;
    }
}
