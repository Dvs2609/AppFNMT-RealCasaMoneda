package com.example.apprealcasamoneda.fragments.PhysicalPersonCertificates

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
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.fragment.findNavController
import com.example.apprealcasamoneda.R
import com.example.apprealcasamoneda.databinding.FrOcPhysicalPersonBinding
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
class PhysicalPerson : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var navController: NavController
    private lateinit var binding: FrOcPhysicalPersonBinding

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
            val step1Ppc = Step1_PPC()
            val bundle = Bundle()
            bundle.putString("key2", "physical")
            step1Ppc.arguments = bundle
            transition?.replace(R.id.mainContainer, step1Ppc)
            transition?.addToBackStack(null)
            transition?.commit()

        }

        binding.GoToStep2.setOnClickListener{
            val step2Ppc = Step2_PPC()
            val bundle = Bundle()
            bundle.putString("key2", "physical")
            step2Ppc.arguments = bundle
            transition?.replace(R.id.mainContainer, step2Ppc)
            transition?.addToBackStack(null)
            transition?.commit()
        }

        binding.GoToStep3.setOnClickListener{
            val step3Ppc = Step3_PPC()
            val bundle = Bundle()
            bundle.putString("key2", "physical")
            step3Ppc.arguments = bundle
            transition?.replace(R.id.mainContainer, step3Ppc)
            transition?.addToBackStack(null)
            transition?.commit()
        }

        binding.GoToStep4.setOnClickListener{
            val step4Ppc = Step4_PPC()
            val bundle = Bundle()
            bundle.putString("key2", "physical")
            step4Ppc.arguments = bundle
            transition?.replace(R.id.mainContainer, step4Ppc)
            transition?.addToBackStack(null)
            transition?.commit()
        }


        binding.iconQst1Dropdown?.setOnClickListener{
            showHide(binding.RLQst1Dropdown, binding.iconQst1Dropdown)
        }

        binding.iconQs2Dropdown?.setOnClickListener{
            showHide(binding.RLQs2Dropdown, binding.iconQs2Dropdown)
        }

        binding.iconQst3Dropdown?.setOnClickListener{
            showHide(binding.RLQst3Dropdown, binding.iconQst3Dropdown)
        }

        binding.iconQs4Dropdown?.setOnClickListener{
            showHide(binding.RLQs4Dropdown, binding.iconQs4Dropdown)
        }

        binding.ocPpCross.setOnClickListener{
            navController.navigateUp()
        }
        return binding.root


    }


    private fun showHide(relativeLayout: RelativeLayout?, imageView: ImageView) {
        val fadeInAnimation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in)
        //val fadeOutAnimation = AnimationUtils.loadAnimation(context, android.R.anim.fade_out)

        if (relativeLayout != null) {
            if (relativeLayout.visibility == View.GONE ){

                relativeLayout.startAnimation(fadeInAnimation)
                relativeLayout.visibility = View.VISIBLE
                imageView.rotation = 90f


            }else{

                //textView.startAnimation(fadeInAnimation)
                relativeLayout.visibility = View.GONE
                imageView.rotation = 0f
            }
        }
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