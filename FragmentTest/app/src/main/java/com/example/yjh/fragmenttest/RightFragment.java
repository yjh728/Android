package com.example.yjh.fragmenttest;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RightFragment extends Fragment {
    /*
    08-07 12:27:21.310 5714-5714/? D/RightFragment: onAttach:
    onCreate:
    08-07 12:27:21.313 5714-5714/? D/RightFragment: onActivityCreated:
        onStart:
    08-07 12:27:21.318 5714-5714/? D/RightFragment: onResume:
    08-07 12:28:00.876 5714-5714/com.example.yjh.fragmenttest D/RightFragment: onPause:
    08-07 12:28:00.877 5714-5714/com.example.yjh.fragmenttest D/RightFragment: onStop:
        onDestroyView:
    08-07 12:28:11.542 5714-5714/com.example.yjh.fragmenttest D/RightFragment: onActivityCreated:
        onStart:
        onResume:
    08-07 12:28:35.152 5714-5714/com.example.yjh.fragmenttest D/RightFragment: onPause:
        onStop:
        onDestroyView:
    08-07 12:28:35.154 5714-5714/com.example.yjh.fragmenttest D/RightFragment: onDestroy:
        onDetach:
    */
    public static final String TAG = "RightFragment";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.right_layout, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated: ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: ");
    }
}
