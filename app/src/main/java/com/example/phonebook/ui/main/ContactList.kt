package com.example.phonebook.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.phonebook.ContactData
import com.example.phonebook.R
import com.example.phonebook.databinding.FragmentContactListBinding

class ContactList : Fragment() {

//    companion object {
//        fun newInstance() = ContactList()
//    }
    var lid = 0 //new
    private lateinit var viewModel: ViewModelContactList

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        var id: String //why not lateinit variable?
        var name: String
        var number: String

        val binding = DataBindingUtil.inflate<FragmentContactListBinding>(inflater, R.layout.fragment_contact_list, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(ViewModelContactList::class.java)
        binding.saveContactInfoButton.setOnClickListener {
            Toast.makeText(context, "this button works", Toast.LENGTH_LONG).show()
            viewModel.insert(ContactData(//new through35. check and implement
                lid++,
                binding.enterContactName.text.toString(),
                binding.enterContactNumber.text.toString()
            ))
            /**
             * This next block of code assigns variables to arguments that are passed from fragment_contact_list to fragment_edit_contact.
             */
            binding.idlist.text = "${lid-1}"
            id = binding.idlist.text.toString()
            name = binding.enterContactName.text.toString()
            number = binding.enterContactNumber.text.toString()
            /**
             * The following lines of code trigger the visibility and populates the text boxes. Will comment these out for now. Might togglne in next fragment or get rid off
             * with recycler adapter.
             */
//            binding.contactName1.text = binding.enterContactName.text.toString()
//            binding.contactNumber1.text = binding.enterContactNumber.text.toString()
//            binding.contactNumber1.visibility = View.VISIBLE
//            binding.contactName1.visibility = View.VISIBLE

//            view?.findNavController()?.navigate(ContactListDirections.actionContactListToEditContact()) //works but need to comment out for now
            view?.findNavController()?.navigate(ContactListDirections.actionContactListToEditContact(name, number, id))
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ViewModelContactList::class.java)
        // TODO: Use the ViewModel
    }

}