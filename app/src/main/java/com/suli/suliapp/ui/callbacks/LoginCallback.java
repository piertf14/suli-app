package com.suli.suliapp.ui.callbacks;

import com.suli.suliapp.data.models.AccessTokenResponse;

public interface LoginCallback {
    void onSuccess(AccessTokenResponse accessToken);
    void onFailure(String error);
}
