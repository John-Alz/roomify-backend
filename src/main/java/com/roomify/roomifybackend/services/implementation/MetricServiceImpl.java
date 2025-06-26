package com.roomify.roomifybackend.services.implementation;

import com.roomify.roomifybackend.persistence.entity.BookingStatus;
import com.roomify.roomifybackend.persistence.repository.BookingRepository;
import com.roomify.roomifybackend.persistence.repository.CancellationRepository;
import com.roomify.roomifybackend.persistence.repository.UserRepository;
import com.roomify.roomifybackend.presentation.dto.response.MetricsResponse;
import com.roomify.roomifybackend.services.interfaces.IMetricsService;
import com.roomify.roomifybackend.utils.helpers.MetricsHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.YearMonth;

@Service
@RequiredArgsConstructor
public class MetricServiceImpl implements IMetricsService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final CancellationRepository cancellationRepository;

    @Override
    public MetricsResponse getMetrics() {
        YearMonth currentMonth = YearMonth.now();
        YearMonth lastMonth = currentMonth.minusMonths(1);
        int currentMonthValue = currentMonth.getMonthValue();
        int currentYear = currentMonth.getYear();

        int lastMonthValue = lastMonth.getMonthValue();
        int lastYear = lastMonth.getYear();

        // TOTAL RESERVAS
        Long currentBookings = bookingRepository.countByMonth(currentMonthValue, lastYear);
        Long previousBookings = bookingRepository.countByMonth(lastMonthValue, lastYear);
        Double bookingsGrowth = MetricsHelper.calculateGrowth(previousBookings, currentBookings);

        // NUEVOS CLIENTES
//        Long currentNewClients = userRepository.countNewClientsByMonth(currentMonth);
//        Long previousNewClients = userRepository.countNewClientsByMonth(lastMonth);
//        Double newClientsGrowth = MetricsHelper.calculateGrowth(previousNewClients, currentNewClients);

        // CANCELACIONES
        Long currentCancellations = cancellationRepository.countByMonth(currentMonthValue, lastYear);
        Long previousCancellations = cancellationRepository.countByMonth(lastMonthValue, lastYear);
        Double cancelledGrowth = MetricsHelper.calculateGrowth(previousCancellations, currentCancellations);

        // INGRESOS
        BigDecimal currentRevenue = bookingRepository.sumPaidTotalPriceByMonth(currentMonthValue, lastYear, BookingStatus.CONFIRMADA);
        BigDecimal previousRevenue = bookingRepository.sumPaidTotalPriceByMonth(lastMonthValue, lastYear, BookingStatus.CONFIRMADA);
        Double revenueGrowth = MetricsHelper.calculateGrowth(previousRevenue, currentRevenue);

        return new MetricsResponse(
                currentBookings, bookingsGrowth,
//                currentNewClients, newClientsGrowth,
                currentCancellations, cancelledGrowth,
                currentRevenue, revenueGrowth
        );
    }
}
