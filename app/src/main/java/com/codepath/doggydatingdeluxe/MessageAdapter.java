package com.codepath.doggydatingdeluxe;

import android.view.ViewGroup;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.parse.ParseFile;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;








public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {


    public static final int MESSAGE_OUTGOING = 123;
    public static final int MESSAGE_INCOMING = 321;

    private Context mContext;
    private List<com.codepath.doggydatingdeluxe.Message> mMessages;
    private String mUserId;

    public abstract class MessageViewHolder extends RecyclerView.ViewHolder{

        public MessageViewHolder(@NonNull View itemView){
            super(itemView);
        }

        abstract void bindMessage(com.codepath.doggydatingdeluxe.Message message);

    }


    public class IncomingMessageViewHolder extends  MessageViewHolder{
        ImageView imageOther;
        TextView body;
        TextView name;


        public IncomingMessageViewHolder(View itemView){
            super(itemView);

            imageOther = itemView.findViewById(R.id.ivProfileOther);
            body = itemView.findViewById(R.id.tvBody);
            name = itemView.findViewById(R.id.tvName);
        }


        void bindMessage(com.codepath.doggydatingdeluxe.Message message) {

            Glide.with(mContext)
                    .load(getProfileUrl(message.getUserId()))
                    .circleCrop() // create an effect of a round profile picture
                    .into(imageOther);
            body.setText(message.getBody());
            name.setText(message.getUserId()); // in addition to message show user ID



        }


    }

    public class OutgoingMessageViewHolder extends MessageViewHolder {
        ImageView imageMe;
        TextView body;

        public OutgoingMessageViewHolder(View itemView) {
            super(itemView);
            imageMe = (ImageView) itemView.findViewById(R.id.ivProfileMe);
            body = (TextView) itemView.findViewById(R.id.tvBody);
        }


        void bindMessage(com.codepath.doggydatingdeluxe.Message message) {

            Glide.with(mContext)
                    .load(getProfileUrl(message.getUserId()))
                    .circleCrop() // create an effect of a round profile picture
                    .into(imageMe);
            body.setText(message.getBody());

        }
    }



    public MessageAdapter(Context context, String userId, List<Message> messages)
    {
        mMessages = messages;
        this.mUserId = userId;
        mContext = context;
    }


    @Override
    public int getItemViewType(int position){
        if(isMe(position)){
            return MESSAGE_OUTGOING;
        }
        else
        {
            return MESSAGE_INCOMING;
        }
    }

    private boolean isMe(int position) {
        com.codepath.doggydatingdeluxe.Message message = mMessages.get(position);
        return message.getUserId() !=null && message.getUserId().equals(mUserId) ;
    }


    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        if (viewType == MESSAGE_INCOMING) {
            View contactView = inflater.inflate(R.layout.message_incoming, parent, false);
            return new IncomingMessageViewHolder(contactView);
        } else if (viewType == MESSAGE_OUTGOING) {
            View contactView = inflater.inflate(R.layout.message_outgoing, parent, false);
            return new OutgoingMessageViewHolder(contactView);
        } else {
            throw new IllegalArgumentException("Unknown view type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.MessageViewHolder holder, int position) {
        com.codepath.doggydatingdeluxe.Message message = mMessages.get(position);
        holder.bindMessage(message);
    }



    @Override
    public int getItemCount(){
        return mMessages.size();
    }

    // Create a gravatar image based on the hash value obtained from userId
    private static String getProfileUrl(final String userId) {
        String hex = "";
        try {
            final MessageDigest digest = MessageDigest.getInstance("MD5");
            final byte[] hash = digest.digest(userId.getBytes());
            final BigInteger bigInt = new BigInteger(hash);
            hex = bigInt.abs().toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "https://www.gravatar.com/avatar/" + hex + "?d=identicon";
    }





}
