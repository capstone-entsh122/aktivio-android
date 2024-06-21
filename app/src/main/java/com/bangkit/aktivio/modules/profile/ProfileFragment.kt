package com.bangkit.aktivio.modules.profile

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.Profile
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bangkit.aktivio.R
import androidx.fragment.app.viewModels
import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.utils.Extensions.toast
import com.bangkit.aktivio.core.utils.Firebase
import com.bangkit.aktivio.databinding.FragmentProfileBinding
import com.bangkit.aktivio.modules.ComingSoonActivity
import com.bangkit.aktivio.modules.auth.LoginActivity
import com.bangkit.aktivio.modules.survey.SurveyActivity
import dagger.hilt.android.AndroidEntryPoint
import www.sanju.motiontoast.MotionToastStyle

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModels: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            btnLogout.setOnClickListener {
                Firebase.auth.signOut()
                viewModels.logout()
                startActivity(Intent(requireContext(), LoginActivity::class.java))
                requireActivity().finish()
            }
            btnSurvey.setOnClickListener {
                startActivity(Intent(requireContext(), SurveyActivity::class.java))
            }
            btnSettings.setOnClickListener {
                startActivity(Intent(requireContext(), ComingSoonActivity::class.java))
            }
            btnAchievement.setOnClickListener {
                startActivity(Intent(requireContext(), ComingSoonActivity::class.java))
            }
            viewModels.getProfileData().observe(viewLifecycleOwner) {
                when(it) {
                    is Resource.Error -> {
                        activity?.toast("Warning", it.message.toString(), MotionToastStyle.WARNING)
                    }
                    is Resource.Loading -> TODO()
                    is Resource.Success -> {
                        it.apply {
                            username.text = data?.displayName
                            email.text = data?.email
                            points.text = "${data?.points} Points"
                            level.text = "Level 1"
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
