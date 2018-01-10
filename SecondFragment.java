package com.rcpl.tabapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SecondFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class SecondFragment extends Fragment implements View.OnClickListener
{
    Button b1,b2,b3;
    private OnFragmentInteractionListener mListener;

    public SecondFragment()
    {
        // Required empty public constructor
    }

    TextView tv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_second, container, false);

        b1 = (Button) v.findViewById(R.id.button1);
        b1.setOnClickListener(this);
        b2 = (Button) v.findViewById(R.id.button2);
        b2.setOnClickListener(this);
        b3 = (Button) v.findViewById(R.id.button3);
        b3.setOnClickListener(this);

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri)
    {
        if (mListener != null)
        {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener)
        {
            mListener = (OnFragmentInteractionListener) context;
        }
        else
        {
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
    public void onClick(View v)
    {
        if(v.getId()==R.id.button1)
        {
            Intent box = new Intent(getActivity(),Database2Activity.class);
            startActivity(box);
        }
        else if(v.getId()==R.id.button2)
        {
            Intent b = new Intent(getActivity(),DatabaseView2Activity.class);
            startActivity(b);
        }
        else if(v.getId()==R.id.button3)
        {
            Intent c = new Intent(getActivity(),DatabaseView3Activity.class);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener //this is callback hook interface
    {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
