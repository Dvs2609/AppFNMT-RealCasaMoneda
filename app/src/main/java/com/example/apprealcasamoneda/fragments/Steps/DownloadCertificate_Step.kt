package com.example.apprealcasamoneda.fragments.Steps

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
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
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import com.example.apprealcasamoneda.R
import com.example.apprealcasamoneda.databinding.FrStepDownloadCertificateBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var binding: FrStepDownloadCertificateBinding


/**
 * A simple [Fragment] subclass.
 * Use the [ProveIdentity_Step.newInstance] factory method to
 * create an instance of this fragment.
 */
class DownloadCertificate_Step : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var preferences : SharedPreferences


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
        binding = FrStepDownloadCertificateBinding.inflate(inflater, container, false)



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

        preferences = activity?.getSharedPreferences("preference", Context.MODE_PRIVATE)!!
        val currentLanguage = preferences?.getString("language", "en")

        val acceptTerms:String
        //val currentLanguage = Locale.getDefault().language

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

        //
        //Check if the fields in the request are empty and put a red shape
        val editTextId = binding.dcIdField
        val RLid = binding.stepDcSetId
        val iconId = binding.dcIdIcon

        val editTextSurname = binding.dcSurnameField
        val RLsurname = binding.stepDcSetSurname
        val iconSurname = binding.dcSurnameIcon

        val editTextCode = binding.dcCodeField
        val RLcode = binding.stepDcSetCod
        val iconDode= binding.dcCodeIcon

        binding.rectangularLYstep4Download.setOnClickListener {
            val fields = listOf(editTextId, editTextSurname, editTextCode)
            val relaLayouts = listOf(RLid, RLsurname, RLcode)
            val checkIcons = listOf(iconId, iconSurname, iconDode)
            checkFields(fields, relaLayouts, checkIcons)
        }

        binding.stepDownloadTxtTerms.text = spannableString
        binding.stepDownloadTxtTerms.movementMethod = LinkMovementMethod.getInstance()

        //
        //Adapt the navBar to the previous Fragment and hide some parts
        val navValue = arguments?.getString("key")
        val navValue2 = arguments?.getString("key2")
        val sharedPreferences = requireContext().getSharedPreferences("preference", Context.MODE_PRIVATE)
        val language = sharedPreferences.getString("language","en")

        when {
            navValue == "representative" -> {
                binding.txtNavStep4.text = resources.getString(R.string.oc_repre_step3_nav)
                binding.ocStep4.visibility = View.VISIBLE
                binding.ocStep4.text = resources.getString(R.string.oc_pp_step3)
            }
            navValue2 == "physical" -> {
                binding.txtNavStep4.text = resources.getString(R.string.oc_pp_step3_nav)
                binding.ocStep4.visibility = View.VISIBLE
                binding.ocStep4.text = resources.getString(R.string.oc_pp_step4)
            }
            else ->{
                binding.txtNavStep4.visibility = View.GONE
                binding.ocStep4.visibility = View.GONE
                binding.downloadCertStepBackCross.visibility = View.GONE
            }

        }

        binding.downloadCertStepBackCross.setOnClickListener{
            val fragmentManager = requireFragmentManager()
            fragmentManager.popBackStack()
        }

        return binding.root
    }

    private fun checkFields(fields: List<EditText>, relaLayouts: List<RelativeLayout>, checkIcons: List<ImageView>) {
        for (i in 0 until fields.size) {
            val field = fields[i]
            val layout = relaLayouts[i]
            val checkIcon = checkIcons[i]
            if (field.text.toString().isEmpty()) {
                layout.setBackgroundResource(R.drawable.shape_corners_red_relative_layout)
                checkIcon.setImageResource(R.drawable.cross_svg)
                field.error = "Campo requerido"
            } else {
                layout.setBackgroundResource(R.drawable.shape_corners_relative_layout)
                checkIcon.setImageResource(R.drawable.icon_check_svg)
                var allFieldsCompleted = true
                for (i in 0 until fields.size) {
                    val field = fields[i]
                    if (field.text.toString().isEmpty()) {
                        allFieldsCompleted = false
                        break
                    }
                }
                if (allFieldsCompleted) {
                    Toast.makeText(context, "Petición enviada correctamente", Toast.LENGTH_SHORT).show()
                }
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
            DownloadCertificate_Step().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}