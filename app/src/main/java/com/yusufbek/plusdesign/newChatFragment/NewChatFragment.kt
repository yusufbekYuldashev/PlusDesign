package com.yusufbek.plusdesign.newChatFragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.SearchView
import androidx.activity.OnBackPressedCallback
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.yusufbek.plusdesign.R
import com.yusufbek.plusdesign.databinding.FragmentNewChatBinding

class NewChatFragment : Fragment() {
    private lateinit var binding: FragmentNewChatBinding
    private var contactArray = arrayListOf<Contact>()
    private var isSearching = false
    private var searchForText = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewChatBinding.inflate(inflater)

        binding.topAppBarNewChatFragment.setNavigationOnClickListener {
            if (isSearching) {
                binding.topAppBarNewChatFragment.title = "New Message"
                binding.topAppBarNewChatFragment.menu.findItem(R.id.searchNewChatFragment).isVisible =
                    true
                binding.topAppBarNewChatFragment.menu.findItem(R.id.sortContactNewChatFragment).isVisible =
                    true
                isSearching = false
                val imm =
                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                val view = requireActivity().currentFocus
                imm.hideSoftInputFromWindow(
                    view!!.windowToken,
                    0
                )
                binding.topAppBarNewChatFragment.findViewById<TextInputLayout>(R.id.searchEditTextLayout).visibility =
                    View.GONE
            } else {
                findNavController().popBackStack(R.id.newChatFragment, true)
            }
        }

        binding.topAppBarNewChatFragment.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.searchNewChatFragment -> {
                    isSearching = true
                    binding.topAppBarNewChatFragment.title = ""
                    binding.topAppBarNewChatFragment.menu.findItem(R.id.searchNewChatFragment).isVisible =
                        false
                    binding.topAppBarNewChatFragment.menu.findItem(R.id.sortContactNewChatFragment).isVisible =
                        false
                    binding.topAppBarNewChatFragment.findViewById<TextInputLayout>(R.id.searchEditTextLayout).visibility =
                        View.VISIBLE
                    binding.topAppBarNewChatFragment.findViewById<TextInputLayout>(R.id.searchEditTextLayout)
                        .requestFocus()
                    val imm =
                        requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.showSoftInput(
                        binding.topAppBarNewChatFragment.findViewById<EditText>(R.id.searchEditText),
                        InputMethodManager.SHOW_IMPLICIT
                    )
                }
                R.id.addAccountNewChatFragment -> {
                    Navigation.findNavController(binding.root)
                        .navigate(R.id.action_newChatFragment_to_addContactFragment)
                }
                R.id.sortContactNewChatFragment -> {

                }
            }
            false
        }

        createContacts()
        val adapter = ContactsRecyclerAdapter(requireContext())
        adapter.setData(contactArray)
        binding.recyclerViewNewChatFragment.adapter = adapter

        binding.topAppBarNewChatFragment.findViewById<EditText>(R.id.searchEditText)
            .addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val tempArray: ArrayList<Contact> = arrayListOf()
                    contactArray.forEach {
                        if (it.name.contains(s.toString())) {
                            tempArray.add(it)
                        }
                    }
                    adapter.setData(tempArray)
                }

                override fun afterTextChanged(s: Editable?) {

                }

            })

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (isSearching) {
                        binding.topAppBarNewChatFragment.title = "New Message"
                        binding.topAppBarNewChatFragment.menu.findItem(R.id.searchNewChatFragment).isVisible =
                            true
                        binding.topAppBarNewChatFragment.menu.findItem(R.id.sortContactNewChatFragment).isVisible =
                            true
                        binding.topAppBarNewChatFragment.findViewById<TextInputLayout>(R.id.searchEditTextLayout).visibility =
                            View.GONE
                        isSearching = false
                    } else {
                        findNavController().popBackStack(R.id.newChatFragment, true)
                    }
                }

            })

        binding.fabCreateNewContactNewChatFragment.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_newChatFragment_to_addContactFragment)
        }

        return binding.root
    }

    private fun createContacts() {
        for (i in 'a'..'z') {
            val contact =
                Contact("$i John Wick", R.drawable.contact_img, "$i$i$i$i$i", "last seen recently")
            contactArray.add(contact)
        }
    }
}