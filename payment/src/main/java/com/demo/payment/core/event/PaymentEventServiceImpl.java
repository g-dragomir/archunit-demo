package com.demo.payment.core.event;

import com.demo.payment.core.ClearCache;
import com.demo.payment.core.util.DateUtils;
import org.springframework.stereotype.Component;

@Deprecated
@Component
@ClearCache
public class PaymentEventServiceImpl implements PaymentEventService {

    @Override
    public void createEvent(PaymentEvent paymentEvent) {

        int dayOfMonth = DateUtils.getDayOfMonthForToday();

        // business logic
    }

}
