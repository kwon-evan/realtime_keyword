package com.kumoh.realtimekeyword.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.kumoh.realtimekeyword.R
import com.kumoh.realtimekeyword.myAdapter


class SportsFragment : Fragment() {
    lateinit var mRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_hot_topic, container, false)

        mRecyclerView = view.findViewById(R.id.recyclerView)
        mRecyclerView.setHasFixedSize(true)

        mRecyclerView.layoutManager = LinearLayoutManager(view.context)

        val database = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference = database.getReference("/sports")


        // Read from the database
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val rankList: Array<Array<String>> =
                    arrayOf(
                        Array(7) { dataSnapshot.child("0").child("$it").value.toString()},
                        Array(7) { dataSnapshot.child("1").child("$it").value.toString()},
                        Array(7) { dataSnapshot.child("2").child("$it").value.toString()},
                        Array(7) { dataSnapshot.child("3").child("$it").value.toString()},
                        Array(7) { dataSnapshot.child("4").child("$it").value.toString()},
                        Array(7) { dataSnapshot.child("5").child("$it").value.toString()},
                        Array(7) { dataSnapshot.child("6").child("$it").value.toString()},
                        Array(7) { dataSnapshot.child("7").child("$it").value.toString()},
                        Array(7) { dataSnapshot.child("8").child("$it").value.toString()},
                        Array(7) { dataSnapshot.child("9").child("$it").value.toString()})
                mRecyclerView.adapter = myAdapter(view.context, rankList)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("firebase", "Failed To Get Data!")
            }
        })
        //mRecyclerView.adapter = myAdapter(view.context, textList)


        return view
    }

}