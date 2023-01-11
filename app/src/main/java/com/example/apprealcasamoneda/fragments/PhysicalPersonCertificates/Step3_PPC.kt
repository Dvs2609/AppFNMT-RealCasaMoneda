package com.example.apprealcasamoneda.fragments.PhysicalPersonCertificates

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import com.example.apprealcasamoneda.R
import com.example.apprealcasamoneda.databinding.FrOcStep1PhysicalPersonBinding
import com.example.apprealcasamoneda.databinding.FrOcStep3PhysicalPersonBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var binding: FrOcStep3PhysicalPersonBinding


/**
 * A simple [Fragment] subclass.
 * Use the [Step3_PPC.newInstance] factory method to
 * create an instance of this fragment.
 */
class Step3_PPC : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        binding = FrOcStep3PhysicalPersonBinding.inflate(inflater, container, false)


        binding.step3IconDropdown1?.setOnClickListener{
            showHide(binding.RLStep3Dropdown, binding.step3IconDropdown1)
        }

        binding.step3IconDropdown2?.setOnClickListener{
            showHide(binding.RLStep3Dropdown2, binding.step3IconDropdown2)
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
         * @return A new instance of fragment Step3_PPC.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Step3_PPC().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}