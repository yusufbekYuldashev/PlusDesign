package com.yusufbek.plusdesign.mainFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yusufbek.plusdesign.R
import com.yusufbek.plusdesign.databinding.ChatItemBinding

class ChatsRecyclerAdapter(var chatArray: ArrayList<Chat>) :
    RecyclerView.Adapter<ChatsRecyclerAdapter.ChatHolder>() {
    private lateinit var binding: ChatItemBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHolder {
        binding = ChatItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatHolder, position: Int) {
        holder.bind(chatArray[position])
    }

    override fun getItemCount(): Int {
        if (chatArray.isNotEmpty()) {
            return chatArray.size
        }
        return 0
    }

    class ChatHolder(var binding: ChatItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(chat: Chat) {
            binding.chatItemImg.setBackgroundResource(chat.imgSrc)
            binding.chatItemName.text = chat.name
            binding.chatItemLastMsg.text = chat.lastMsg
            binding.chatItemDate.text = chat.date
            if (chat.isPin) {
                binding.chatItemIsPin.visibility = View.VISIBLE
            }else{
                binding.chatItemIsPin.visibility = View.INVISIBLE
            }
            when (chat.lastMsgStatus) {
                LastMessageStatus.NOT_SENT -> {
                    binding.chatItemIsRead.visibility = View.VISIBLE
                }
                LastMessageStatus.SENT -> {
                    binding.chatItemIsRead.setImageResource(R.drawable.ic_sent)
                }
                LastMessageStatus.READ -> {
                    binding.chatItemIsRead.setImageResource(R.drawable.ic_read)
                }
            }
        }
    }
}