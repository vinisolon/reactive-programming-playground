package com.vinisolon.section01;

import com.vinisolon.section01.publisher.PublisherImpl;
import com.vinisolon.section01.subscriber.SubscriberImpl;

import java.time.Duration;

public class MainApp {

	public static void main(String[] args) throws InterruptedException {
		demo01Section01();
		demo02Section01();
		demo03Section01();
		demo04Section01();
	}

	private static void demo01Section01() {
		var publisher = new PublisherImpl();
		var subscriber = new SubscriberImpl();
		publisher.subscribe(subscriber);
	}

	private static void demo02Section01() throws InterruptedException {
		var publisher = new PublisherImpl();
		var subscriber = new SubscriberImpl();
		publisher.subscribe(subscriber);
		subscriber.getSubscription().request(3);
		Thread.sleep(Duration.ofSeconds(2).toMillis());
		subscriber.getSubscription().request(4);
		Thread.sleep(Duration.ofSeconds(2).toMillis());
		subscriber.getSubscription().request(5);
		Thread.sleep(Duration.ofSeconds(2).toMillis());
		subscriber.getSubscription().request(6);
	}

	private static void demo03Section01() throws InterruptedException {
		var publisher = new PublisherImpl();
		var subscriber = new SubscriberImpl();
		publisher.subscribe(subscriber);
		subscriber.getSubscription().request(3);
		Thread.sleep(Duration.ofSeconds(2).toMillis());
		subscriber.getSubscription().cancel();
		Thread.sleep(Duration.ofSeconds(2).toMillis());
		subscriber.getSubscription().request(3);
	}

	private static void demo04Section01() throws InterruptedException {
		var publisher = new PublisherImpl();
		var subscriber = new SubscriberImpl();
		publisher.subscribe(subscriber);
		subscriber.getSubscription().request(3);
		Thread.sleep(Duration.ofSeconds(2).toMillis());
		subscriber.getSubscription().request(11);
		Thread.sleep(Duration.ofSeconds(2).toMillis());
		subscriber.getSubscription().request(3);
	}

}
