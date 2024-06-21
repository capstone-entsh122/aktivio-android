package com.bangkit.aktivio.modules.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.aktivio.R
import com.bangkit.aktivio.config.DummyArticles
import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.local.adapter.ArticleAdapter
import com.bangkit.aktivio.core.domain.model.ArticleModel
import com.bangkit.aktivio.core.utils.Firebase
import com.bangkit.aktivio.databinding.FragmentHomeBinding
import com.bangkit.aktivio.databinding.FragmentProfileBinding
import com.bangkit.aktivio.modules.auth.LoginActivity
import com.bangkit.aktivio.modules.profile.ProfileViewModel
import com.bangkit.aktivio.modules.survey.SurveyActivity
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var articles: ArrayList<ArticleModel> = arrayListOf()
    private val adapter: ArticleAdapter by lazy {
        ArticleAdapter(articles)
    }
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModels: HomeViewModel by viewModels()

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
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.addData(DummyArticles.getDummyArticles())
        showRecyclerList()
        with(binding){
            viewModels.getProfileData().observe(requireActivity()){
                when(it){
                    is Resource.Success -> {
                        val user = it.data
                        tvHello.text = "Hello, ${user?.displayName}"
                    }
                    is Resource.Error -> {
                        Log.e("MainActivity", it.message.toString())
                    }
                    else -> {}
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun showRecyclerList() {
        with(binding){
            val layoutManager = LinearLayoutManager(context)
            rvArticles.layoutManager = layoutManager
            rvArticles.adapter = adapter
            adapter.setOnItemCLickCallback(object : ArticleAdapter.OnItemClickCallback {
                override fun onItemClicked(data: ArticleModel) {
                    val intent = Intent(context, ArticleActivity::class.java)
                    intent.putExtras(Bundle().apply {
                        putParcelable("article", data)
                    })
                    startActivity(intent)
                }
            })
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}