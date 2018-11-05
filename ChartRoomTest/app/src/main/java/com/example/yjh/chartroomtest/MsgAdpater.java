package com.example.yjh.chartroomtest;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MsgAdpater extends RecyclerView.Adapter<MsgAdpater.ViewHolder> {

    private List<Msg> list = new ArrayList<>();

    public MsgAdpater(List<Msg> list) {
        this.list = list;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView leftMsg;
        TextView rightMsg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            leftLayout = itemView.findViewById(R.id.left_layout);
            rightLayout = itemView.findViewById(R.id.right_layout);
            leftMsg = itemView.findViewById(R.id.left_text);
            rightMsg = itemView.findViewById(R.id.right_text);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.msg_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Msg msg = list.get(i);
        if (msg.getType() == Msg.TYPE_RECEIVE) {
            viewHolder.rightLayout.setVisibility(View.VISIBLE);
            viewHolder.leftLayout.setVisibility(View.GONE);
            viewHolder.leftMsg.setText(msg.getContent());
        } else if (msg.getType() == Msg.TYPE_SEND) {
            viewHolder.leftLayout.setVisibility(View.GONE);
            viewHolder.rightLayout.setVisibility(View.VISIBLE);
            viewHolder.rightMsg.setText(msg.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
