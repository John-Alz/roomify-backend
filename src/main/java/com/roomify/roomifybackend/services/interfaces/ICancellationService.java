package com.roomify.roomifybackend.services.interfaces;

import com.roomify.roomifybackend.presentation.dto.request.SaveCancellationRequest;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;

public interface ICancellationService {

    SaveResponse saveCancellation(SaveCancellationRequest cancellationRequest);

}
