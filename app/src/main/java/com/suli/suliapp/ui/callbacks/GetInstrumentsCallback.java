package com.suli.suliapp.ui.callbacks;

import com.suli.suliapp.data.models.post.response.InstrumentResponse;

import java.util.List;

public interface GetInstrumentsCallback {
    void onSuccess(List<InstrumentResponse> instruments);
    void onFailure(String error);
}
