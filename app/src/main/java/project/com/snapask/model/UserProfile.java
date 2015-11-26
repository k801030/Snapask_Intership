package project.com.snapask.model;

import java.util.Objects;

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
    Subject[] subjects;

    public class Subject {
        int id;
        String abbr;
        String description;
        public String getAbbr() {
            return abbr;
        }
    }


    public String getEmail() {
        return email;
    }

    public String getName() {
        return username;
    }

    public String getPhoto() {
        return profile_pic_file_name;
    }

    public String getPhone() {
        return phone;
    }

    public String getSchoolName() {
        return school_name;
    }

    public Subject[] getSubjects() {
        return subjects;
    }

}