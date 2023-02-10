package com.example.apprealcasamoneda.fragments.RepresentativeCertificates

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import com.example.apprealcasamoneda.R
import com.example.apprealcasamoneda.databinding.FragmentRepresentativeBinding
import com.example.apprealcasamoneda.fragments.BaseFragment
import com.example.apprealcasamoneda.fragments.PageNotAvailable
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Representative.newInstance] factory method to
 * create an instance of this fragment.
 */
class Representative : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentRepresentativeBinding

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
        binding = FragmentRepresentativeBinding.inflate(inflater, container, false)
        val transition = fragmentManager?.beginTransaction()

        val sharedPreferences = requireContext().getSharedPreferences("preference", Context.MODE_PRIVATE)
        val language = sharedPreferences.getString("language","en")
        if(language == "en"){
            binding.ocRepreNav.text =  resources.getString(R.string.txt_oc_repre_nav)
        }else{
            val conf = Configuration(resources.configuration)
            conf.locale = Locale("es")
            val resources = requireContext().createConfigurationContext(conf).resources
            binding.ocRepreNav.text =  resources.getString(R.string.txt_oc_repre_nav)
        }


        binding.repreIconDropdown1.setOnClickListener{
            showHide(binding.RLReminderDropdown, binding.repreIconDropdown1)
        }
        binding.iconRepreGoToReqUniq.setOnClickListener{
            val singleAdministrator = RC_Single_Administrator()
            transition?.replace(R.id.mainContainer, singleAdministrator)
            transition?.setReorderingAllowed(true)
            transition?.addToBackStack(null)
            transition?.commit()
        }
        binding.repreIconDropdown2.setOnClickListener{
            showHide(binding.RLJuridicDropdown, binding.repreIconDropdown2)
        }
        binding.repreIconDropdown3.setOnClickListener{
            showHide(binding.RLSoftwareNecesaryDropdown, binding.repreIconDropdown3)
        }

        binding.ocRepreBackCross.setOnClickListener{
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
        binding.iconGoToOtherOptions.setOnClickListener(notAvailablePage)
        binding.iconGoToLegalEntity.setOnClickListener(notAvailablePage)
        binding.iconGoToNonLegalEntity.setOnClickListener(notAvailablePage)
        binding.iconRepreGoToJuridic.setOnClickListener(notAvailablePage)
        binding.iconStep1GoToDownloadArea.setOnClickListener(notAvailablePage)
        binding.iconGoToHelp.setOnClickListener(notAvailablePage)


        binding.iconGoToSingleAdmin.setOnClickListener{
            val singleAdministrator = RC_Single_Administrator()
            transition?.replace(R.id.mainContainer, singleAdministrator)
            transition?.setReorderingAllowed(true)
            transition?.addToBackStack(null)
            transition?.commit()
        }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Representative.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Representative().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}