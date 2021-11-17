package com.codepath.doggydatingdeluxe.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codepath.doggydatingdeluxe.LoginActivity;
import com.codepath.doggydatingdeluxe.Post;
import com.codepath.doggydatingdeluxe.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;


public class DoggyDatingFragment extends Fragment {
    public static final String TAG = "DoggyDatingFragment";
    private EditText etModUsername;
    private EditText etModPassword;
    private EditText etModBiography;
    private EditText etModEmail;
    private Button btnUpdate;
    private Button btnDelete;

    public DoggyDatingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doggy_dating, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etModUsername = view.findViewById(R.id.etModUsername);
        etModPassword = view.findViewById(R.id.etModPassword);
        etModBiography = view.findViewById(R.id.etModBiography);
        etModEmail = view.findViewById(R.id.etModEmail);
        btnUpdate = view.findViewById(R.id.btnUpdate);
        btnDelete = view.findViewById(R.id.btnDelete);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUser(etModEmail.getText().toString(),etModUsername.getText().toString(),etModPassword.getText().toString(),etModBiography.getText().toString());
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePosts();
                deleteUser();
            }
        });


    }

    public void updateUser(String Newemail, String Newusername, String Newpassword, String Newbiography) {
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // Other attributes than "email" will remain unchanged!
            currentUser.put("email", Newemail);
            currentUser.put("username", Newusername);
            currentUser.put("password", Newpassword);
            currentUser.put("biography", Newbiography);

            // Saves the object.
            currentUser.saveInBackground(e -> {
                if (e == null) {
                    //Save successful
                    Toast.makeText(getContext(), "Save Successful", Toast.LENGTH_SHORT).show();
                } else {
                    // Something went wrong while saving
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void deleteUser() {
        // Notice that ParseUser extends ParseObject class, so we can
        // use the "remove" method in order to delete a single attribute.
        ParseUser currentUser = ParseUser.getCurrentUser();

        if (currentUser != null) {
            // Deletes the user.
            // Notice that the DeleteCallback is totally optional!
            currentUser.deleteInBackground(e -> {
                if(e==null){
                    //Delete successful
                    Toast.makeText(getContext(), "User was deleted", Toast.LENGTH_SHORT).show();
                    LoginActivity();
                    ParseUser.getCurrentUser().logOut();
                }else{
                    // Something went wrong while deleting
                    Toast.makeText(getContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }



        
    }

    private void deletePosts() {

            ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
            query.include(Post.KEY_USER);
            query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
            query.addDescendingOrder(Post.KEY_CREATED_KEY);
            query.findInBackground(new FindCallback<Post>() {
                @Override
                public void done(List<Post> posts, ParseException e) {
                    if(e != null){
                        Log.e(TAG,"Issue with getting posts", e);
                        return;
                    }
                    for(Post post: posts){
                    query.getInBackground(post.getObjectId(), (object, error) -> {
                        if (error == null) {
                            //Object was fetched
                            //Deletes the fetched ParseObject from the database
                            object.deleteInBackground(e2 -> {
                                if(e2==null){
                                    Toast.makeText(getContext(), "Delete Successful", Toast.LENGTH_SHORT).show();
                                }else{
                                    //Something went wrong while deleting the Object
                                    Toast.makeText(getContext(), "Error: "+e2.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else{
                            //Something went wrong
                            Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                }
            });
        }


    private void LoginActivity() {
        Intent i = new Intent(getContext(), LoginActivity.class);
        startActivity(i);



}
}
