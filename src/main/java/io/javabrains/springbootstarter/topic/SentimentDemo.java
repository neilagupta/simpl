package io.javabrains.springbootstarter.topic;


import com.aylien.textapi.TextAPIClient;
import com.aylien.textapi.parameters.*;
import com.aylien.textapi.responses.*;

class SentimentDemo {
    public static void main(String[] args) throws Exception {
        TextAPIClient client = new TextAPIClient("17f4651a", "96c204d7f5cf1c91d23f64ac0fa55408");
        SentimentParams.Builder builder = SentimentParams.newBuilder();
        builder.setText("John is a very good football player");
        Sentiment sentiment = client.sentiment(builder.build());

        System.out.println(sentiment);
    }
}