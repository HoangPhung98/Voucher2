package com.kingphung.voucher2.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.AdapterListUpdateCallback;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kingphung.voucher2.R;
import com.kingphung.voucher2.model.adapter.RestaurantAdapter;
import com.kingphung.voucher2.model.entity.Resaurant;
import com.kingphung.voucher2.presenter.getListMyRestaurant.P_GetListMyRestaurant;
import com.kingphung.voucher2.presenter.getListMyRestaurant.P_I_GetListMyRestaurant;
import com.kingphung.voucher2.view.getListMyRestaurant.V_I_GetListMyRestaurant;

import java.util.ArrayList;


public class ListMyRestaurant extends Fragment
    implements V_I_GetListMyRestaurant {

    private RestaurantAdapter restaurantAdapter;
//    public static ArrayList<Resaurant> listMyResaurant;
    private Context context;
    RecyclerView recyclerView;

    private OnFragmentInteractionListener mListener;

    public ListMyRestaurant() {
        // Required empty public constructor
    }


    public static ListMyRestaurant newInstance(String param1, String param2) {
        ListMyRestaurant fragment = new ListMyRestaurant();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_my_restaurant, container, false);
        //recycler view
        recyclerView = view.findViewById(R.id.recycler_ListRestaurant);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

//        listMyResaurant = new ArrayList<>();

        P_GetListMyRestaurant p_getListMyRestaurant = new P_GetListMyRestaurant(context, this);
        p_getListMyRestaurant.get();
        return view;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
            this.context = context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onCompleteGetListMyRestaurant(ArrayList<Resaurant> listMyRestaurant) {
        if(listMyRestaurant!=null){
            Log.d("kkkListMyRestaurant", listMyRestaurant.size()+"");
            restaurantAdapter = new RestaurantAdapter(context, listMyRestaurant);
            recyclerView.setAdapter(restaurantAdapter);
            restaurantAdapter.notifyDataSetChanged();
        }else{
            Toast.makeText(context, "Something wrong!", Toast.LENGTH_LONG).show();
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
