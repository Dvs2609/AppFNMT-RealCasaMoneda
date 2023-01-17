package com.example.apprealcasamoneda.fragments.PhysicalPersonCertificates

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.LocalServerSocket
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.apprealcasamoneda.R
import com.example.apprealcasamoneda.databinding.FrOcStep1PhysicalPersonBinding
import com.example.apprealcasamoneda.databinding.FrOcStep2PhysicalPersonBinding
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var binding: FrOcStep2PhysicalPersonBinding


/**
 * A simple [Fragment] subclass.
 * Use the [Step2_PPC.newInstance] factory method to
 * create an instance of this fragment.
 */
class Step2_PPC : Fragment() {
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
        binding = FrOcStep2PhysicalPersonBinding.inflate(inflater, container, false)

        val redirectTerms = getString(R.string.oc_pp_step2_accept_terms)

        val spannableString = SpannableString(redirectTerms)
        val clickableSpan = object : ClickableSpan(){
            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.BLUE
                ds.isUnderlineText = false
            }
            override fun onClick(widget: View) {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"))
                startActivity(browserIntent)
            }
        }

        val term_es = "términos y condiciones"
        val term_en = "terms and conditions"


        val acceptTerms:String
        val currentLanguage = Locale.getDefault().language

        if(currentLanguage == "es"){
            val redirectTerms = getString(R.string.oc_pp_step2_accept_terms)
            val startIndex = redirectTerms.indexOf(term_es)
            val endIndex = startIndex + term_es.length
            spannableString.setSpan(clickableSpan,startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        }else{
            val redirectTerms = getString(R.string.oc_pp_step2_accept_terms)
            val startIndex = redirectTerms.indexOf(term_en)
            val endIndex = startIndex + term_en.length
            spannableString.setSpan(clickableSpan,startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        }

        binding.step2TxtTerms1.text = spannableString
        binding.step2TxtTerms1.movementMethod = LinkMovementMethod.getInstance()

        val navValue = arguments?.getString("key")
        val navValue2 = arguments?.getString("key2")
        val sharedPreferences = requireContext().getSharedPreferences("preference", Context.MODE_PRIVATE)
        val language = sharedPreferences.getString("language","en")

        when {
            navValue == "representative" -> binding.txtNavStep2.text = resources.getString(R.string.oc_repre_step2_nav)
            navValue2 == "physical" -> binding.txtNavStep2.text = resources.getString(R.string.oc_pp_step2_nav)
            else -> binding.txtNavStep2.text = "Error"
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
         * @return A new instance of fragment Step2_PPC.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Step2_PPC().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}