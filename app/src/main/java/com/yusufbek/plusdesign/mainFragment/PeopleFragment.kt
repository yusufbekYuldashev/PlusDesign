package com.yusufbek.plusdesign.mainFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yusufbek.plusdesign.databinding.FragmentPeopleBinding
import com.yusufbek.plusdesign.mainFragment.Chat
import com.yusufbek.plusdesign.mainFragment.ChatsRecyclerAdapter

class PeopleFragment(var chatArray:ArrayList<Chat>) : Fragment() {
    private lateinit var binding: FragmentPeopleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPeopleBinding.inflate(inflater, container, false)
        binding.chatsRecyclerView.adapter = ChatsRecyclerAdapter(chatArray)
        return binding.root
    }
}