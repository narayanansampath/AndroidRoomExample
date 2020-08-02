package com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Database.Patient;
import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.R;

import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<Patient> patientList;

    //getting the context and product list with constructor
    public PatientAdapter(Context mCtx, List<Patient> patientList) {
        this.mCtx = mCtx;
        this.patientList = patientList;
    }

    @Override
    public PatientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_patients, parent, false);
        return new PatientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PatientViewHolder holder, int position) {
        //getting the product of the specified position
        Patient patient = patientList.get(position);

        //binding the data with the viewholder views
        holder.textViewPatientName.setText(" Patient Name:       " + patient.getFName() + " " + patient.getLName());
        holder.textViewPatientId.setText(" Patient ID:             " + patient.getPatientId());
        holder.textViewDept.setText(" Patient Dept:         " + String.valueOf(patient.getDept()));
        holder.textViewNurseId.setText(" Nurse ID:                 " + String.valueOf(patient.getNurseId()));
        holder.textViewPatientRoom.setText(" Patient Room:        " + patient.getRoom());
    }


    @Override
    public int getItemCount() {
        return patientList.size();
    }


    class PatientViewHolder extends RecyclerView.ViewHolder {

        TextView textViewPatientName, textViewPatientId, textViewDept, textViewNurseId, textViewPatientRoom;

        public PatientViewHolder(View itemView) {
            super(itemView);

            textViewPatientName = itemView.findViewById(R.id.tvPatientName);
            textViewPatientId = itemView.findViewById(R.id.tvPatientId);
            textViewDept = itemView.findViewById(R.id.tvPatientDepartment);
            textViewNurseId = itemView.findViewById(R.id.tvNurseId);
            textViewPatientRoom = itemView.findViewById(R.id.tvRoom);
        }
    }
}