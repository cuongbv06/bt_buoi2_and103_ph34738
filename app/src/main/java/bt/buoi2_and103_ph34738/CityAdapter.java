package bt.buoi2_and103_ph34738;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.List;

public class CityAdapter extends ArrayAdapter<City> {
    private Context context;
    private List<City> cityList;

    public CityAdapter(Context context, List<City> cityList) {
        super(context, 0, cityList);
        this.context = context;
        this.cityList = cityList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        City city = cityList.get(position);

        TextView cityNameTextView = convertView.findViewById(android.R.id.text1);
        cityNameTextView.setText(city.getName());

        return convertView;
    }
}
