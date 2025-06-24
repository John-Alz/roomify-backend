package com.roomify.roomifybackend.presentation.dto.response;

import java.math.BigDecimal;

public record MetricsResponse(
         Long totalBookings,
         Double bookingsGrowth,

//         Long newClients,
//         Double newClientsGrowth,

         Long cancelledBookings,
         Double cancelledGrowth,

         BigDecimal monthlyRevenue,
         Double revenueGrowth
) {
}
