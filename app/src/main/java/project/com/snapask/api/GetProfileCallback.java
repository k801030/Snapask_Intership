package project.com.snapask.api;

import project.com.snapask.model.UserProfile;

/**
 * Created by Vison on 2015/11/26.
 */
public interface GetProfileCallback {
    void onSuccess(UserProfile userProfile);
    void onError(Exception e);
}
