package com.example.phonebook.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.phonebook.ContactData

class ViewModelContactList : ViewModel() {
    /**
     * This is the data structure where contacts will be stored
     */
    var contactDataList = mutableListOf<ContactData>()

    //LiveData has no public available methods to update stored data. So we use MutableLiveData to update data and expose immutable LiveData objects
    /**
     * LiveData has no public available methods to update stored data. So we use MutableLiveData to update data and expose immutable LiveData objects
     * to observers. Using LiveData helps prevent accidental data modification. MutableLiveData is used for intentional data modification.
     */
    var _contactData = MutableLiveData<MutableList<ContactData>>().apply {
        postValue(contactDataList)
    }
    val contactData: LiveData<MutableList<ContactData>>
        get() = _contactData

    /**
     * I think will observe a specific ContactData to select correct entry to update. Will be used in textView
     */
    private val _navigateToSelectedItem = MutableLiveData<ContactData?>()
    val navigateToSelectedItem : LiveData<ContactData?>
        get() = _navigateToSelectedItem

    /**
     * function to update contact data
     */
    fun updateContactData(position: Int, name: String, number: String){
        contactDataList[position].name = name
        contactDataList[position].phoneNumber = number
        _contactData.value = contactDataList
    }
    /**
     * add new contact to ContactList
     */
    fun insert(contact:ContactData){
        contactDataList.add(contact)
        _contactData.value = contactDataList
    }

    /**
     * I think these two work in conjunction to navigate to a specific item then reset selection. Will uncomment when I understand more
     */
//    fun displayPropertyDetails(contactItem: ContactData){
//        _navigateToSelectedItem.value = contactItem
//    }
//    fun displayPropertyDetailsComplete(){
//        _navigateToSelectedItem.value = null
//    }
}