package com.suli.suliapp.ui.callbacks;

import com.suli.suliapp.data.models.post.response.CustodyChainResponse;

public interface PostCustodyChainCallback {
    void onSuccess(CustodyChainResponse custodyChainResponse);
    void onFailure(String error);
}
