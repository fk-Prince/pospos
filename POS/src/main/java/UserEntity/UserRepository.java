package UserEntity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

public class UserRepository {

    public static File userFile = new File("users.txt");

    public static boolean isUserExist(User user) throws IOException {
        if (!userFile.exists()) userFile.createNewFile();
        return new BufferedReader(new FileReader(userFile))
                .lines().map(lines -> lines.split(","))
                .anyMatch(lines -> user.getUsername().equalsIgnoreCase(lines[0]) && user.getPassword().equalsIgnoreCase(lines[1]));
    }
}