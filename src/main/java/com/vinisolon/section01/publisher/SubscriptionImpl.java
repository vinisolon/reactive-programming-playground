package com.vinisolon.section01.publisher;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@Slf4j
public class SubscriptionImpl implements Subscription {

    private static final short MAX_ITEMS = 10;

    private final Subscriber<? super String> subscriber;

    private final Faker faker;

    private boolean isCanceled;

    public SubscriptionImpl(Subscriber<? super String> subscriber) {
        this.subscriber = subscriber;
        this.faker = Faker.instance();
    }

    @Override
    public void request(long requested) {
        if (isAmountRequestedValid(requested) && Boolean.FALSE.equals(this.isCanceled)) {
            log.info("Subscriber has requested {} items", requested);
            sendData(requested);
        }
    }

    @Override
    public void cancel() {
        log.info("Subscription canceled!");
        this.isCanceled = true;
    }

    private boolean isAmountRequestedValid(long requested) {
        if (requested > MAX_ITEMS) {
            var errorMessage = String.format("Requested more items than permitted!%nRequested %d%nPermitted %d", requested, MAX_ITEMS);
            this.subscriber.onError(new RuntimeException(errorMessage));
            this.cancel();
            return false;
        }
        return true;
    }

    private void sendData(long requested) {
        int current;

        for (current = 0; current < requested && current < MAX_ITEMS; current++) {
            this.subscriber.onNext(faker.internet().emailAddress());
        }

        if (current == MAX_ITEMS) {
            finishSubscription();
        }
    }

    private void finishSubscription() {
        log.info("No more data to produce!");
        this.subscriber.onComplete();
        this.cancel();
    }

}
