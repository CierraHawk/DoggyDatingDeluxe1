package com.codepath.doggydatingdeluxe;
import com.parse.Parse;
import com.parse.ParseObject;
import android.app.Application;

public class ParseApplication extends Application{
       public void onCreate(){
           super.onCreate();

           // Register Parse Models
           ParseObject.registerSubclass(Post.class);
           Parse.initialize(new Parse.Configuration.Builder(this)
                   .applicationId("CLS4prRYF8J9ZIkIUZYRf1EfnLZohroioqDZbhAW")
                   .clientKey("f8NsMTi6EIhX0XY7BT06ujJ99cE0bUJ4ZOw1Lms6")
                   .server("https://parseapi.back4app.com")
                   .build()
           );
       }

}
