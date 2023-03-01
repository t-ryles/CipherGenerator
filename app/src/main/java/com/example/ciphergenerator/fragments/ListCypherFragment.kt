package com.example.ciphergenerator.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ciphergenerator.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListCypherFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_list_cypher, container, false)

        view.findViewById<FloatingActionButton>(R.id.backButton).setOnClickListener {
            findNavController().navigate(R.id.action_cypherListFragment_to_addCypherFragment)
        }


        return view
    }
}