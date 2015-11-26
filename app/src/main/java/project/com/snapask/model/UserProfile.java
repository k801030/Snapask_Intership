package project.com.snapask.model;

/**
 * Created by Vison on 2015/11/25.
 */
public class UserProfile {
    int id;
    String email;
    String username;
    String profile_pic_file_name;

    String first_name;
    String last_name;
    String gender;
    String phone;
    String school_name;
    Subjects subjects;

    class Subjects {
        int id;
        String abbr;
        String description;
    }

}