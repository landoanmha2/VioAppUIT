package com.example.demotruyen.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.demotruyen.Activity.DanhsachtruyenActivity2;
import com.example.demotruyen.Adapter.TruyenListAdapter;
import com.example.demotruyen.Model.Top3Truyen;
import com.example.demotruyen.Model.Truyen;
import com.example.demotruyen.R;
import com.example.demotruyen.Service.APIService;
import com.example.demotruyen.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Truyen_List extends Fragment {
//Class kế thừa Fragment
    //Khai báo biến
    View view;
    ListView lvDSTruyen;
    TextView txttitleTruyen;

    TruyenListAdapter truyenListAdapter;
    ArrayList<Truyen> mangtruyenList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Phương thức onCreateView dùng để gán View vào Fragment
        view = inflater.inflate(R.layout.fragment_truyen_list, container,false); //gán layout

        //Ánh xạ
        lvDSTruyen = view.findViewById(R.id.listViewTruyenList);
        txttitleTruyen = view.findViewById(R.id.textViewtitlelist);

        GetData(); //Lấy dữ liệu

        return view;
    }

    private void GetData() {
        //Khởi tạo lại APIService
        Dataservice dataservice = APIService.getService();
        //Gửi dữ liệu lên theo thương thức GetDataTruyen, trả dữ liệu qua biến callback
        Call<List<Truyen>> callback = dataservice.GetDataTruyen();
        //Lắng nghe cho sự kiện trả về
        callback.enqueue(new Callback<List<Truyen>>() {
            @Override
            public void onResponse(Call<List<Truyen>> call, Response<List<Truyen>> response) {
                //Nếu thành công
                mangtruyenList = (ArrayList<Truyen>) response.body();

                //Gán dữ liệu vào
                truyenListAdapter = new TruyenListAdapter(getActivity(), android.R.layout.simple_list_item_1, mangtruyenList);
                lvDSTruyen.setAdapter(truyenListAdapter);
                setListViewHeightBasedOnChildren(lvDSTruyen);

                //Bắt sự kiện khi nhấn vào item trong ListView
                lvDSTruyen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //Muốn chuyển màn hình gọi phương thức Intent, màn hình đang đứng getActivity()
                        // chuyển đến màn hình DanhsachtruyenActivity2
                        Intent intent = new Intent(getActivity(), DanhsachtruyenActivity2.class);
                        //Đẩy dữ liệu đi theo từ khóa "itemtruyenlist", muốn chuyển dưới dạng Object Class phải có dạng implements Serializeable
                        intent.putExtra("itemtruyenlist", mangtruyenList.get(position));
                        //Lệnh chuyển màn hình
                        startActivity(intent);
                    }
                });
            }
            @Override
            public void onFailure(Call<List<Truyen>> call, Throwable t) {
                //Nếu thất bại
                Log.d("Thất Bại rồi", "Thử lại đi GetData Fragment Truyen List");
            }
        });
    }
    //Phương thức dùng set lại chiều cao khi vuốt
    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if(listItem != null){
                // This next line is needed before you call measure or else you won't get measured height at all. The listitem needs to be drawn first to know the height.
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();

            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
