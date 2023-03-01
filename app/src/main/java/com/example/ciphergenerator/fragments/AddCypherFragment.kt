package com.example.ciphergenerator.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.ciphergenerator.R
import com.example.ciphergenerator.data.Cypher
import com.example.ciphergenerator.data.CypherViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class AddCypherFragment : Fragment() {

    private lateinit var cypherTitle : EditText
    private lateinit var cypherPassword : EditText
    private lateinit var cypherLength : EditText
    private lateinit var generateBTN : Button
    private lateinit var saveBTN : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add_cypher, container, false)

        //Linking views to variables
        cypherTitle = view.findViewById(R.id.passwordTitle)
        cypherPassword = view.findViewById(R.id.passwordOutput)
        cypherLength = view.findViewById(R.id.lengthInput)
        generateBTN = view.findViewById(R.id.generate)
        saveBTN = view.findViewById(R.id.save)
        val x = 8

        //Generate Password Button
        generateBTN.setOnClickListener{
            //Checking if cypherLength is not empty
            if (cypherLength.text.isNotEmpty()) {
                //Comparing the value input is greater than x
                minLengthValue(x, cypherLength.text.toString().toInt())
                cypherLength.text.clear()
            } else {
                Toast.makeText(requireContext(), "Minimum length is $x.", Toast.LENGTH_SHORT).show()
            }
        }

        //Saving cypher to database
        saveBTN.setOnClickListener {
            if (cypherTitle.text.isNotEmpty()) {
                cypherPassword.visibility = View.VISIBLE
                saveBTN.visibility = View.VISIBLE
                cypherLength.visibility = View.INVISIBLE
                insertDataToDatabase()
            } else {
                Toast.makeText(requireContext(), "Enter a title.", Toast.LENGTH_SHORT).show()
            }
        }

        //Floating Action Button
        //Navigate to the recycler view with list of passwords
        view.findViewById<FloatingActionButton>(R.id.listButton).setOnClickListener {
            findNavController().navigate(R.id.action_addCypherFragment_to_cypherListFragment)
        }

        return view
    }

    //Comparing the length to the "x" value
    private fun minLengthValue(x: Int, i: Int) {
        try {
            if (i < x) {
                Toast.makeText(requireContext(), "Minimum length is $x", Toast.LENGTH_SHORT).show()
                return
            }
        } catch (e: Exception) {
            println("Error: $e")
        }
        generateCypher(i.toString().toInt())
        cypherPassword.visibility = View.VISIBLE
    }

    //Generate the password once the enter length is valid
    //TODO : create options for password complexity
    private fun generateCypher(length : Int) {
        val specialChars = listOf('!', '@', '#', '$', '%', '^', '&', '*', '(', ')')
        val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9') + specialChars
        var password = ""

            for (i in 1..length) {
                val randomIndex = Random().nextInt(charPool.size)
                password += charPool[randomIndex]
            }
            //Log.i(TAG, "generatePassword: $password")
            cypherPassword.setText(password)
            cypherTitle.visibility = View.VISIBLE
            saveBTN.visibility = View.VISIBLE
            cypherLength.text.clear()
    }

    //Inserting title and password into Database
    private fun insertDataToDatabase() {
        val title = cypherTitle.text.toString()
        val password = cypherPassword.text.toString()

        val newCypher = Cypher(0, title, password)

        CypherViewModel(requireActivity().application).addCypher(newCypher)

        Toast.makeText(requireContext(), "Added Cypher to Database", Toast.LENGTH_SHORT).show()
        //Navigate to list view of passwords
        findNavController().navigate(R.id.action_addCypherFragment_to_cypherListFragment)
    }
}