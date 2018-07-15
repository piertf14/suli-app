package com.suli.suliapp.ui.callbacks;

import com.suli.suliapp.data.models.post.response.AgentResponse;

import java.util.List;

public interface GetAgentsCallback {
    void onSuccess(List<AgentResponse> agents);
    void onFailure(String error);
}
