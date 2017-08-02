package layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.peter.parkinglotapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peter on 01/08/17.
 */

public class InfoAdaptor extends ArrayAdapter{
    List list = new ArrayList<>();
    public InfoAdaptor(Context context, int resource){
        super(context, resource);
    }

    public void add(ParkingInfo object){
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position){
        return list.get(position);

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        InfoHolder infoholder;
        View row;
        row = convertView;
        if(row == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            infoholder = new InfoHolder();
            infoholder.tx_id = (TextView) row.findViewById(R.id.textItemId);
            infoholder.tx_occ = (TextView) row.findViewById(R.id.textItemOcc);
            infoholder.tx_arr = (TextView) row.findViewById(R.id.textItemArr);
            infoholder.tx_pur= (TextView) row.findViewById(R.id.textItemPur);
            infoholder.tx_leave = (TextView) row.findViewById(R.id.textItemLeave);
            row.setTag(infoholder);
        }
        else
        {
            infoholder = (InfoHolder) row.getTag();
        }
        ParkingInfo parkingInfo = (ParkingInfo) this.getItem(position);
        infoholder.tx_id.setText(parkingInfo.getLotId());
        infoholder.tx_occ.setText(parkingInfo.getIsOcc());
        infoholder.tx_arr.setText(parkingInfo.getArrivalTime());
        infoholder.tx_pur.setText(parkingInfo.getPurchasedTime());
        infoholder.tx_leave.setText(parkingInfo.getLeaveTime());

        return  row;
    }
    static class InfoHolder{
        TextView tx_id,tx_occ,tx_arr,tx_pur,tx_leave;
    }

}