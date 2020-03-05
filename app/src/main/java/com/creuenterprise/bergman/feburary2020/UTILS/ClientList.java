package com.creuenterprise.bergman.feburary2020.UTILS;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.creuenterprise.bergman.feburary2020.R;

import java.util.List;

import androidx.annotation.NonNull;

public class ClientList extends ArrayAdapter<Client> {

    private Activity context;
    private List<Client> clientList;

    public ClientList(Activity context, List<Client> clientList)
    {
        super(context, R.layout.list_layout,clientList);
        this.context = context;
        this.clientList = clientList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout,null,true);

        TextView textViewName = listViewItem.findViewById(R.id.textViewFirstName);
        TextView textLastName = listViewItem.findViewById(R.id.textViewLastName);

        Client client = clientList.get(position);
        textViewName.setText(client.getFirstName());
        textLastName.setText(client.getLastName());

        return listViewItem;

    }
}
