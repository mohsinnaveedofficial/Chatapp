package com.example.chatapp.frag;

import android.os.Bundle;
import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.chatapp.MainActivity;
import com.example.chatapp.adapter.useradapter;
import com.example.chatapp.models.modelofuser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.example.chatapp.R;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.auth.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private CollectionReference usersCollectionRef;
    private FirebaseFirestore firestore;
    RecyclerView recyclerView;
    private useradapter userAdapter;
    private int contentView;
    private List<modelofuser> userList;
    public Fragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment1.
     */
    // TODO: Rename and change types and number of parameters




    public static Fragment1 newInstance(String param1, String param2) {
        Fragment1 fragment = new Fragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }




            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container,
                    Bundle savedInstanceState) {
                View view = inflater.inflate(R.layout.fragment_1, container, false);
userList = new ArrayList<>();
                // Initialize RecyclerView and adapter
                recyclerView = view.findViewById(R.id.recyclerView);
                userAdapter = new useradapter(getContext(),userList);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                recyclerView.setAdapter(userAdapter);
                userAdapter.notifyDataSetChanged();

                // Fetch user data from Firestore
                fetchUserData();
                return view;


            }

            private void fetchUserData() {
                firestore = FirebaseFirestore.getInstance();
                CollectionReference usersCollectionRef = firestore.collection("user");
                usersCollectionRef.get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<modelofuser> userList = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            modelofuser user = document.toObject(modelofuser.class);
                            userList.add(user);
                        }
                        userAdapter.setUserList(userList);
                    } else {

                        // Handle error
                    }
                });
            }

}


