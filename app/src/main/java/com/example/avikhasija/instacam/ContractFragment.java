package com.example.avikhasija.instacam;

import android.app.Activity;
import android.app.Fragment;

/**
 * Created by Avik Hasija on 8/1/2015.
 */
public class ContractFragment<T> extends Fragment{

    private T mContract;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            mContract = (T)getActivity();
        }catch (ClassCastException e){
            throw new IllegalStateException("Activity does not implement contract");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContract = null;
    }

    public T getContract(){
        return mContract;
    }
}
