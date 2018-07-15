package com.suli.suliapp.ui.callbacks;

import com.suli.suliapp.data.models.post.response.MeasurementValueResponse;

public interface PostMeasurementValueCallback {
    void onSuccess(MeasurementValueResponse measurementValueResponse);
    void onFailure(String error);
}
