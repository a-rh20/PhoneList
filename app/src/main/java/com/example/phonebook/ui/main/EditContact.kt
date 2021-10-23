package com.example.phonebook.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.phonebook.R
import com.example.phonebook.databinding.FragmentEditContactBinding

class EditContact:Fragment() {
    private lateinit var viewModel: ViewModelContactList

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentEditContactBinding>(inflater, R.layout.fragment_edit_contact, container, false )
        viewModel = ViewModelProvider(requireActivity()).get(ViewModelContactList::class.java)

        var args = EditContactArgs.fromBundle(requireArguments())
        binding.detailsEnterContactName.setText(args.name)
        binding.detailsEnterContactNumber.setText(args.number)
        binding.button.setOnClickListener {
            Toast.makeText(context, "this button works", Toast.LENGTH_LONG).show()
            viewModel.updateContactData(
                args.contactid.toInt()-1,
                binding.detailsEnterContactName.text.toString(),
                binding.detailsEnterContactNumber.text.toString()
            )
            view?.findNavController()?.navigate(EditContactDirections.actionEditContactToContactList())
        }
        return binding.root
    }
}