package com.example.apprealcasamoneda.fragments.RepresentativeCertificates

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import com.example.apprealcasamoneda.R
import com.example.apprealcasamoneda.databinding.FrRcSingleAdministratorBinding
import com.example.apprealcasamoneda.fragments.BaseFragment
import com.example.apprealcasamoneda.fragments.PageNotAvailable
import com.example.apprealcasamoneda.fragments.Steps.DownloadCertificate_Step
import com.example.apprealcasamoneda.fragments.Steps.PreConfiguration_Step
import com.example.apprealcasamoneda.fragments.Steps.RequestCertificate_Step

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RC_Single_Administrator.newInstance] factory method to
 * create an instance of this fragment.
 */
class RC_Single_Administrator : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FrRcSingleAdministratorBinding

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
        binding = FrRcSingleAdministratorBinding.inflate(inflater, container, false)
        val transition = fragmentManager?.beginTransaction()
        //val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment
        //navController = navHostFragment.navController

        binding.GoToStep1SA.setOnClickListener{
            val preconfigurationStep = PreConfiguration_Step()
            val bundle = Bundle()
            bundle.putString("key", "representative")
            preconfigurationStep.arguments = bundle
            transition?.replace(R.id.mainContainer, preconfigurationStep)
            transition?.setReorderingAllowed(true)
            transition?.addToBackStack(null)
            transition?.commit()
        }

        binding.GoToStep2SA.setOnClickListener{
            val requestcertificateStep = RequestCertificate_Step()
            val bundle = Bundle()
            bundle.putString("key", "representative")
            requestcertificateStep.arguments = bundle
            transition?.replace(R.id.mainContainer, requestcertificateStep)
            transition?.setReorderingAllowed(true)
            transition?.addToBackStack(null)
            transition?.commit()
        }

        binding.GoToStep3SA.setOnClickListener{
            val downloadcertificateStep = DownloadCertificate_Step()
            val bundle = Bundle()
            bundle.putString("key", "representative")
            downloadcertificateStep.arguments = bundle
            transition?.replace(R.id.mainContainer, downloadcertificateStep)
            transition?.setReorderingAllowed(true)
            transition?.addToBackStack(null)
            transition?.commit()
        }

        binding.iconSADropdown.setOnClickListener{
            showHide(binding.RLSAdropdown1, binding.iconSADropdown)
        }
        binding.iconSADropdown2.setOnClickListener{
            showHide(binding.RLSAdropdown2, binding.iconSADropdown2)
        }

        binding.ocRepreSingleAdminBackCross.setOnClickListener{
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
        binding.iconRepresentateGoToHelpRepre.setOnClickListener(notAvailablePage)

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RC_Single_Administrator.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RC_Single_Administrator().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}