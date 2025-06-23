package com.roomify.roomifybackend.services.interfaces;

import com.roomify.roomifybackend.persistence.entity.PageResult;
import com.roomify.roomifybackend.presentation.dto.request.SaveCancellationRequest;
import com.roomify.roomifybackend.presentation.dto.response.CancellationResponse;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;

public interface ICancellationService {

    SaveResponse saveCancellation(SaveCancellationRequest cancellationRequest);

    PageResult<CancellationResponse> getAllCancellations(Integer page, Integer size, boolean orderAsc);

    CancellationResponse getCancellation(Long cancellationId);

}

