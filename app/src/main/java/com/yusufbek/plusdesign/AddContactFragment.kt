package com.yusufbek.plusdesign

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.yusufbek.plusdesign.databinding.FragmentAddContactBinding
import com.yusufbek.plusdesign.newChatFragment.Contact

class AddContactFragment : Fragment() {
    private lateinit var binding: FragmentAddContactBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddContactBinding.inflate(inflater, container, false)
        binding.topAppBarAddContactFragment.setNavigationOnClickListener {
            findNavController().popBackStack(R.id.addContactFragment, true)
        }
        binding.topAppBarAddContactFragment.setOnMenuItemClickListener {
            if (it.itemId == R.id.save_contact_add_contact_fragment) {
                if (binding.editTextFirstNameAddContactFragment.text.isNotEmpty() && binding.editTextNumberAddContactFragment.text.isNotEmpty()) {
                    val contact = Contact(
                        imgRes = R.drawable.ic_person,
                        name = binding.editTextFirstNameAddContactFragment.text.toString() + " " + binding.editTextLastNameAddContactFragment,
                        number = binding.editTextNumberAddContactFragment.text.toString()
                    )
                    findNavController().popBackStack(R.id.addContactFragment, true)
                } else if (binding.editTextFirstNameAddContactFragment.text.isNotEmpty() && binding.editTextNumberAddContactFragment.text.isEmpty()) {
                    val anim =
                        AnimationUtils.loadAnimation(requireContext(), R.anim.shake)
                    binding.editTextNumberAddContactFragment.startAnimation(anim)
                    vibrate()
                } else if (binding.editTextFirstNameAddContactFragment.text.isEmpty()) {
                    val anim =
                        AnimationUtils.loadAnimation(requireContext(), R.anim.shake)
                    binding.editTextFirstNameAddContactFragment.startAnimation(anim)
                    vibrate()
                }
            }
            false
        }

        return binding.root
    }

    private fun vibrate() {
        val duration = 500L
        if (Build.VERSION.SDK_INT >= 26) {
            (requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator).vibrate(
                VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE)
            )
        } else {
            (requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator).vibrate(
                duration
            )
        }
    }
}