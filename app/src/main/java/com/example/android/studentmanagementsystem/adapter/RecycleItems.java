package com.example.android.studentmanagementsystem.adapter;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.studentmanagementsystem.Main2Activity;
import com.example.android.studentmanagementsystem.R;
import com.example.android.studentmanagementsystem.Model.Studentinfo;

import java.util.ArrayList;


/**
 * recyler view is applied here
 */
public class RecycleItems extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Studentinfo> mstudentInfoArrayList;
    private Context context;
    private TextView nameTextView;
    private TextView rollnoTextView;
    private TextView schoolTextView;
    private TextView emailTextView;
    //Activity activity = (Activity) Context;

    /**
     * @param context has context
     * @param al      array list
     */
    public RecycleItems(final Context context, final ArrayList<Studentinfo> al) {
        this.mstudentInfoArrayList = al;
        this.context = context;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {

        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.activity_recycle_items, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        Studentinfo objinfo = mstudentInfoArrayList.get(position);

        TextView textViewRollno = rollnoTextView;
        textViewRollno.setText(objinfo.getRollno());
        TextView textViewName = nameTextView;
        textViewName.setText(objinfo.getName());
        TextView textViewSchoolName = schoolTextView;
        textViewSchoolName.setText(objinfo.getSchool());
        TextView textViewEmail = emailTextView;
        textViewEmail.setText(objinfo.getEmail());
    }

    /**
     * @return context
     */

    private Context getContext() {
        return context;
    }

    /**
     * @return size
     */

    @Override
    public int getItemCount() {
        return mstudentInfoArrayList.size();
    }

    /**
     * viewholder class
     */

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        /**
         *
         * @param itemView has items we want to display
         */
        public ViewHolder(final View itemView) {

            super(itemView);

            rollnoTextView = (TextView) itemView.findViewById(R.id.tvrollno);
            nameTextView = (TextView) itemView.findViewById(R.id.tvname);
            schoolTextView = (TextView) itemView.findViewById(R.id.tvschoolname);
            emailTextView = (TextView) itemView.findViewById(R.id.tvemail);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(final View v) {
            final int pos = getAdapterPosition();


            AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
            builder.setTitle(R.string.dialog_fire_missiles);
            builder.setPositiveButton(R.string.view_dialog, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(final DialogInterface dialog, final int id) {
                    Intent intent = new Intent(context, Main2Activity.class);
                    Studentinfo obj = mstudentInfoArrayList.get(pos);
                    intent.putExtra("key", "view");
                    intent.putExtra("object", obj);
                    context.startActivity(intent);
                }
            });
            builder.setNegativeButton(R.string.edit_dialog, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(final DialogInterface dialog, final int id) {
                    Intent intent = new Intent(context, Main2Activity.class);
                    Studentinfo obj = mstudentInfoArrayList.get(pos);
                    intent.putExtra("object", obj);
                    intent.putExtra("position", pos);
                    ((Activity) context).startActivityForResult(intent, 2);
                }
            });
            builder.setNeutralButton(R.string.delete_dialog, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(final DialogInterface dialog, final int id) {
                    mstudentInfoArrayList.remove(pos);
                    notifyDataSetChanged();
                }
            });
            builder.create();
            builder.show();


        }
    }
}
