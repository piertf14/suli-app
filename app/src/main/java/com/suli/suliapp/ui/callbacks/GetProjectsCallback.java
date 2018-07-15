package com.suli.suliapp.ui.callbacks;

import com.suli.suliapp.data.models.ProjectResponse;

import java.util.List;

public interface GetProjectsCallback {
    void onSuccess(List<ProjectResponse> projects);
    void onFailure(String error);
}
