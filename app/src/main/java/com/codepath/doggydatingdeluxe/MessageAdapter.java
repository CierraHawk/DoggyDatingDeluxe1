package com.codepath.doggydatingdeluxe;

import android.os.Message;
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
import java.util.List;








public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {


    public static final int MESSAGE_OUTGOING = 123;
    public static final int MESSAGE_INCOMING = 321;

    private Context mContext;
    private List<Message> mMessages;
    private String mUserId;

    public abstract class MessageViewHolder extends RecyclerView.ViewHolder{

        public MessageViewHolder(@NonNull View itemView){
            super(itemView);
        }

        abstract void bindMessage(Message message);

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

        @Override
        void bindMessage(Message message) {

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
        return false;
    }


    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.MessageViewHolder holder, int position) {

    }



    @Override
    public int getItemCount(){
        return mMessages.size();
    }

}
