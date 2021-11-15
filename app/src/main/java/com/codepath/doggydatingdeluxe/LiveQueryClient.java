package com.codepath.doggydatingdeluxe;

import android.app.Application;

import com.parse.Parse;

public class LiveQueryClient extends Application {

      public void onCreate() {
            super.onCreate();

            Parse.initialize(new Parse.Configuration.Builder(this)
                    .applicationId("CLS4prRYF8J9ZIkIUZYRf1EfnLZohroioqDZbhAW")
                    .clientKey("f8NsMTi6EIhX0XY7BT06ujJ99cE0bUJ4ZOw1Lms6")
                    .server("https://parseapi.back4app.com")
                    .build());


      }
}
