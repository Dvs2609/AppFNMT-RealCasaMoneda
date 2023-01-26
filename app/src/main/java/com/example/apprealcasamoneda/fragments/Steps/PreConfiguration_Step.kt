package com.example.apprealcasamoneda.fragments.Steps

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import com.example.apprealcasamoneda.R
import com.example.apprealcasamoneda.databinding.FrStepPreConfigurationBinding
import com.example.apprealcasamoneda.fragments.BaseFragment
import com.example.apprealcasamoneda.fragments.PageNotAvailable

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PreConfiguration_Step.newInstance] factory method to
 * create an instance of this fragment.
 */
class PreConfiguration_Step : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FrStepPreConfigurationBinding

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
        binding = FrStepPreConfigurationBinding.inflate(inflater, container, false)
        val transition = fragmentManager?.beginTransaction()

        binding.step1IconDropdown1?.setOnClickListener{
            showHide(binding.RLReminderDropdown, binding.step1IconDropdown1)
        }

        binding.step1IconDropdown2?.setOnClickListener{
            showHide(binding.RLBrowsersDropdown, binding.step1IconDropdown2)
        }

        binding.step1IconDropdown3?.setOnClickListener{
            showHide(binding.RLSoftwareNecesaryDropdown, binding.step1IconDropdown3)
        }


        val sharedPreferences = requireContext().getSharedPreferences("preference", Context.MODE_PRIVATE)
        val language = sharedPreferences.getString("language","en")

        val navValue = arguments?.getString("key")
        val navValue2 = arguments?.getString("key2")
        when {
            navValue == "representative" -> binding.txtNavStep1.text = resources.getString(R.string.oc_repre_step1_nav)
            navValue2 == "physical" -> binding.txtNavStep1.text = resources.getString(R.string.oc_pp_step1_nav)
            else -> binding.txtNavStep1.text = "Error"
        }

        binding.preConfigStepBackCross.setOnClickListener{
            val fragmentManager = requireFragmentManager()
            fragmentManager.popBackStack()
        }

        val notAvailablePage = View.OnClickListener {
            val pageNotAvailable = PageNotAvailable()
            val transition = fragmentManager?.beginTransaction()
            transition?.replace(R.id.mainContainer, pageNotAvailable)
            transition?.setReorderingAllowed(true)
            transition?.addToBackStack(null)
            transition?.commit()
        }
        binding.iconPreConfigGoToDeclaration.setOnClickListener(notAvailablePage)
        binding.iconPreConfigGoToDownloadArea.setOnClickListener(notAvailablePage)

        return binding.root
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Step1_PPC.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PreConfiguration_Step().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}