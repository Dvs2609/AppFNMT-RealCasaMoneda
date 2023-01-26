package com.example.apprealcasamoneda.fragments.PhysicalPersonCertificates

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.addCallback
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.apprealcasamoneda.R
import com.example.apprealcasamoneda.databinding.FrOcPhysicalPersonBinding
import com.example.apprealcasamoneda.fragments.BaseFragment
import com.example.apprealcasamoneda.fragments.PageNotAvailable
import com.example.apprealcasamoneda.fragments.Steps.DownloadCertificate_Step
import com.example.apprealcasamoneda.fragments.Steps.PreConfiguration_Step
import com.example.apprealcasamoneda.fragments.Steps.ProveIdentity_Step
import com.example.apprealcasamoneda.fragments.Steps.RequestCertificate_Step
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PhysicalPerson.newInstance] factory method to
 * create an instance of this fragment.
 */
class PhysicalPerson : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var navController: NavController
    private lateinit var binding: FrOcPhysicalPersonBinding

    private var qst1Open = false
    private var qst2Open = false
    private var qst3Open = false
    private var qst4Open = false

    private lateinit var callback: OnBackPressedCallback

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
        binding = FrOcPhysicalPersonBinding.inflate(inflater, container, false)
        val transition = fragmentManager?.beginTransaction()


        val sharedPreferences = requireContext().getSharedPreferences("preference",Context.MODE_PRIVATE)
        val language = sharedPreferences.getString("language","en")
        if(language == "en"){
            binding.navOcPp.text =  resources.getString(R.string.oc_pp_nav)
        }else{
            val conf = Configuration(resources.configuration)
            conf.locale = Locale("es")
            val resources = requireContext().createConfigurationContext(conf).resources
            binding.navOcPp.text =  resources.getString(R.string.oc_pp_nav)
        }



        binding.GoToStep1.setOnClickListener{
            val preconfigurationStep = PreConfiguration_Step()
            val bundle = Bundle()
            bundle.putString("key2", "physical")
            preconfigurationStep.arguments = bundle
            transition?.replace(R.id.mainContainer, preconfigurationStep)
            transition?.setReorderingAllowed(true)
            transition?.addToBackStack(null)
            transition?.commit()

        }

        binding.GoToStep2.setOnClickListener{
            val requestcertificateStep = RequestCertificate_Step()
            val bundle = Bundle()
            bundle.putString("key2", "physical")
            requestcertificateStep.arguments = bundle
            transition?.replace(R.id.mainContainer, requestcertificateStep)
            transition?.setReorderingAllowed(true)
            transition?.addToBackStack(null)
            transition?.commit()
        }

        binding.GoToStep3.setOnClickListener{
            val proveidentityStep = ProveIdentity_Step()
            val bundle = Bundle()
            bundle.putString("key2", "physical")
            proveidentityStep.arguments = bundle
            transition?.replace(R.id.mainContainer, proveidentityStep)
            transition?.setReorderingAllowed(true)
            transition?.addToBackStack(null)
            transition?.commit()
        }

        binding.GoToStep4.setOnClickListener{
            val downloadcertificateStep = DownloadCertificate_Step()
            val bundle = Bundle()
            bundle.putString("key2", "physical")
            downloadcertificateStep.arguments = bundle
            transition?.replace(R.id.mainContainer, downloadcertificateStep)
            transition?.setReorderingAllowed(true)
            transition?.addToBackStack(null)
            transition?.commit()
        }



        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            if (qst1Open) showHide(binding.RLQst1Dropdown, binding.iconQst1Dropdown)
            if (qst2Open) showHide(binding.RLQs2Dropdown, binding.iconQs2Dropdown)
            if (qst3Open) showHide(binding.RLQst3Dropdown, binding.iconQst3Dropdown)
            if (qst4Open) showHide(binding.RLQs4Dropdown, binding.iconQs4Dropdown)
        }

        binding.iconQst1Dropdown?.setOnClickListener{
            showHide(binding.RLQst1Dropdown, binding.iconQst1Dropdown)
            qst1Open = !qst1Open
        }


        binding.iconQs2Dropdown?.setOnClickListener{
            showHide(binding.RLQs2Dropdown, binding.iconQs2Dropdown)
            qst2Open = !qst2Open
        }

        binding.iconQst3Dropdown?.setOnClickListener{
            showHide(binding.RLQst3Dropdown, binding.iconQst3Dropdown)
            qst3Open = !qst3Open
        }

        binding.iconQs4Dropdown?.setOnClickListener{
            showHide(binding.RLQs4Dropdown, binding.iconQs4Dropdown)
            qst3Open = !qst3Open
        }

        binding.ocPpCrossBack.setOnClickListener{
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
        binding.iconRepresentateGoToDigitalCertificates.setOnClickListener(notAvailablePage)
        binding.iconRepresentateGoToServices.setOnClickListener(notAvailablePage)

        return binding.root

    }

    override fun onDestroy() {
        super.onDestroy()
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PhysicalPerson.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PhysicalPerson().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}