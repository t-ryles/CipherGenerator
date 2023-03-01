package com.example.ciphergenerator.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ciphergenerator.R
import com.example.ciphergenerator.data.CypherViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListCypherFragment : Fragment() {

    private lateinit var mCypherViewModel : CypherViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_list_cypher, container, false)

        //Adding recycler view
        val adapter = ListAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.cypherRecyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //CypherViewModel
        mCypherViewModel = ViewModelProvider(this).get(CypherViewModel::class.java)
        mCypherViewModel.readAllData.observe(viewLifecycleOwner, Observer { cypher ->
            adapter.setData( cypher )
        })

        view.findViewById<FloatingActionButton>(R.id.backButton).setOnClickListener {
            findNavController().navigate(R.id.action_cypherListFragment_to_addCypherFragment)
        }

        return view
    }
}