package com.codepath.doggydatingdeluxe.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.codepath.doggydatingdeluxe.Message;
import com.codepath.doggydatingdeluxe.R;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class MessageFragment extends Fragment {
    private RecyclerView messagePost;
    public static final String TAG = "MessageFragment";
    static final String USER_ID_KEY = "userId";
    static final String BODY_KEY = "body";
    private EditText etMessage;
    private ImageButton ibSend;


    public MessageFragment() {
        // Required empty public constructor
    }

    // ** YOUR ONCREATE WAS SUPPOSED TO BE ONCREATEVIEW **
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceStates){
        super.onViewCreated(view, savedInstanceStates);

        etMessage = view.findViewById(R.id.etMessage);
        ibSend = view.findViewById(R.id.ibSend);


    }


    // Get the userId from the cached currentUser object
    void startWithCurrentUser() {
        setupMessagePosting();
    }




    // Set up button event handler which posts the entered message to Parse
    void setupMessagePosting() {

        // When send button is clicked, create message object on Parse
        ibSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              String data = etMessage.getText().toString();
              Message message = new Message();
              message.setUserId(ParseUser.getCurrentUser().getObjectId());
              message.setBody(data);
                message.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(getContext(), "Successfully created message on Parse",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e(TAG, "Failed to save message", e);
                        }
                    }
                });
            }
        });
    }
}



