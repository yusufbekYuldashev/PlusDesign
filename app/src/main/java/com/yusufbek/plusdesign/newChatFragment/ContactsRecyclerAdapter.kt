package com.yusufbek.plusdesign.newChatFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yusufbek.plusdesign.databinding.ItemNewChatRecyclerBinding

class ContactsRecyclerAdapter(var context: Context) :
    RecyclerView.Adapter<ContactsRecyclerAdapter.ContactHolder>() {
    private lateinit var binding: ItemNewChatRecyclerBinding
    private var set: MutableSet<String> = mutableSetOf()
    private lateinit var contactArray:ArrayList<Contact>

    fun setData(array:ArrayList<Contact>){
        set.clear()
        contactArray = array
        notifyDataSetChanged()
    }

    fun addContact(contact: Contact){
        contactArray.add(contact)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        binding = ItemNewChatRecyclerBinding.inflate(LayoutInflater.from(context), parent, false)
        return ContactHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        holder.bind(contactArray[position])
    }

    override fun getItemCount(): Int = contactArray.size

    inner class ContactHolder(var binding: ItemNewChatRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: Contact) {
            val char = contact.name[0].toString()
            if (!set.contains(char)) {
                set.add(char)
                binding.itemFirstLetterNewChatFragment.text = contact.name[0].toUpperCase().toString()
            } else {
                binding.itemFirstLetterNewChatFragment.visibility = View.INVISIBLE
            }
            binding.itemNameNewChatFragment.text = contact.name
            binding.itemLastSeenNewChatFragment.text = contact.lastSeen
            binding.itemImgNewChatFragment.setImageResource(contact.imgRes)
        }
    }
}