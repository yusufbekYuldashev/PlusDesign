package com.yusufbek.plusdesign.mainFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.yusufbek.plusdesign.R
import com.yusufbek.plusdesign.databinding.FragmentMainPageBinding

class MainPageFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: FragmentMainPageBinding

    companion object {
        private var chatArray = arrayListOf<Chat>()
        private var groupArray = arrayListOf<Chat>()
        private var superGroupArray = arrayListOf<Chat>()
        private var channelArray = arrayListOf<Chat>()
        private var botArray = arrayListOf<Chat>()
        private val CATEGORY_LIST = listOf("Users", "Groups", "Supergroups", "Channels", "Bots")
        private val CATEGORY_ICON_LIST = listOf(
            R.drawable.ic_person,
            R.drawable.ic_group,
            R.drawable.ic_super_group,
            R.drawable.ic_campaign,
            R.drawable.ic_robot
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainPageBinding.inflate(inflater)
        createChat()
        createGroup()
        createSuperGroup()
        createChannel()
        createBot()

        val drawerToggle = ActionBarDrawerToggle(
            activity,
            binding.drawerLayout,
            binding.topAppBar,
            R.string.open,
            R.string.close
        )
        binding.navView.setNavigationItemSelectedListener(this)
        binding.drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        val headerView = binding.navView.getHeaderView(0)

        headerView.findViewById<ConstraintLayout>(R.id.layout_to_open_all_accounts)
            .setOnClickListener {
                val allAccountLayout = headerView.findViewById<ConstraintLayout>(R.id.all_accounts)
                if (allAccountLayout.visibility == View.GONE) {
                    allAccountLayout.visibility = View.VISIBLE

                    headerView.findViewById<ImageView>(R.id.imageViewOpenAccounts)
                        .setImageResource(R.drawable.ic_keyboard_arrow_up)
                } else {
                    allAccountLayout.visibility = View.GONE
                    headerView.findViewById<ImageView>(R.id.imageViewOpenAccounts)
                        .setImageResource(R.drawable.ic_keyboard_arrow_down)
                }
            }

        binding.pager.adapter = ViewPagerAdapter(requireActivity())
        binding.topAppBar.title = CATEGORY_LIST[0]
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.icon = resources.getDrawable(CATEGORY_ICON_LIST[position])
        }.attach()

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.topAppBar.title = CATEGORY_LIST[tab!!.position]
                if (tab.position == 1 || tab.position == 4) {
                    binding.topAppBar.subtitle = "Sort by unread messages"
                } else {
                    binding.topAppBar.subtitle = ""
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        binding.fabStartChat.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_mainPageFragment_to_newChatFragment)
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        binding.drawerLayout.closeDrawer(GravityCompat.START)
                    }
                }

            })

        return binding.root
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        when (item.itemId) {
            R.id.new_group -> {
                Toast.makeText(context, "You are going to create new group", Toast.LENGTH_SHORT)
                    .show()
            }
            R.id.new_secret_chat -> {

            }
            R.id.new_channel -> {

            }
            R.id.contacts -> {

            }
            R.id.folders -> {

            }
            R.id.people_nearby -> {

            }
            R.id.saved_messages -> {

            }
            R.id.archived_chats -> {

            }
            R.id.calls -> {

            }
            R.id.settings -> {

            }
            R.id.plus_settings -> {

            }
            R.id.categories -> {

            }
            R.id.download_themes -> {

            }
            R.id.theming -> {

            }
            R.id.support_group -> {

            }
            R.id.chats_counters -> {

            }
        }
        return false
    }

    class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int {
            return CATEGORY_LIST.size
        }

        override fun createFragment(position: Int): Fragment {
            when (position) {
                0 -> {
                    return PeopleFragment(chatArray)
                }
                1 -> {
                    return PeopleFragment(groupArray)
                }
                2 -> {
                    return PeopleFragment(superGroupArray)
                }
                3 -> {
                    return PeopleFragment(channelArray)
                }
                4 -> {
                    return PeopleFragment(botArray)
                }
                else -> {
                    return PeopleFragment(chatArray)
                }
            }
        }
    }

    private fun createChat() {
        var chat: Chat?
        for (i in (1..20)) {

            when {
                i % 3 == 0 -> {
                    chat = Chat(
                        R.drawable.download,
                        "Saved messages $i",
                        "Hi bro $i! How are you?",
                        "Fri",
                        true,
                        LastMessageStatus.NOT_SENT
                    )
                }
                i % 3 == 1 -> {
                    chat = Chat(
                        R.drawable.download,
                        "Saved messages $i",
                        "Hi bro $i! How are you?",
                        "Fri",
                        false,
                        LastMessageStatus.SENT
                    )
                }
                else -> {
                    chat = Chat(
                        R.drawable.download,
                        "Saved messages $i",
                        "Hi bro $i! How are you?",
                        "Fri",
                        true,
                        LastMessageStatus.READ
                    )
                }
            }

            chatArray.add(chat)
        }
    }

    private fun createGroup() {
        var chat: Chat?
        for (i in (1..20)) {

            when {
                i % 3 == 0 -> {
                    chat = Chat(
                        R.drawable.download,
                        "Group $i",
                        "Group $i! is for us",
                        "Fri",
                        true,
                        LastMessageStatus.NOT_SENT
                    )
                }
                i % 3 == 1 -> {
                    chat = Chat(
                        R.drawable.download,
                        "Group $i",
                        "Group $i! How are you?",
                        "Tue",
                        false,
                        LastMessageStatus.SENT
                    )
                }
                else -> {
                    chat = Chat(
                        R.drawable.download,
                        "Group $i",
                        "Group $i! How are you now?",
                        "Fri",
                        true,
                        LastMessageStatus.READ
                    )
                }
            }

            groupArray.add(chat)
        }
    }

    private fun createSuperGroup() {
        var chat: Chat?
        for (i in (1..20)) {

            when {
                i % 3 == 0 -> {
                    chat = Chat(
                        R.drawable.download,
                        "SuperGroup $i",
                        "SuperGroup $i! is for us",
                        "Fri",
                        true,
                        LastMessageStatus.NOT_SENT
                    )
                }
                i % 3 == 1 -> {
                    chat = Chat(
                        R.drawable.download,
                        "SuperGroup $i",
                        "SuperGroup $i! How are you?",
                        "Tue",
                        false,
                        LastMessageStatus.SENT
                    )
                }
                else -> {
                    chat = Chat(
                        R.drawable.download,
                        "SuperGroup $i",
                        "SuperGroup $i! How are you now?",
                        "Fri",
                        true,
                        LastMessageStatus.READ
                    )
                }
            }

            superGroupArray.add(chat)
        }
    }

    private fun createChannel() {
        var chat: Chat?
        for (i in (1..20)) {

            when {
                i % 3 == 0 -> {
                    chat = Chat(
                        R.drawable.download,
                        "Channel $i",
                        "Channel $i! is for us",
                        "Fri",
                        true,
                        LastMessageStatus.NOT_SENT
                    )
                }
                i % 3 == 1 -> {
                    chat = Chat(
                        R.drawable.download,
                        "Channel $i",
                        "Channel $i! How are you?",
                        "Tue",
                        false,
                        LastMessageStatus.SENT
                    )
                }
                else -> {
                    chat = Chat(
                        R.drawable.download,
                        "Channel $i",
                        "Channel $i! How are you now?",
                        "Fri",
                        true,
                        LastMessageStatus.READ
                    )
                }
            }

            channelArray.add(chat)
        }
    }

    private fun createBot() {
        var chat: Chat?
        for (i in (1..20)) {

            when {
                i % 3 == 0 -> {
                    chat = Chat(
                        R.drawable.download,
                        "Bot $i",
                        "Bot $i! is for us",
                        "Fri",
                        true,
                        LastMessageStatus.NOT_SENT
                    )
                }
                i % 3 == 1 -> {
                    chat = Chat(
                        R.drawable.download,
                        "Bot $i",
                        "Bot $i! How are you?",
                        "Tue",
                        false,
                        LastMessageStatus.SENT
                    )
                }
                else -> {
                    chat = Chat(
                        R.drawable.download,
                        "Bot $i",
                        "Bot $i! How are you now?",
                        "Fri",
                        true,
                        LastMessageStatus.READ
                    )
                }
            }

            botArray.add(chat)
        }
    }
}